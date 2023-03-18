package intelligent_bank_msa.calculateservice.controller.restResponse;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

public class RestResponse {

    public static ResponseEntity<?> cantRequestInterest() {
        return ResponseEntity
                .status(HttpStatus.BAD_REQUEST)
                .body(ResponseMessage.INTEREST_ONLY_DECEMBER.getValue());
    }

    public static ResponseEntity<?> calculateInterestSuccess() {
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(ResponseMessage.CALCULATE_INTEREST_SUCCESS.getValue());
    }
}
