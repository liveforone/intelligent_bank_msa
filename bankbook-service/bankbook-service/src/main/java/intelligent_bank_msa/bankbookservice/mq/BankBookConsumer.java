package intelligent_bank_msa.bankbookservice.mq;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import intelligent_bank_msa.bankbookservice.dto.kafka_error.KafkaErrorDto;
import intelligent_bank_msa.bankbookservice.model.BankBook;
import intelligent_bank_msa.bankbookservice.mq.constant.KafkaLog;
import intelligent_bank_msa.bankbookservice.mq.constant.KafkaMessage;
import intelligent_bank_msa.bankbookservice.mq.constant.Topic;
import intelligent_bank_msa.bankbookservice.repository.BankBookRepository;
import intelligent_bank_msa.bankbookservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class BankBookConsumer {

    private final BankBookRepository bankBookRepository;
    private final BankBookProducer bankBookProducer;

    private JsonObject parseKafkaMessage(String kafkaMessage) {
        return JsonParser
                .parseString(kafkaMessage)
                .getAsJsonObject();
    }

    private KafkaErrorDto makeErrorNoBankBook() {
        String NO_BANKBOOK_ERROR_MESSAGE = "통장이 존재하지 않습니다.";
        return KafkaErrorDto.builder()
                .errorMessage(NO_BANKBOOK_ERROR_MESSAGE)
                .build();
    }

    @KafkaListener(topics = Topic.REQUEST_INCREASE_BALANCE)
    @Transactional
    public void increaseBalance(String kafkaMessage) {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG + kafkaMessage);

        JsonObject jsonMessage = parseKafkaMessage(kafkaMessage);
        String bankBookNum = jsonMessage.get(KafkaMessage.BANKBOOK_NUM).getAsString();
        long inputMoney = jsonMessage.get(KafkaMessage.INPUT_MONEY).getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            bankBookProducer.sendNoBankBookError(Topic.RESPONSE_INCREASE_BALANCE, makeErrorNoBankBook());
        } else {
            bankBookRepository.increaseBalance(bankBookNum, inputMoney);
        }
    }

    @KafkaListener(topics = Topic.REQUEST_DECREASE_BALANCE)
    @Transactional
    public void decreaseBalance(String kafkaMessage) {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG + kafkaMessage);

        JsonObject jsonMessage = parseKafkaMessage(kafkaMessage);
        String bankBookNum = jsonMessage.get(KafkaMessage.BANKBOOK_NUM).getAsString();
        long inputMoney = jsonMessage.get(KafkaMessage.INPUT_MONEY).getAsLong();

        BankBook bankBook = bankBookRepository.findOneByBankBookNum(bankBookNum);

        if (CommonUtils.isNull(bankBook)) {
            bankBookProducer.sendNoBankBookError(Topic.RESPONSE_DECREASE_BALANCE, makeErrorNoBankBook());
        } else {
            bankBookRepository.decreaseBalance(bankBookNum, inputMoney);
        }
    }
}
