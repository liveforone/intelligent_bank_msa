package intelligent_bank_msa.atmservice.controller;

import intelligent_bank_msa.atmservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.atmservice.client.BankBookServiceClient;
import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.atmservice.dto.bankbook.PasswordCheckRequest;
import intelligent_bank_msa.atmservice.dto.bankbook.PasswordCheckResponse;
import intelligent_bank_msa.atmservice.dto.bankbook.PasswordStatus;
import intelligent_bank_msa.atmservice.service.AtmService;
import intelligent_bank_msa.atmservice.utility.BankBookStateCheck;
import intelligent_bank_msa.atmservice.utility.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
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

    private final BankBookServiceClient bankBookServiceClient;
    private final AtmService atmService;
    private final CircuitBreakerFactory circuitBreakerFactory;
    CircuitBreaker circuitBreaker = circuitBreakerFactory.create("atm-service-breaker");

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

        String bankBookNum = atmRequest.getBankBookNum();
        BankBookResponse bankBook = circuitBreaker.run(
                () -> bankBookServiceClient.getBankBook(bankBookNum),
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

        String inputPassword = atmRequest.getPassword();
        PasswordCheckRequest request = PasswordCheckRequest.createRequest(
                bankBookNum,
                inputPassword
        );

        PasswordCheckResponse checkResponse = bankBookServiceClient.checkBankPassword(request);
        if (Objects.equals(checkResponse.getStatus(), PasswordStatus.FALSE.name())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        atmService.depositAtm(atmRequest);
        log.info("ATM 입금 성공");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 입금에 성공하셨습니다");
    }

    @PostMapping("/withdraw")
    @LogExecutionTime
    public ResponseEntity<?> withdrawAtm(
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

        String bankBookNum = atmRequest.getBankBookNum();
        BankBookResponse bankBook = circuitBreaker.run(
                () -> bankBookServiceClient.getBankBook(bankBookNum),
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

        String inputPassword = atmRequest.getPassword();
        PasswordCheckRequest request = PasswordCheckRequest.createRequest(
                bankBookNum,
                inputPassword
        );

        PasswordCheckResponse checkResponse = bankBookServiceClient.checkBankPassword(request);
        if (Objects.equals(checkResponse.getStatus(), PasswordStatus.FALSE.name())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        atmService.withdrawAtm(atmRequest);
        log.info("ATM 출금 성공");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("ATM 출금에 성공하셨습니다");
    }
}
