package intelligent_bank_msa.calculateservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    INTEREST_ONLY_DECEMBER("이자 신청은 12월에만 가능합니다."),
    CALCULATE_INTEREST_SUCCESS("이자 지급이 완료되었습니다.");

    private final String value;
}
