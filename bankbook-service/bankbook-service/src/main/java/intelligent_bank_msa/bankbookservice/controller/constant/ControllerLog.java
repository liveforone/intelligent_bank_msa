package intelligent_bank_msa.bankbookservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    CREATE_LOG("통장 개설 완료"),
    SUSPEND_LOG("통장 정지 성공"),
    CANCEL_SUSPEND_LOG("통장 정지 해제 성공");

    private final String value;
}
