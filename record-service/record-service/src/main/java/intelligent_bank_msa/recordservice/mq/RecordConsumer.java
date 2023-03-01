package intelligent_bank_msa.recordservice.mq;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import intelligent_bank_msa.recordservice.dto.RecordRequest;
import intelligent_bank_msa.recordservice.mq.constant.KafkaLog;
import intelligent_bank_msa.recordservice.mq.constant.Topic;
import intelligent_bank_msa.recordservice.repository.RecordRepository;
import intelligent_bank_msa.recordservice.utility.RecordMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RecordConsumer {

    private final RecordRepository recordRepository;
    private final RecordProducer recordProducer;

    @KafkaListener(topics = Topic.REQUEST_SAVE_RECORD)
    public void requestSaveRecord(String kafkaMessage) throws JsonProcessingException {
        log.info(KafkaLog.KAFKA_RECEIVE_LOG + kafkaMessage);

        ObjectMapper objectMapper = new ObjectMapper();
        RecordRequest recordRequest = objectMapper.readValue(kafkaMessage, RecordRequest.class);

        recordRepository.save(RecordMapper.dtoToEntity(recordRequest));
        recordProducer.requestSaveCopyRecord(recordRequest);
    }
}
