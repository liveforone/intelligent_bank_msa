package intelligent_bank_msa.remitservice.dto.bankbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class BalanceRequest {

    private long inputMoney;
    private String bankBookNum;

    public static BalanceRequest makeBalanceRequest(long inputMoney, String bankBookNum) {
        return new BalanceRequest(inputMoney, bankBookNum);
    }
}
