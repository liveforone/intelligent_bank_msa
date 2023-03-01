package intelligent_bank_msa.recordservice.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import intelligent_bank_msa.recordservice.model.QRecord;
import intelligent_bank_msa.recordservice.model.Record;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class RecordRepositoryImpl implements RecordRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public Page<Record> findRecordsByBankBookNum(String bankBookNum, Pageable pageable) {
        QRecord record = QRecord.record;

        List<Record> content = queryFactory.selectFrom(record)
                .where(record.bankBookNum.eq(bankBookNum))
                .orderBy(record.id.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();

        return new PageImpl<>(content, pageable, content.size());
    }
}
