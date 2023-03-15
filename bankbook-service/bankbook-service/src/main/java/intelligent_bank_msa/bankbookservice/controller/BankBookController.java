package intelligent_bank_msa.bankbookservice.controller;

import intelligent_bank_msa.bankbookservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.bankbookservice.controller.util.RestResponse;
import intelligent_bank_msa.bankbookservice.dto.bankbook.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.bankbook.SuspendRequest;
import intelligent_bank_msa.bankbookservice.domain.BankBook;
import intelligent_bank_msa.bankbookservice.service.BankBookService;
import intelligent_bank_msa.bankbookservice.utility.BankBookMapper;
import intelligent_bank_msa.bankbookservice.utility.BankBookPassword;
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

    @GetMapping(BankBookUrl.MY_BANK)
    public ResponseEntity<?> myBank(@PathVariable("email") String email) {
        BankBook bankBook = bankBookService.getBankBookByEmail(email);

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        return ResponseEntity.ok(BankBookMapper.entityToDtoDetail(bankBook));
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
        BankBook bankBook = bankBookService.getBankBookByEmail(email);

        if (!CommonUtils.isNull(bankBook)) {
            return RestResponse.duplicateBankBook();
        }

        bankBookService.saveBankBook(bankBookRequest);
        log.info(ControllerLog.CREATE_LOG.getValue());

        return RestResponse.createSuccess();
    }

    @PatchMapping(BankBookUrl.SUSPEND)
    public ResponseEntity<?> suspendBankBook(@RequestBody SuspendRequest suspendRequest) {
        BankBook bankBook = bankBookService.getBankBookByEmail(suspendRequest.getEmail());

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        String inputPassword = suspendRequest.getPassword();
        String originalPassword = bankBook.getPassword();
        if (BankBookPassword.isNotMatchPassword(inputPassword, originalPassword)) {
            return RestResponse.notMatchPassword();
        }

        bankBookService.suspendByEmail(suspendRequest);
        log.info(ControllerLog.SUSPEND_LOG.getValue());

        return RestResponse.suspendSuccess();
    }

    @PatchMapping(BankBookUrl.CANCEL_SUSPEND)
    public ResponseEntity<?> cancelSuspendBankBook(
            @RequestBody SuspendRequest suspendRequest
    ) {
        BankBook bankBook = bankBookService.getBankBookByEmail(suspendRequest.getEmail());

        if (CommonUtils.isNull(bankBook)) {
            return RestResponse.noBankBook();
        }

        String inputPassword = suspendRequest.getPassword();
        String originalPassword = bankBook.getPassword();
        if (BankBookPassword.isNotMatchPassword(inputPassword, originalPassword)) {
            return RestResponse.notMatchPassword();
        }

        bankBookService.cancelSuspendByEmail(suspendRequest);
        log.info(ControllerLog.CANCEL_SUSPEND_LOG.getValue());

        return RestResponse.cancelSuspendSuccess();
    }
}
