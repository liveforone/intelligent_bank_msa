package intelligent_bank_msa.remitservice.controller;

import intelligent_bank_msa.remitservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.remitservice.controller.constant.ControllerLog;
import intelligent_bank_msa.remitservice.controller.constant.RemitUrl;
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

    @PostMapping(RemitUrl.REMIT)
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
                .create(ControllerLog.REMIT_CIRCUIT.getValue())
                .run(() -> bankBookFeignService.getBankBook(remitRequest),
                throwable -> null
        );

        if (CommonUtils.isNull(remitDto)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("존재하지 않는 통장 번호입니다.");
        }

        if (BankBookStateCheck.isSuspendSenderBank(remitDto.getSenderBankBookState())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 송금이 불가능합니다.");
        }

        if (BankBookStateCheck.isSuspendReceiverBank(remitDto.getReceiverBankBookState())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("정지된 통장입니다.\n정지된 통장으로는 송금이 불가능합니다.");
        }

        if (Objects.equals(
                remitDto.getPasswordStatus().name(),
                PasswordStatus.FALSE.name())
        ) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 일치하지 않습니다.");
        }

        remitService.remit(remitRequest);
        log.info(ControllerLog.REMIT_SUCCESS.getValue());

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("송금 완료되었습니다.");
    }
}
