package intelligent_bank_msa.atmservice.controller;

import intelligent_bank_msa.atmservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.atmservice.controller.constant.AtmUrl;
import intelligent_bank_msa.atmservice.controller.constant.ControllerLog;
import intelligent_bank_msa.atmservice.controller.restResponse.RestResponse;
import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.feign.PasswordStatus;
import intelligent_bank_msa.atmservice.dto.feign.BankInfoAtmDto;
import intelligent_bank_msa.atmservice.feign.BankBookFeignService;
import intelligent_bank_msa.atmservice.service.AtmService;
import intelligent_bank_msa.atmservice.utility.BankBookStateCheck;
import intelligent_bank_msa.atmservice.utility.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class AtmController {

    private final AtmService atmService;
    private final BankBookFeignService bankBookFeignService;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

    @PostMapping(AtmUrl.DEPOSIT)
    @LogExecutionTime
    public ResponseEntity<?> depositAtm(
            @RequestBody @Valid AtmRequest atmRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return RestResponse.validError(bindingResult);
        }

        BankInfoAtmDto bankBook = circuitBreakerFactory
                .create(ControllerLog.ATM_CIRCUIT.getValue())
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return RestResponse.suspendBank();
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return RestResponse.wrongPassword();
        }

        atmService.depositAtm(atmRequest);
        log.info(ControllerLog.DEPOSIT_SUCCESS.getValue());

        return RestResponse.depositSuccess();
    }

    @PostMapping(AtmUrl.WITHDRAW)
    @LogExecutionTime
    public ResponseEntity<?> withdrawAtm(
            @RequestBody @Valid AtmRequest atmRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return RestResponse.validError(bindingResult);
        }

        BankInfoAtmDto bankBook = circuitBreakerFactory
                .create(ControllerLog.ATM_CIRCUIT.getValue())
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return RestResponse.suspendBank();
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return RestResponse.wrongPassword();
        }

        atmService.withdrawAtm(atmRequest);
        log.info(ControllerLog.WITHDRAW_SUCCESS.getValue());

        return RestResponse.withdrawSuccess();
    }
}
