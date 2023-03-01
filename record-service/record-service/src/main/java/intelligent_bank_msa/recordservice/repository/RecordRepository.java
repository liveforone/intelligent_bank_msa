package intelligent_bank_msa.recordservice.repository;

import intelligent_bank_msa.recordservice.model.Record;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RecordRepository extends JpaRepository<Record, Long>, RecordRepositoryCustom {
}
