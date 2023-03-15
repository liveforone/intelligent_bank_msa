package intelligent_bank_msa.userservice.service.util;


import intelligent_bank_msa.userservice.dto.MemberResponse;
import intelligent_bank_msa.userservice.dto.MemberSignupRequest;
import intelligent_bank_msa.userservice.domain.Member;

import java.util.List;
import java.util.stream.Collectors;

public class MemberMapper {

    public static Member dtoToEntity(MemberSignupRequest memberSignupRequest) {
        return Member.builder()
                .id(memberSignupRequest.getId())
                .email(memberSignupRequest.getEmail())
                .password(memberSignupRequest.getPassword())
                .realName(memberSignupRequest.getRealName())
                .auth(memberSignupRequest.getAuth())
                .build();
    }

    public static MemberResponse entityToDto(Member member) {
        return MemberResponse.builder()
                .id(member.getId())
                .email(member.getEmail())
                .realName(member.getRealName())
                .auth(member.getAuth())
                .build();
    }

    public static List<MemberResponse> entityToDtoList(List<Member> members) {
        return members
                .stream()
                .map(MemberMapper::entityToDto)
                .collect(Collectors.toList());
    }
}
