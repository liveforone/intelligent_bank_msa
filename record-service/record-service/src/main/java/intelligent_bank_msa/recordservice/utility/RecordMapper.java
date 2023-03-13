package intelligent_bank_msa.recordservice.utility;

import intelligent_bank_msa.recordservice.dto.RecordRequest;
import intelligent_bank_msa.recordservice.dto.RecordResponse;
import intelligent_bank_msa.recordservice.domain.Record;

import java.util.List;
import java.util.stream.Collectors;

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

    public static List<RecordResponse> entityToDtoList(List<Record> records) {
        return records
                .stream()
                .map(RecordMapper::dtoBuilder)
                .collect(Collectors.toList());
    }
}
