package intelligent_bank_msa.atmservice.dto.bankbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordCheckRequest {

    private String inputPassword;
    private String originalPassword;

    public static PasswordCheckRequest createRequest(String inputPassword, String originalPassword) {
        return new PasswordCheckRequest(inputPassword, originalPassword);
    }
}
