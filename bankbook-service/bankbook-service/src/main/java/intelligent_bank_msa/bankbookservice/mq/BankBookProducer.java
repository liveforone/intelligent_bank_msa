package intelligent_bank_msa.bankbookservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.bankbookservice.dto.BankBookResponse;
import intelligent_bank_msa.bankbookservice.dto.KafkaErrorDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankBookProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;

    public void sendErrorMessage(String topic, KafkaErrorDto kafkaErrorDto) {
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(kafkaErrorDto);

        kafkaTemplate.send(topic, jsonOrder);
        log.info("BankBook Producer send Error Message");
    }

    public void sendBankBookDetail(String topic, BankBookResponse bankBookResponse) {
        Gson gson = new Gson();
        String jsonOrder = gson.toJson(bankBookResponse);

        kafkaTemplate.send(topic, jsonOrder);
        log.info("BankBook Producer send BankBook Detail data");
    }
}
