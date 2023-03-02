package intelligent_bank_msa.remitservice.utility;

import intelligent_bank_msa.remitservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.remitservice.dto.bankbook.BankBookState;

public class BankBookStateCheck {

    public static boolean isSuspendBankBook(BankBookResponse bankBookResponse) {
        return bankBookResponse.getBankBookState() == BankBookState.SUSPEND;
    }
}
