package intelligent_bank_msa.bankbookservice.dto.password_check;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordCheckRequest {

    private String bankBookNum;
    private String inputPassword;
}
