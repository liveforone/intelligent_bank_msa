package intelligent_bank_msa.bankbookservice.mq;

import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import intelligent_bank_msa.bankbookservice.dto.kafka.KafkaErrorDto;
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

    private KafkaErrorDto returnError(String errorLog) {
        return KafkaErrorDto.builder()
                .errorMessage(errorLog)
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
            bankBookProducer.sendError(
                    Topic.RESPONSE_INCREASE_BALANCE,
                    returnError(KafkaLog.ERROR_NO_BANKBOOK)
            );
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
            bankBookProducer.sendError(
                    Topic.RESPONSE_DECREASE_BALANCE,
                    returnError(KafkaLog.ERROR_NO_BANKBOOK)
            );
        } else {
            bankBookRepository.decreaseBalance(bankBookNum, inputMoney);
        }
    }
}
