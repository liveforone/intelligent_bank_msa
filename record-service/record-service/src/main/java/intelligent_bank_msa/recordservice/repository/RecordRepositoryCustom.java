package intelligent_bank_msa.recordservice.repository;

import intelligent_bank_msa.recordservice.model.Record;

import java.util.List;

public interface RecordRepositoryCustom {
    List<Record> findRecordsByBankBookNum(String bankBookNum, Long recordId, int pageSize);
}
