package intelligent_bank_msa.recordservice.dto;

import intelligent_bank_msa.recordservice.model.RecordState;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Month;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RecordRequest {

    private Long id;
    private String title;
    private String bankBookNum;
    private long money;
    private RecordState recordState;
    private int createdYear;
    private Month createdMonth;
}
