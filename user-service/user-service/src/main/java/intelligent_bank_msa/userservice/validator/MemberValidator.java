package intelligent_bank_msa.userservice.validator;

import intelligent_bank_msa.userservice.domain.Member;
import intelligent_bank_msa.userservice.repository.MemberRepository;
import intelligent_bank_msa.userservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberRepository memberRepository;

    static BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public boolean isNotMatchingPassword(String inputPassword, String email) {
        Member foundMember = memberRepository.findByEmail(email);
        String originalPassword = foundMember.getPassword();

        return !passwordEncoder.matches(inputPassword, originalPassword);
    }

    public boolean isDuplicateEmail(String email) {
        Member member = memberRepository.findByEmail(email);

        return !CommonUtils.isNull(member);
    }
}
