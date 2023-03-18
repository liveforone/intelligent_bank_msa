package intelligent_bank_msa.calculateservice.dto.record.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum RecordStatus {

    INTEREST("[입금] 연 이자 지급");

    private final String value;
}
