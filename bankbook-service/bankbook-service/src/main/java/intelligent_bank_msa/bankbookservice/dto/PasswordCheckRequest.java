package intelligent_bank_msa.bankbookservice.dto;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class PasswordCheckRequest {

    private String inputPassword;
    private String originalPassword;
}
