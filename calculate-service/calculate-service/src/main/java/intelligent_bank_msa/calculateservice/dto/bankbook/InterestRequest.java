package intelligent_bank_msa.calculateservice.dto.bankbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class InterestRequest {

    private String bankBookNum;
    private long inputMoney;

    public static InterestRequest makeInterestRequest(String bankBookNum, long inputMoney) {
        return new InterestRequest(bankBookNum, inputMoney);
    }
}
