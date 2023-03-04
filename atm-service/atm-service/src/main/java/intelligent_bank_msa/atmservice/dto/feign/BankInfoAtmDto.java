package intelligent_bank_msa.atmservice.dto.feign;

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
