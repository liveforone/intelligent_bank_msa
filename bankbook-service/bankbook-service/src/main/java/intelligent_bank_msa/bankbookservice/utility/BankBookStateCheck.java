package intelligent_bank_msa.bankbookservice.utility;

import intelligent_bank_msa.bankbookservice.model.BankBook;
import intelligent_bank_msa.bankbookservice.model.BankBookState;

public class BankBookStateCheck {

    public static boolean isSuspendBankBook(BankBook bankBook) {
        return bankBook.getBankBookState() == BankBookState.SUSPEND;
    }
}
