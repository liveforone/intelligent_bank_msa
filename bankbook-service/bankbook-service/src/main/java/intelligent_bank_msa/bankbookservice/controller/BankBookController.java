package intelligent_bank_msa.bankbookservice.controller;

import intelligent_bank_msa.bankbookservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.bankbookservice.controller.constant.BankBookUrl;
import intelligent_bank_msa.bankbookservice.controller.constant.ControllerLog;
import intelligent_bank_msa.bankbookservice.controller.restResonse.RestResponse;
import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.bankbookservice.dto.bankbook.SuspendRequest;
import intelligent_bank_msa.bankbookservice.service.BankBookService;
import intelligent_bank_msa.bankbookservice.validator.BankBookValidator;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankBookController {

    private final BankBookService bankBookService;
    private final BankBookValidator bankBookValidator;

    @GetMapping(BankBookUrl.MY_BANK)
    public ResponseEntity<?> myBank(@PathVariable("email") String email) {
        BankBookResponse bankBook = bankBookService.getBankBookByEmail(email);

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        return ResponseEntity.ok(bankBook);
    }

    @GetMapping(BankBookUrl.CREATE)
    public ResponseEntity<?> postBankBookPage() {
        return RestResponse.createPage();
    }

    @PostMapping(BankBookUrl.CREATE)
    @LogExecutionTime
    public ResponseEntity<?> postBankBook(
            @RequestBody @Valid BankBookRequest bankBookRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            return RestResponse.validError(bindingResult);
        }

        String email = bankBookRequest.getEmail();
        BankBookResponse bankBook = bankBookService.getBankBookByEmail(email);

        if (!CommonUtils.isNull(bankBook)) {
            return RestResponse.duplicateBankBook();
        }

        bankBookService.saveBankBook(bankBookRequest);
        log.info(ControllerLog.CREATE_LOG.getValue());

        return RestResponse.createSuccess();
    }

    @PatchMapping(BankBookUrl.SUSPEND)
    public ResponseEntity<?> suspendBankBook(@RequestBody SuspendRequest suspendRequest) {
        String email = suspendRequest.getEmail();
        String inputPassword = suspendRequest.getPassword();
        if (bankBookValidator.isNullOrNotMatchPassword(inputPassword, email)) {
            return RestResponse.badRequest();
        }

        bankBookService.suspendByEmail(suspendRequest);
        log.info(ControllerLog.SUSPEND_LOG.getValue());

        return RestResponse.suspendSuccess();
    }

    @PatchMapping(BankBookUrl.CANCEL_SUSPEND)
    public ResponseEntity<?> cancelSuspendBankBook(
            @RequestBody SuspendRequest suspendRequest
    ) {
        String email = suspendRequest.getEmail();
        String inputPassword = suspendRequest.getPassword();
        if (bankBookValidator.isNullOrNotMatchPassword(inputPassword, email)) {
            return RestResponse.badRequest();
        }

        bankBookService.cancelSuspendByEmail(suspendRequest);
        log.info(ControllerLog.CANCEL_SUSPEND_LOG.getValue());

        return RestResponse.cancelSuspendSuccess();
    }
}
