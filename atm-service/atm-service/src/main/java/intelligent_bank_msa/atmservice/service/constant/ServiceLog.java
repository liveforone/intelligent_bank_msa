package intelligent_bank_msa.atmservice.service.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ServiceLog {

    BANK_NUM("통장 번호 : "),
    DEPOSIT_SUM(" ATM 입금 금액 : "),
    WITHDRAW_SUM(" ATM 출금 금액 : ");

    private final String value;
}
