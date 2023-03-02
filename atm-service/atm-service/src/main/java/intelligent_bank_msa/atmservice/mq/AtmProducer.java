package intelligent_bank_msa.atmservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.record.RecordRequest;
import intelligent_bank_msa.atmservice.mq.constant.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    public void requestIncreaseBalance(AtmRequest atmRequest) {
        String jsonOrder = gson.toJson(atmRequest);

        kafkaTemplate.send(Topic.REQUEST_INCREASE_BALANCE, jsonOrder);
    }

    public void requestDecreaseBalance(AtmRequest atmRequest) {
        String jsonOrder = gson.toJson(atmRequest);

        kafkaTemplate.send(Topic.REQUEST_DECREASE_BALANCE, jsonOrder);
    }

    public void requestSaveRecord(RecordRequest request) {
        String jsonOrder = gson.toJson(request);

        kafkaTemplate.send(Topic.REQUEST_SAVE_RECORD, jsonOrder);
    }
}
