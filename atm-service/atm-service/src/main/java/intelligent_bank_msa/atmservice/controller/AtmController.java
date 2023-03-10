package intelligent_bank_msa.atmservice.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import intelligent_bank_msa.atmservice.aop.stopwatch.LogExecutionTime;
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

    @PostMapping("/deposit")
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
                .create("atm-service-circuit")
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("???????????? ?????? ?????? ???????????????.");
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("????????? ???????????????.\n????????? ??????????????? ????????? ??????????????????.");
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("??????????????? ???????????? ????????????.");
        }

        atmService.depositAtm(atmRequest);
        log.info("ATM ?????? ??????");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM ????????? ?????????????????????");
    }

    @PostMapping("/withdraw")
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
                .create("atm-service-circuit")
                .run(() -> bankBookFeignService.getBankBook(atmRequest),
                        throwable -> null
                );

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("???????????? ?????? ?????? ???????????????.");
        }

        if (BankBookStateCheck.isSuspendBankBook(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("????????? ???????????????.\n????????? ??????????????? ????????? ??????????????????.");
        }

        if (Objects.equals(
                bankBook.getPasswordStatus().name(),
                PasswordStatus.FALSE.name()
        )) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("??????????????? ???????????? ????????????.");
        }

        atmService.withdrawAtm(atmRequest);
        log.info("ATM ?????? ??????");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM ????????? ?????????????????????");
    }
}
