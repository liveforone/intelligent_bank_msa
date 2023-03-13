package intelligent_bank_msa.userservice.repository;


import intelligent_bank_msa.userservice.domain.Member;

public interface MemberRepositoryCustom {

    Member findByEmail(String email);

    void updateEmail(String oldEmail, String newEmail);

    void updatePassword(Long id, String password);
}
