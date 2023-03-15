package intelligent_bank_msa.userservice.service;

import intelligent_bank_msa.userservice.dto.ChangeEmailRequest;
import intelligent_bank_msa.userservice.dto.MemberLoginRequest;
import intelligent_bank_msa.userservice.dto.MemberResponse;
import intelligent_bank_msa.userservice.dto.MemberSignupRequest;
import intelligent_bank_msa.userservice.jwt.JwtTokenProvider;
import intelligent_bank_msa.userservice.jwt.TokenInfo;
import intelligent_bank_msa.userservice.domain.Role;
import intelligent_bank_msa.userservice.repository.MemberRepository;
import intelligent_bank_msa.userservice.service.util.PasswordUtils;
import intelligent_bank_msa.userservice.service.util.MemberMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class MemberService {

    private final MemberRepository memberRepository;
    private final AuthenticationManagerBuilder authenticationManagerBuilder;
    private final JwtTokenProvider jwtTokenProvider;

    private static final String ADMIN = "admin@intelligentBank.com";

    public MemberResponse getMemberEntity(String email) {
        return MemberMapper.entityToDto(memberRepository.findByEmail(email));
    }

    /*
     * 모든 유저 반환
     * when : 권한이 어드민인 유저가 호출할때
     */
    public List<MemberResponse> getAllMemberForAdmin() {
        return MemberMapper.entityToDtoList(memberRepository.findAll());
    }

    @Transactional
    public void signup(MemberSignupRequest memberSignupRequest) {
        memberSignupRequest.setPassword(
                PasswordUtils.encodePassword(memberSignupRequest.getPassword())
        );

        if (Objects.equals(memberSignupRequest.getEmail(), ADMIN)) {
            memberSignupRequest.setAuth(Role.ADMIN);
        } else {
            memberSignupRequest.setAuth(Role.MEMBER);
        }

        memberRepository.save(MemberMapper.dtoToEntity(memberSignupRequest));
    }

    @Transactional
    public TokenInfo login(MemberLoginRequest memberLoginRequest) {
        String email = memberLoginRequest.getEmail();
        String password = memberLoginRequest.getPassword();

        UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(email, password);
        Authentication authentication = authenticationManagerBuilder
                .getObject()
                .authenticate(authenticationToken);

        return jwtTokenProvider
                .generateToken(authentication);
    }

    @Transactional
    public void updateEmail(String email, ChangeEmailRequest changeEmailRequest) {
        String newEmail = changeEmailRequest.getEmail();
        memberRepository.updateEmail(email, newEmail);
    }

    @Transactional
    public void updatePassword(String inputPassword, String email) {
        String newPassword = PasswordUtils.encodePassword(inputPassword);
        memberRepository.updatePassword(newPassword, email);
    }

    @Transactional
    public void deleteUser(String email) {
        memberRepository.deleteByEmail(email);
    }
}
