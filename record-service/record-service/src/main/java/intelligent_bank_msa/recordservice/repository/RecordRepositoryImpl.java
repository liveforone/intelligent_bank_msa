package intelligent_bank_msa.recordservice.repository;

import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank_msa.recordservice.domain.QRecord;
import intelligent_bank_msa.recordservice.domain.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;
    QRecord record = QRecord.record;

    public List<Record> findRecordsByBankBookNum(String bankBookNum, Long recordId, int pageSize) {
        return queryFactory.selectFrom(record)
                .where(
                        record.bankBookNum.eq(bankBookNum),
                        ltBookId(recordId)
                )
                .orderBy(record.id.desc())
                .limit(pageSize)
                .fetch();
    }

    private BooleanExpression ltBookId(Long recordId) {
        if (recordId == null) {
            return null;
        }

        return record.id.lt(recordId);
    }
}
