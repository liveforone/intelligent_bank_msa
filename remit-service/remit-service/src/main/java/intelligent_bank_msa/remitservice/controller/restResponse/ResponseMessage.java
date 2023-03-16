package intelligent_bank_msa.remitservice.controller.restResponse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {
    NO_BANKBOOK("존재하지 않는 통장입니다."),
    SUSPEND_BANKBOOK("정지된 통장입니다.\n정지된 통장으로는 입/출금이 불가능합니다."),
    WRONG_PASSWORD("비밀번호가 일치하지 않습니다."),
    REMIT_SUCCESS("송금 완료되었습니다.");

    private final String value;
}
