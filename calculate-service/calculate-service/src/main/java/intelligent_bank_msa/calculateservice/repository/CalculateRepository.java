package intelligent_bank_msa.calculateservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank_msa.calculateservice.model.QRecordCopy;
import intelligent_bank_msa.calculateservice.model.RecordState;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.time.Month;

@Repository
@RequiredArgsConstructor
public class CalculateRepository {

    private final JPAQueryFactory queryFactory;

    public Long calculateThisMonthExpense(String bankBookNum, int nowYear, Month nowMonth) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.EXPENSE),
                        record.createdYear.eq(nowYear),
                        record.createdMonth.eq(nowMonth)
                )
                .fetchOne();
    }

    public Long calculateThisMonthIncome(String bankBookNum, int nowYear, Month nowMonth) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.INCOME),
                        record.createdYear.eq(nowYear),
                        record.createdMonth.eq(nowMonth)
                )
                .fetchOne();
    }

    public Long calculateThisYearExpense (String bankBookNum, int nowYear) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.EXPENSE),
                        record.createdYear.eq(nowYear)
                )
                .fetchOne();
    }

    public Long calculateThisYearIncome(String bankBookNum, int nowYear) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.INCOME),
                        record.createdYear.eq(nowYear)
                )
                .fetchOne();
    }

    public Long calculateTotalExpense(String bankBookNum) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.EXPENSE)
                )
                .fetchOne();
    }

    public Long calculateTotalIncome(String bankBookNum) {
        QRecordCopy record = QRecordCopy.recordCopy;

        return queryFactory.select(record.money.sum())
                .from(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        record.recordState.eq(RecordState.INCOME)
                )
                .fetchOne();
    }
}
