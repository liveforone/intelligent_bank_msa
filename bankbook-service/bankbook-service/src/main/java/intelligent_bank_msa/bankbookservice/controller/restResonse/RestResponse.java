package intelligent_bank_msa.bankbookservice.controller.restResonse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Objects;

public class RestResponse {

    public static ResponseEntity<?> noBankBook() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage.NO_BANKBOOK.getValue());
    }

    public static ResponseEntity<?> createPage() {
        return ResponseEntity.ok(ResponseMessage.CREATE_PAGE.getValue());
    }

    public static ResponseEntity<?> validError(BindingResult bindingResult) {
        String errorMessage = Objects
                .requireNonNull(bindingResult.getFieldError())
                .getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    public static ResponseEntity<?> duplicateBankBook() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.DUPLICATE_BANK.getValue());
    }

    public static ResponseEntity<?> createSuccess() {
        return ResponseEntity.ok(ResponseMessage.CREATE_SUCCESS.getValue());
    }

    public static ResponseEntity<?> suspendSuccess() {
        return ResponseEntity.ok(ResponseMessage.SUSPEND.getValue());
    }

    public static ResponseEntity<?> cancelSuspendSuccess() {
        return ResponseEntity.ok(ResponseMessage.CANCEL_SUSPEND.getValue());
    }

    public static ResponseEntity<?> badRequest() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.BAD_REQUEST.getValue());
    }
}
