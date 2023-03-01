package intelligent_bank_msa.bankbookservice.mq;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import intelligent_bank_msa.bankbookservice.dto.KafkaErrorDto;
import intelligent_bank_msa.bankbookservice.model.BankBook;
import intelligent_bank_msa.bankbookservice.repository.BankBookRepository;
import intelligent_bank_msa.bankbookservice.utility.BankBookMapper;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankBookConsumer {

    private final BankBookRepository bankBookRepository;
    private final BankBookProducer bankBookProducer;

    @KafkaListener(topics = "request-bankbook-detail")
    public void requestBankBookDetail(String kafkaMessage) {
        log.info("Consumer receive Kafka Message -> " + kafkaMessage);

        JsonObject jsonMessage = JsonParser
                .parseString(kafkaMessage)
                .getAsJsonObject();
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String topic = "request-bankbook-detail";
            String errorMessage = "통장이 존재하지 않습니다.";

            KafkaErrorDto kafkaErrorDto = KafkaErrorDto.builder()
                    .errorMessage(errorMessage)
                    .build();

            bankBookProducer.sendErrorMessage(topic, kafkaErrorDto);
        } else {
            bankBookProducer.sendBankBookDetail(BankBookMapper.entityToDtoDetail(bankBook));
        }
    }

    @KafkaListener(topics = "increase-balance")
    public void increaseBalance(String kafkaMessage) {
        log.info("Consumer receive Kafka Message -> " + kafkaMessage);

        JsonObject jsonMessage = JsonParser
                .parseString(kafkaMessage)
                .getAsJsonObject();
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();
        long inputMoney = jsonMessage.get("inputMoney").getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String topic = "request-bankbook-detail";
            String errorMessage = "통장이 존재하지 않습니다.";

            KafkaErrorDto kafkaErrorDto = KafkaErrorDto.builder()
                    .errorMessage(errorMessage)
                    .build();

            bankBookProducer.sendErrorMessage(topic, kafkaErrorDto);
        } else {
            bankBookRepository.increaseBalance(bankBookNum, inputMoney);
        }
    }

    @KafkaListener(topics = "decrease-balance")
    public void decreaseBalance(String kafkaMessage) {
        log.info("Consumer receive Kafka Message -> " + kafkaMessage);

        JsonObject jsonMessage = JsonParser
                .parseString(kafkaMessage)
                .getAsJsonObject();
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();
        long inputMoney = jsonMessage.get("inputMoney").getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String topic = "request-bankbook-detail";
            String errorMessage = "통장이 존재하지 않습니다.";

            KafkaErrorDto kafkaErrorDto = KafkaErrorDto.builder()
                    .errorMessage(errorMessage)
                    .build();

            bankBookProducer.sendErrorMessage(topic, kafkaErrorDto);
        } else {
            bankBookRepository.decreaseBalance(bankBookNum, inputMoney);
        }
    }
}
