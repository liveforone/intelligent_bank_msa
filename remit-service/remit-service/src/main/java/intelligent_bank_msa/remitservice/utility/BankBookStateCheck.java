package intelligent_bank_msa.remitservice.utility;

import intelligent_bank_msa.remitservice.dto.feign.BankBookState;

public class BankBookStateCheck {

    public static boolean isSuspendSenderBank(BankBookState senderBankBookState) {
        return senderBankBookState == BankBookState.SUSPEND;
    }

    public static boolean isSuspendReceiverBank(BankBookState receiverBankBookState) {
        return receiverBankBookState == BankBookState.SUSPEND;
    }
}
