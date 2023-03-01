package intelligent_bank_msa.bankbookservice.dto;

import intelligent_bank_msa.bankbookservice.model.BankBookState;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBookRequest {

    private Long id;
    private String bankBookNum;
    private String email;

    @NotBlank(message = "통장 비밀번호는 반드시 입력하셔야합니다.")
    private String password;
    private long balance;
    private BankBookState bankBookState;
}
