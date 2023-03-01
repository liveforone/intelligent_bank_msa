package intelligent_bank_msa.bankbookservice.dto;

import intelligent_bank_msa.bankbookservice.model.BankBookState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BankBookResponse {

    private Long id;
    private String bankBookNum;
    private long balance;
    private BankBookState bankBookState;
    private String email;
    private LocalDate createdDate;
}
