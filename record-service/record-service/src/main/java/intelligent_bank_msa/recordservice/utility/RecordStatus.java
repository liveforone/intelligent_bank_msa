package intelligent_bank_msa.recordservice.utility;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordStatus {

    DEPOSIT_ATM("[입금] ATM"),
    WITHDRAW_ATM("[출금] ATM"),
    DEPOSIT_REMIT("[입금] "),
    WITHDRAW_REMIT("[출금] ");

    private String value;
}
