package intelligent_bank_msa.atmservice.controller.restResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;

import java.util.Objects;

public class RestResponse {

    public static ResponseEntity<?> validError(BindingResult bindingResult) {
        String errorMessage = Objects
                .requireNonNull(bindingResult.getFieldError())
                .getDefaultMessage();
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(errorMessage);
    }

    public static ResponseEntity<?> noBankBook() {
        return ResponseEntity
                .status(HttpStatus.NOT_FOUND)
                .body(ResponseMessage.NO_BANKBOOK.getValue());
    }

    public static ResponseEntity<?> suspendBank() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.SUSPEND_BANKBOOK.getValue());
    }

    public static ResponseEntity<?> wrongPassword() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.WRONG_PASSWORD.getValue());
    }

    public static ResponseEntity<?> depositSuccess() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseMessage.DEPOSIT_SUCCESS.getValue());
    }

    public static ResponseEntity<?> withdrawSuccess() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseMessage.WITHDRAW_SUCCESS.getValue());
    }
}
