package intelligent_bank_msa.recordservice.service;

import intelligent_bank_msa.recordservice.dto.RecordResponse;
import intelligent_bank_msa.recordservice.repository.RecordRepository;
import intelligent_bank_msa.recordservice.utility.RecordMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class RecordService {

    private final RecordRepository recordRepository;

    public List<RecordResponse> getRecordsByBankBookNum(String bankBookNum, Long lastId, int pageSize) {
        return RecordMapper.entityToDtoList(
                recordRepository.findRecordsByBankBookNum(bankBookNum, lastId, pageSize)
        );
    }
}
