package intelligent_bank_msa.bankbookservice.dto.bankbook;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SuspendRequest {

    private String email;
    private String password;
}
