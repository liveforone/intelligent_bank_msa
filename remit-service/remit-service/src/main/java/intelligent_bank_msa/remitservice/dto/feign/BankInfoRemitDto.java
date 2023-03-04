package intelligent_bank_msa.remitservice.dto.feign;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankInfoRemitDto {

    private String senderBankBookNum;
    private String receiverBankBookNum;
    private PasswordStatus passwordStatus;
    private BankBookState senderBankBookState;
    private BankBookState receiverBankBookState;
}
