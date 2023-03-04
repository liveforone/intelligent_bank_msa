package intelligent_bank_msa.bankbookservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.bankbookservice.dto.kafka.KafkaErrorDto;
import intelligent_bank_msa.bankbookservice.mq.constant.KafkaLog;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankBookProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    public void sendError(String topic, KafkaErrorDto kafkaErrorDto) {
        String jsonOrder = gson.toJson(kafkaErrorDto);

        kafkaTemplate.send(topic, jsonOrder);
        log.info(KafkaLog.ERROR_NO_BANKBOOK);
    }
}
