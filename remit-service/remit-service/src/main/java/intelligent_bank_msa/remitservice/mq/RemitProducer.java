package intelligent_bank_msa.remitservice.mq;

import com.google.gson.Gson;
import intelligent_bank_msa.remitservice.dto.record.RecordRequest;
import intelligent_bank_msa.remitservice.dto.remit.BalanceRequest;
import intelligent_bank_msa.remitservice.dto.remit.RemitRequest;
import intelligent_bank_msa.remitservice.mq.constant.Topic;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemitProducer {

    private final KafkaTemplate<String, String> kafkaTemplate;
    Gson gson = new Gson();

    public void requestIncreaseBalance(RemitRequest remitRequest) {
        BalanceRequest balanceRequest = BalanceRequest.builder()
                .inputMoney(remitRequest.getInputMoney())
                .bankBookNum(remitRequest.getReceiverBankBookNum())
                .build();
        String jsonOrder = gson.toJson(balanceRequest);

        kafkaTemplate.send(Topic.REQUEST_INCREASE_BALANCE, jsonOrder);
    }

    public void requestDecreaseBalance(RemitRequest remitRequest) {
        BalanceRequest balanceRequest = BalanceRequest.builder()
                .inputMoney(remitRequest.getInputMoney())
                .bankBookNum(remitRequest.getSenderBankBookNum())
                .build();
        String jsonOrder = gson.toJson(balanceRequest);

        kafkaTemplate.send(Topic.REQUEST_DECREASE_BALANCE, jsonOrder);
    }

    public void requestSaveRecord(RecordRequest request) {
        String jsonOrder = gson.toJson(request);

        kafkaTemplate.send(Topic.REQUEST_SAVE_RECORD, jsonOrder);
    }
}
