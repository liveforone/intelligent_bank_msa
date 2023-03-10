package intelligent_bank_msa.calculateservice.dto.kafka_error;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class KafkaErrorDto {

    private String errorMessage;
}
