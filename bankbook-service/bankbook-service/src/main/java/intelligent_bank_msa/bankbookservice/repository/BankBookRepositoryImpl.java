package intelligent_bank_msa.bankbookservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank_msa.bankbookservice.domain.BankBook;
import intelligent_bank_msa.bankbookservice.domain.BankBookState;
import intelligent_bank_msa.bankbookservice.domain.QBankBook;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BankBookRepositoryImpl implements BankBookRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public BankBook findOneByEmail(String email) {
        QBankBook bankBook = QBankBook.bankBook;

        return queryFactory.selectFrom(bankBook)
                .where(bankBook.email.eq(email))
                .fetchOne();
    }

    public BankBook findOneByBankBookNum(String bankBookNum) {
        QBankBook bankBook = QBankBook.bankBook;

        return queryFactory.selectFrom(bankBook)
                .where(bankBook.bankBookNum.eq(bankBookNum))
                .fetchOne();
    }

    public void suspendByEmail(String email) {
        QBankBook bankBook = QBankBook.bankBook;

        queryFactory.update(bankBook)
                .set(bankBook.bankBookState, BankBookState.SUSPEND)
                .where(bankBook.email.eq(email))
                .execute();
    }

    public void cancelSuspendByEmail(String email) {
        QBankBook bankBook = QBankBook.bankBook;

        queryFactory.update(bankBook)
                .set(bankBook.bankBookState, BankBookState.USE)
                .where(bankBook.email.eq(email))
                .execute();
    }

    public void increaseBalance(String bankBookNum, long inputMoney) {
        QBankBook bankBook = QBankBook.bankBook;

        queryFactory.update(bankBook)
                .set(bankBook.balance, bankBook.balance.add(inputMoney))
                .where(bankBook.bankBookNum.eq(bankBookNum))
                .execute();
    }

    public void decreaseBalance(String bankBookNum, long inputMoney) {
        QBankBook bankBook = QBankBook.bankBook;

        queryFactory.update(bankBook)
                .set(bankBook.balance, bankBook.balance.add(-inputMoney))
                .where(bankBook.bankBookNum.eq(bankBookNum))
                .execute();
    }
}
