package intelligent_bank_msa.calculateservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_bank_msa.calculateservice.dto.kafka_error.KafkaErrorDto;
import intelligent_bank_msa.calculateservice.dto.record.RecordRequest;
import intelligent_bank_msa.calculateservice.mq.constant.KafkaLog;
import intelligent_bank_msa.calculateservice.mq.constant.Topic;
import intelligent_bank_msa.calculateservice.repository.RecordCopyRepository;
import intelligent_bank_msa.calculateservice.utility.CommonUtils;
import intelligent_bank_msa.calculateservice.utility.RecordCopyMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class CalculateConsumer {

    private final RecordCopyRepository recordCopyRepository;
    private final CalculateProducer calculateProducer;

    private KafkaErrorDto makeErrorNoRecord() {
        return KafkaErrorDto.builder()
                .errorMessage(KafkaLog.ERROR_NO_RECORD)
                .build();
    }

    @KafkaListener(topics = Topic.REQUEST_SAVE_COPY_RECORD)
    @Transactional
    public void requestSaveCopyRecord(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG + kafkaMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        RecordRequest recordRequest = objectMapper.readValue(kafkaMessage, RecordRequest.class);

        if (CommonUtils.isNull(recordRequest)) {
            calculateProducer.sendNoRecordError(Topic.RESPONSE_SAVE_RECORD, makeErrorNoRecord());
        } else {
            recordCopyRepository.save(RecordCopyMapper.dtoToEntity(recordRequest));
        }
    }
}
