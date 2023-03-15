package intelligent_bank_msa.bankbookservice.controller.restResonse;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ResponseMessage {

    NO_BANKBOOK("통장이 없습니다. \n통장을 개설하여주세요"),
    CREATE_PAGE("통장 개설 페이지입니다."),
    DUPLICATE_BANK("이미 통장이 존재합니다.\n 통장은 '하나'만 개설 가능합니다."),
    CREATE_SUCCESS("통장이 정상적으로 개설되었습니다.\n감사합니다"),
    WRONG_PASSWORD("비밀번호가 틀렸습니다."),
    SUSPEND("통장이 성공적으로 정지 되었습니다."),
    CANCEL_SUSPEND("통장의 정지가 성공적으로 해제 되었습니다.");

    private final String value;
}
