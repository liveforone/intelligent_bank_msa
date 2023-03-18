package intelligent_bank_msa.calculateservice.mq.util;

import intelligent_bank_msa.calculateservice.dto.record.RecordRequest;
import intelligent_bank_msa.calculateservice.model.RecordCopy;

public class RecordCopyMapper {

    public static RecordCopy dtoToEntity(RecordRequest recordRequest) {
        return RecordCopy.builder()
                .id(recordRequest.getId())
                .title(recordRequest.getTitle())
                .bankBookNum(recordRequest.getBankBookNum())
                .money(recordRequest.getMoney())
                .recordState(recordRequest.getRecordState())
                .createdYear(recordRequest.getCreatedYear())
                .createdMonth(recordRequest.getCreatedMonth())
                .build();
    }
}
