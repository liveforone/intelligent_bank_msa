package intelligent_bank_msa.userservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank_msa.userservice.domain.Member;
import intelligent_bank_msa.userservice.domain.QMember;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class MemberRepositoryImpl implements MemberRepositoryCustom{

    private final JPAQueryFactory queryFactory;
    QMember member = QMember.member;

    public Member findByEmail(String email) {
        return queryFactory.selectFrom(member)
                .where(member.email.eq(email))
                .fetchOne();
    }

    public void updateEmail(String oldEmail, String newEmail) {
        queryFactory.update(member)
                .set(member.email, newEmail)
                .where(member.email.eq(oldEmail))
                .execute();
    }

    public void updatePassword(String password, String email) {
        queryFactory.update(member)
                .set(member.password, password)
                .where(member.email.eq(email))
                .execute();
    }

    public void deleteByEmail(String email) {
        queryFactory.delete(member)
                .where(member.email.eq(email))
                .execute();
    }
}
