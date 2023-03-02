package intelligent_bank_msa.atmservice.dto.bankbook;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
public class BankBookResponse {

    private Long id;

    private String bankBookNum;

    private String email;

    private String password;

    private long balance;

    private BankBookState bankBookState;
    private LocalDate createdDate;
}
