package intelligent_bank_msa.calculateservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.calculateservice.dto.bankbook.InterestRequest;
import intelligent_bank_msa.calculateservice.dto.record.RecordRequest;
import intelligent_bank_msa.calculateservice.mq.constant.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    public void requestIncreaseBalance(InterestRequest interestRequest) {
        String jsonOrder = gson.toJson(interestRequest);

        kafkaTemplate.send(Topic.REQUEST_INCREASE_BALANCE, jsonOrder);
    }

    public void requestSaveRecord(RecordRequest request) {
        String jsonOrder = gson.toJson(request);

        kafkaTemplate.send(Topic.REQUEST_SAVE_RECORD, jsonOrder);
    }
}
