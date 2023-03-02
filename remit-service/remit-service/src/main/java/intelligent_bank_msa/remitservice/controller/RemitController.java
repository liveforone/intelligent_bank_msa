package intelligent_bank_msa.remitservice.controller;

import intelligent_bank_msa.remitservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.remitservice.client.BankBookServiceClient;
import intelligent_bank_msa.remitservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.remitservice.dto.bankbook.PasswordCheckRequest;
import intelligent_bank_msa.remitservice.dto.bankbook.PasswordCheckResponse;
import intelligent_bank_msa.remitservice.dto.bankbook.PasswordStatus;
import intelligent_bank_msa.remitservice.dto.remit.RemitRequest;
import intelligent_bank_msa.remitservice.service.RemitService;
import intelligent_bank_msa.remitservice.utility.BankBookStateCheck;
import intelligent_bank_msa.remitservice.utility.CommonUtils;
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
public class RemitController {

    private final BankBookServiceClient bankBookServiceClient;
    private final CircuitBreakerFactory circuitBreakerFactory;
    private final RemitService remitService;

    @PostMapping("/remit")
    @LogExecutionTime
    public ResponseEntity<?> remit(
            @RequestBody @Valid RemitRequest remitRequest,
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

        String bankBookNum = remitRequest.getBankBookNum();
        CircuitBreaker circuitBreaker = circuitBreakerFactory.create("remit-service-breaker");
        BankBookResponse bankBook = circuitBreaker.run(
                () -> bankBookServiceClient.getBankBookByBankBookNum(bankBookNum),
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
                    .body("정지된 통장입니다.\n정지된 통장으로는 송금이 불가능합니다.");
        }

        BankBookResponse myBankBook = circuitBreaker.run(
                () -> bankBookServiceClient.getBankBookByBankBookNum(bankBookNum),
                throwable -> null
        );

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("내 통장 번호가 잘못되었습니다.");
        }

        String inputPassword = remitRequest.getPassword();
        PasswordCheckRequest request = PasswordCheckRequest.createRequest(
                myBankBook.getBankBookNum(),
                inputPassword
        );
        PasswordCheckResponse checkResponse = bankBookServiceClient.checkBankPassword(request);

        if (Objects.equals(checkResponse.getStatus(), PasswordStatus.FALSE.name())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        remitService.remit(remitRequest);
        log.info("송금 완료");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("송금 완료되었습니다.");
    }
}
