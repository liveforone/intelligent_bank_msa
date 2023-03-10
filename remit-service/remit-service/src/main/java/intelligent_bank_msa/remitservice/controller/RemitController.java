package intelligent_bank_msa.remitservice.controller;

import intelligent_bank_msa.remitservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.remitservice.dto.feign.BankInfoRemitDto;
import intelligent_bank_msa.remitservice.dto.feign.PasswordStatus;
import intelligent_bank_msa.remitservice.dto.remit.RemitRequest;
import intelligent_bank_msa.remitservice.feign.BankBookFeignService;
import intelligent_bank_msa.remitservice.service.RemitService;
import intelligent_bank_msa.remitservice.utility.BankBookStateCheck;
import intelligent_bank_msa.remitservice.utility.CommonUtils;
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
public class RemitController {

    private final BankBookFeignService bankBookFeignService;
    private final RemitService remitService;
    private final CircuitBreakerFactory<?, ?> circuitBreakerFactory;

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

        BankInfoRemitDto remitDto = circuitBreakerFactory
                .create("remit-service-circuit")
                .run(() -> bankBookFeignService.getBankBook(remitRequest),
                throwable -> null
        );

        if (CommonUtils.isNull(remitDto)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("???????????? ?????? ?????? ???????????????.");
        }

        if (BankBookStateCheck.isSuspendSenderBank(remitDto.getSenderBankBookState())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("????????? ???????????????.\n????????? ??????????????? ????????? ??????????????????.");
        }

        if (BankBookStateCheck.isSuspendReceiverBank(remitDto.getReceiverBankBookState())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("????????? ???????????????.\n????????? ??????????????? ????????? ??????????????????.");
        }

        if (Objects.equals(
                remitDto.getPasswordStatus().name(),
                PasswordStatus.FALSE.name())
        ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("??????????????? ???????????? ????????????.");
        }

        remitService.remit(remitRequest);
        log.info("?????? ??????");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("?????? ?????????????????????.");
    }
}
