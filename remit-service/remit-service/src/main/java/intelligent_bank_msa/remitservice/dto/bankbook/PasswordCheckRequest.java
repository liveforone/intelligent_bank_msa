package intelligent_bank_msa.remitservice.dto.bankbook;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class PasswordCheckRequest {

    private String bankBookNum;
    private String inputPassword;

    public static PasswordCheckRequest createRequest(String bankBookNum, String inputPassword) {
        return new PasswordCheckRequest(bankBookNum, inputPassword);
    }
}
