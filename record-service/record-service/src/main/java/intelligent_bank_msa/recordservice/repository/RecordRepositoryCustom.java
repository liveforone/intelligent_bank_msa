package intelligent_bank_msa.recordservice.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import intelligent_bank_msa.recordservice.model.Record;

public interface RecordRepositoryCustom {
    Page<Record> findRecordsByBankBookNum(String bankBookNum, Pageable pageable);
}
