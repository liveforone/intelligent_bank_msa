package intelligent_bank_msa.recordservice.utility;

import intelligent_bank_msa.recordservice.dto.RecordRequest;
import intelligent_bank_msa.recordservice.dto.RecordResponse;
import intelligent_bank_msa.recordservice.model.Record;
import org.springframework.data.domain.Page;

public class RecordMapper {

    public static Record dtoToEntity(RecordRequest recordRequest) {
        return Record.builder()
                .id(recordRequest.getId())
                .title(recordRequest.getTitle())
                .bankBookNum(recordRequest.getBankBookNum())
                .money(recordRequest.getMoney())
                .recordState(recordRequest.getRecordState())
                .createdYear(recordRequest.getCreatedYear())
                .createdMonth(recordRequest.getCreatedMonth())
                .build();
    }

    private static RecordResponse dtoBuilder(Record record) {
        return RecordResponse.builder()
                .id(record.getId())
                .title(record.getTitle())
                .money(record.getMoney())
                .recordState(record.getRecordState())
                .createdDate(record.getCreatedDate())
                .build();
    }

    public static Page<RecordResponse> entityToDtoPage(Page<Record> records) {
        return records.map(RecordMapper::dtoBuilder);
    }
}
