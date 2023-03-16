package intelligent_bank_msa.atmservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import intelligent_bank_msa.atmservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.atmservice.controller.constant.AtmUrl;
import intelligent_bank_msa.atmservice.controller.constant.ControllerLog;
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
import org.springframework.http.HttpStatus;
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
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        BankInfoAtmDto bankBook = circuitBreakerFactory
                .create(ControllerLog.ATM_CIRCUIT.getValue())
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 입금이 불가능합니다.");
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        atmService.depositAtm(atmRequest);
        log.info(ControllerLog.DEPOSIT_SUCCESS.getValue());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 입금에 성공하셨습니다");
    }

    @PostMapping(AtmUrl.WITHDRAW)
    @LogExecutionTime
    public ResponseEntity<?> withdrawAtm(
            @RequestBody @Valid AtmRequest atmRequest,
            BindingResult bindingResult
    ) throws JsonProcessingException {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        BankInfoAtmDto bankBook = circuitBreakerFactory
                .create(ControllerLog.ATM_CIRCUIT.getValue())
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 출금이 불가능합니다.");
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        atmService.withdrawAtm(atmRequest);
        log.info(ControllerLog.WITHDRAW_SUCCESS.getValue());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 출금에 성공하셨습니다");
    }
}
