package intelligent_bank_msa.bankbookservice.utility;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

public class BankBookPassword {

    static PasswordEncoder encoder = new BCryptPasswordEncoder();

    public static String encodePassword(String password) {
        return encoder.encode(password);
    }

    public static boolean isNotMatchPassword(String inputPassword, String originalPassword) {
        return !encoder.matches(inputPassword, originalPassword);
    }
}
