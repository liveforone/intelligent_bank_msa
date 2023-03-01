package intelligent_bank_msa.userservice.repository;

import intelligent_bank_msa.userservice.model.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long>, MemberRepositoryCustom {
}
