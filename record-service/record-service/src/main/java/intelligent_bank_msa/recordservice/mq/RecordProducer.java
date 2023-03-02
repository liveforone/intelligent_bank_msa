package intelligent_bank_msa.recordservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.recordservice.dto.RecordRequest;
import intelligent_bank_msa.recordservice.mq.constant.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    public void requestSaveCopyRecord(RecordRequest recordRequest) {
        String jsonOrder = gson.toJson(recordRequest);

        kafkaTemplate.send(Topic.REQUEST_SAVE_COPY_RECORD, jsonOrder);
    }
}
