package intelligent_bank_msa.remitservice.service;

import intelligent_bank_msa.remitservice.dto.record.RecordRequest;
import intelligent_bank_msa.remitservice.dto.record.RecordState;
import intelligent_bank_msa.remitservice.dto.record.RecordStatus;
import intelligent_bank_msa.remitservice.dto.remit.RemitRequest;
import intelligent_bank_msa.remitservice.mq.RemitProducer;
import intelligent_bank_msa.remitservice.service.constant.ServiceLog;
import intelligent_bank_msa.remitservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class RemitService {

    private final RemitProducer remitProducer;

    public void remit(RemitRequest remitRequest) {
        remitAndRecordForSender(remitRequest);
        remitAndRecordForReceiver(remitRequest);
    }

    private void remitAndRecordForSender(RemitRequest remitRequest) {
        long inputMoney = remitRequest.getInputMoney();
        String senderBankBookNum = remitRequest.getSenderBankBookNum();
        String receiverBankBookNum = remitRequest.getReceiverBankBookNum();


        remitProducer.requestDecreaseBalance(remitRequest);
        log.info(ServiceLog.BANK_NUM.getValue() + senderBankBookNum +
                ServiceLog.WITHDRAW_SUM.getValue() + inputMoney);

        RecordRequest senderRequest = RecordRequest.builder()
                .title(RecordStatus.WITHDRAW_REMIT.getValue() + receiverBankBookNum)
                .bankBookNum(senderBankBookNum)
                .money(-inputMoney)
                .recordState(RecordState.EXPENSE)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        remitProducer.requestSaveRecord(senderRequest);
    }

    private void remitAndRecordForReceiver(RemitRequest remitRequest) {
        long inputMoney = remitRequest.getInputMoney();
        String senderBankBookNum = remitRequest.getSenderBankBookNum();
        String receiverBankBookNum = remitRequest.getReceiverBankBookNum();

        remitProducer.requestIncreaseBalance(remitRequest);
        log.info(ServiceLog.BANK_NUM.getValue() + receiverBankBookNum +
                ServiceLog.DEPOSIT_SUM.getValue() + inputMoney);

        RecordRequest receiverRequest = RecordRequest.builder()
                .title(RecordStatus.DEPOSIT_REMIT.getValue() + senderBankBookNum)
                .bankBookNum(receiverBankBookNum)
                .money(inputMoney)
                .recordState(RecordState.INCOME)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        remitProducer.requestSaveRecord(receiverRequest);
    }
}
