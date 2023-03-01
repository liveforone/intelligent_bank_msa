package intelligent_bank_msa.userservice.validator;

import intelligent_bank_msa.userservice.dto.MemberLoginRequest;
import intelligent_bank_msa.userservice.model.Member;
import intelligent_bank_msa.userservice.service.MemberService;
import intelligent_bank_msa.userservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class MemberValidator {

    private final MemberService memberService;

    public boolean isDuplicateEmail(String email) {
        Member member = memberService.getMemberEntity(email);

        return !CommonUtils.isNull(member);
    }

    public boolean isNotRightMemberInfo(MemberLoginRequest memberLoginRequest) {
        Member foundMember = memberService.getMemberEntity(memberLoginRequest.getEmail());

        return CommonUtils.isNull(foundMember)
                || MemberPasswordValidator.isNotMatchingPassword(
                memberLoginRequest.getPassword(),
                foundMember.getPassword());
    }
}
