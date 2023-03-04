package intelligent_bank_msa.remitservice.dto.remit;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BalanceRequest {

    private long inputMoney;
    private String bankBookNum;
}
