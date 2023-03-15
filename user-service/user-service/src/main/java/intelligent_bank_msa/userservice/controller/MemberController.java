package intelligent_bank_msa.userservice.controller;

import intelligent_bank_msa.userservice.dto.*;
import intelligent_bank_msa.userservice.jwt.TokenInfo;
import intelligent_bank_msa.userservice.domain.Role;
import intelligent_bank_msa.userservice.service.MemberService;
import intelligent_bank_msa.userservice.validator.MemberValidator;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;
import java.util.Objects;

@RestController
@RequiredArgsConstructor
@Slf4j
public class MemberController {

    private final MemberService memberService;
    private final MemberValidator memberValidator;

    @GetMapping(MemberUrl.HOME)
    public ResponseEntity<?> home() {
        return ResponseEntity.ok("home");
    }

    @GetMapping(MemberUrl.SIGNUP)
    public ResponseEntity<?> signupPage() {
        return ResponseEntity.ok("가입할 이메일과 비밀번호 그리고 실명을 입력해주세요");
    }

    @PostMapping(MemberUrl.SIGNUP)
    public ResponseEntity<?> signup(
            @RequestBody @Valid MemberSignupRequest memberSignupRequest,
            BindingResult bindingResult
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        if (memberValidator.isDuplicateEmail(memberSignupRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("중복되는 이메일이 있어 회원가입이 불가능합니다.");
        }

        memberService.signup(memberSignupRequest);
        log.info("회원 가입 성공");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("반갑습니다. 회원가입에 성공하셨습니다.");
    }

    @GetMapping(MemberUrl.LOGIN)
    public ResponseEntity<?> loginPage() {
        return ResponseEntity.ok("이메일과 비밀번호를 입력하세요.");
    }

    @PostMapping(MemberUrl.LOGIN)
    public ResponseEntity<?> login(
            @RequestBody @Valid MemberLoginRequest memberLoginRequest,
            BindingResult bindingResult,
            HttpServletResponse response
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        try {
            TokenInfo tokenInfo = memberService.login(memberLoginRequest);
            log.info("로그인 성공");

            response.addHeader("access-token", tokenInfo.getAccessToken());
            response.addHeader("refresh-token", tokenInfo.getRefreshToken());
            return ResponseEntity.ok("로그인에 성공하였습니다.");
        } catch (BadCredentialsException exception) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("이메일 혹은 비밀번호가 다릅니다.\n다시 시도하세요.");
        }
    }

    @GetMapping(MemberUrl.MY_PAGE)
    public ResponseEntity<MemberResponse> myPage(Principal principal) {
        MemberResponse member = memberService.getMemberEntity(principal.getName());

        return ResponseEntity.ok(member);
    }

    @PatchMapping(MemberUrl.CHANGE_EMAIL)
    public ResponseEntity<?> changeEmail(
            @RequestBody @Valid ChangeEmailRequest changeEmailRequest,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        if (memberValidator.isDuplicateEmail(changeEmailRequest.getEmail())) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("중복되는 이메일이 있어 변경이 불가능합니다.");
        }

        String email = principal.getName();
        memberService.updateEmail(email, changeEmailRequest);
        log.info("이메일 변경 성공");

        return ResponseEntity.ok("이메일이 변경되었습니다.");
    }

    @PatchMapping(MemberUrl.CHANGE_PASSWORD)
    public ResponseEntity<?> changePassword(
            @RequestBody @Valid ChangePasswordRequest changePasswordRequest,
            BindingResult bindingResult,
            Principal principal
    ) {
        if (bindingResult.hasErrors()) {
            String errorMessage = Objects
                    .requireNonNull(bindingResult.getFieldError())
                    .getDefaultMessage();
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(errorMessage);
        }

        String inputPw = changePasswordRequest.getOldPassword();
        String email = principal.getName();
        if (memberValidator.isNotMatchingPassword(inputPw, email)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 다릅니다. 다시 입력해주세요.");
        }

        String requestPw = changePasswordRequest.getNewPassword();
        memberService.updatePassword(requestPw, email);
        log.info("비밀번호 변경 성공");

        return ResponseEntity.ok("비밀번호가 변경되었습니다.");
    }
    @DeleteMapping(MemberUrl.WITHDRAW)
    public ResponseEntity<?> withdraw(
            @RequestBody String password,
            Principal principal
    ) {
        String email = principal.getName();
        if (memberValidator.isNotMatchingPassword(password, email)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("비밀번호가 다릅니다. 다시 입력해주세요.");
        }

        memberService.deleteUser(email);
        log.info("회원 : " + email + " 탈퇴 성공");

        return ResponseEntity.ok("그동안 서비스를 이용해주셔서 감사합니다.");
    }

    @GetMapping(MemberUrl.ADMIN)
    public ResponseEntity<?> adminPage(Principal principal) {
        MemberResponse foundMember = memberService.getMemberEntity(principal.getName());

        if (!foundMember.getAuth().equals(Role.ADMIN)) {
            log.error("어드민 페이지 접속에 실패했습니다.");
            return ResponseEntity
                    .status(HttpStatus.FORBIDDEN)
                    .body("접근 권한이 없습니다.");
        }

        List<MemberResponse> allMembers = memberService.getAllMemberForAdmin();
        log.info("어드민이 어드민 페이지에 접속했습니다.");

        return ResponseEntity.ok(allMembers);
    }

    @GetMapping(MemberUrl.PROHIBITION)
    public ResponseEntity<?> prohibition() {
        return ResponseEntity
                .status(HttpStatus.FORBIDDEN)
                .body("접근 권한이 없습니다.");
    }
}
