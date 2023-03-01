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

    private static final String KAFKA_RECEIVE_LOG = "Consumer receive Kafka Message -> ";

    private JsonObject parseKafkaMessage(String kafkaMessage) {
        return JsonParser
                .parseString(kafkaMessage)
                .getAsJsonObject();
    }

    private KafkaErrorDto makeErrorDto(String errorMessage) {
        return KafkaErrorDto.builder()
                .errorMessage(errorMessage)
                .build();
    }

    @KafkaListener(topics = Topic.REQUEST_BANKBOOK_DETAIL)
    public void requestBankBookDetail(String kafkaMessage) {
        log.info(KAFKA_RECEIVE_LOG + kafkaMessage);

        JsonObject jsonMessage = parseKafkaMessage(kafkaMessage);
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String errorMessage = "통장이 존재하지 않습니다.";
            KafkaErrorDto kafkaErrorDto = makeErrorDto(errorMessage);

            bankBookProducer.sendErrorMessage(
                    Topic.RESPONSE_BANKBOOK_DETAIL,
                    kafkaErrorDto
            );
        } else {
            bankBookProducer.sendBankBookDetail(
                    Topic.RESPONSE_BANKBOOK_DETAIL,
                    BankBookMapper.entityToDtoDetail(bankBook)
            );
        }
    }

    @KafkaListener(topics = Topic.INCREASE_BALANCE)
    public void increaseBalance(String kafkaMessage) {
        log.info(KAFKA_RECEIVE_LOG + kafkaMessage);

        JsonObject jsonMessage = parseKafkaMessage(kafkaMessage);
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();
        long inputMoney = jsonMessage.get("inputMoney").getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String errorMessage = "통장이 존재하지 않습니다.";
            KafkaErrorDto kafkaErrorDto = makeErrorDto(errorMessage);

            bankBookProducer.sendErrorMessage(Topic.INCREASE_BALANCE, kafkaErrorDto);
        } else {
            bankBookRepository.increaseBalance(bankBookNum, inputMoney);
        }
    }

    @KafkaListener(topics = Topic.DECREASE_BALANCE)
    public void decreaseBalance(String kafkaMessage) {
        log.info(KAFKA_RECEIVE_LOG + kafkaMessage);

        JsonObject jsonMessage = parseKafkaMessage(kafkaMessage);
        String bankBookNum = jsonMessage.get("bankBookNum").getAsString();
        long inputMoney = jsonMessage.get("inputMoney").getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            String errorMessage = "통장이 존재하지 않습니다.";
            KafkaErrorDto kafkaErrorDto = makeErrorDto(errorMessage);

            bankBookProducer.sendErrorMessage(Topic.DECREASE_BALANCE, kafkaErrorDto);
        } else {
            bankBookRepository.decreaseBalance(bankBookNum, inputMoney);
        }
    }
}
