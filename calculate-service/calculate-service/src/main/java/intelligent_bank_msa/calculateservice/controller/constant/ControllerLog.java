package intelligent_bank_msa.calculateservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    CALCULATE_INTEREST_SUCCESS("이자 지금 완료");

    private final String value;
}
