package intelligent_bank_msa.bankbookservice.validator;

import intelligent_bank_msa.bankbookservice.domain.BankBook;
import intelligent_bank_msa.bankbookservice.repository.BankBookRepository;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class BankBookValidator {

    private final BankBookRepository bankBookRepository;

    static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public boolean isNullOrNotMatchPassword(String inputPassword, String email) {
        BankBook foundBankBook = bankBookRepository.findOneByEmail(email);

        if (CommonUtils.isNull(foundBankBook)) {
            return true;
        } else return !encoder.matches(inputPassword, foundBankBook.getPassword());
    }

    public boolean isNotMatchPassword(String inputPassword, String originalPassword) {
        return !encoder.matches(inputPassword, originalPassword);
    }
}
