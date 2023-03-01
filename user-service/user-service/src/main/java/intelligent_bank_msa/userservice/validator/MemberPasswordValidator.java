package intelligent_bank_msa.userservice.validator;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class MemberPasswordValidator {
    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static boolean isNotMatchingPassword(String inputPassword, String originalPassword) {
        return !passwordEncoder.matches(inputPassword, originalPassword);
    }

    public static String encodePassword(String password) {
        return passwordEncoder.encode(password);
    }
}
