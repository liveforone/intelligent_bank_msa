package intelligent_bank_msa.atmservice.utility;

import intelligent_bank_msa.atmservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.atmservice.dto.bankbook.BankBookState;

public class BankBookStateCheck {

    public static boolean isSuspendBankBook(BankBookResponse bankBookResponse) {
        return bankBookResponse.getBankBookState() == BankBookState.SUSPEND;
    }
}
