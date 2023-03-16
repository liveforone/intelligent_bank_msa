package intelligent_bank_msa.remitservice.service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceLog {

    BANK_NUM("통장 번호 : "),
    DEPOSIT_SUM(" 입금 금액 : "),
    WITHDRAW_SUM(" 출금 금액 : ");

    private final String value;
}
