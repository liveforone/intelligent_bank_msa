package intelligent_bank_msa.atmservice.dto.feign;

import intelligent_bank_msa.atmservice.dto.feign.constant.BankBookState;
import intelligent_bank_msa.atmservice.dto.feign.constant.PasswordStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankInfoAtmDto {

    private String bankBookNum;
    private PasswordStatus passwordStatus;
    private BankBookState bankBookState;
}
