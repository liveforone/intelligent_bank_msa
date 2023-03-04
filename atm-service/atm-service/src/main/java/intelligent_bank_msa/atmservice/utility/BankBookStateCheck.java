package intelligent_bank_msa.atmservice.utility;

import intelligent_bank_msa.atmservice.dto.feign.BankBookState;
import intelligent_bank_msa.atmservice.dto.feign.BankInfoAtmDto;

public class BankBookStateCheck {

    public static boolean isSuspendBankBook(BankInfoAtmDto bankInfoAtmDto) {
        return bankInfoAtmDto.getBankBookState() == BankBookState.SUSPEND;
    }
}
