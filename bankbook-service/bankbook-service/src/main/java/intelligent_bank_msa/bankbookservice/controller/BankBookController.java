package intelligent_bank_msa.bankbookservice.controller;

import intelligent_bank_msa.bankbookservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.bankbookservice.dto.BankBookRequest;
import intelligent_bank_msa.bankbookservice.dto.SuspendRequest;
import intelligent_bank_msa.bankbookservice.model.BankBook;
import intelligent_bank_msa.bankbookservice.service.BankBookService;
import intelligent_bank_msa.bankbookservice.utility.BankBookMapper;
import intelligent_bank_msa.bankbookservice.utility.BankBookPassword;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class BankBookController {

    private final BankBookService bankBookService;

    @GetMapping("/my-bank")
    public ResponseEntity<?> myBank(@RequestBody String email) {
        BankBook bankBook = bankBookService.getBankBookByEmail(email);

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body("통장이 없습니다. \n통장을 개설하여주세요");
        }

        return ResponseEntity.ok(BankBookMapper.entityToDtoDetail(bankBook));
    }

    @GetMapping("/create")
    public ResponseEntity<?> postBankBookPage() {
        return ResponseEntity.ok("통장 개설 페이지입니다.");
    }

    @PostMapping("/create")
    @LogExecutionTime
    public ResponseEntity<?> postBankBook(
            @RequestBody @Valid BankBookRequest bankBookRequest,
            BindingResult bindingResult,
            HttpServletRequest request
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity.ok(errorMessage);
        }

        String email = bankBookRequest.getEmail();
        BankBook bankBook = bankBookService.getBankBookByEmail(email);

        if (!CommonUtils.isNull(bankBook)) {
            return ResponseEntity.ok("이미 통장이 존재합니다.\n 통장은 '하나'만 개설 가능합니다.");
        }

        bankBookService.saveBankBook(bankBookRequest);
        log.info("통장 개설 완료");

        return ResponseEntity.ok("통장이 정상적으로 개설되었습니다.\n 감사합니다");
    }

    @PatchMapping("/suspend")
    public ResponseEntity<?> suspendBankBook(@RequestBody SuspendRequest suspendRequest) {
        BankBook bankBook = bankBookService.getBankBookByEmail(suspendRequest.getEmail());

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("통장이 없습니다.");
        }

        String inputPassword = suspendRequest.getPassword();
        String originalPassword = bankBook.getPassword();
        if (!BankBookPassword.isMatchPassword(inputPassword, originalPassword)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 틀렸습니다.");
        }

        bankBookService.suspendByEmail(suspendRequest);
        log.info("통장 정지 성공");

        return ResponseEntity.ok("통장이 성공적으로 정지 되었습니다.");
    }

    @PatchMapping("/cancel-suspend")
    public ResponseEntity<?> cancelSuspendBankBook(
            @RequestBody SuspendRequest suspendRequest
    ) {
        BankBook bankBook = bankBookService.getBankBookByEmail(suspendRequest.getEmail());

        if (CommonUtils.isNull(bankBook)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("통장이 없습니다.");
        }

        String inputPassword = suspendRequest.getPassword();
        String originalPassword = bankBook.getPassword();
        if (!BankBookPassword.isMatchPassword(inputPassword, originalPassword)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 틀렸습니다.");
        }

        bankBookService.cancelSuspendByEmail(suspendRequest);
        log.info("통장 정지 해제 성공");

        return ResponseEntity.ok("통장의 정지가 성공적으로 해제 되었습니다.");
    }
}
