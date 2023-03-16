package intelligent_bank_msa.atmservice.service;

import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.record.RecordRequest;
import intelligent_bank_msa.atmservice.dto.record.constant.RecordState;
import intelligent_bank_msa.atmservice.mq.AtmProducer;
import intelligent_bank_msa.atmservice.service.constant.ServiceLog;
import intelligent_bank_msa.atmservice.utility.CommonUtils;
import intelligent_bank_msa.atmservice.dto.record.constant.RecordStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmService {

    private final AtmProducer atmProducer;

    public void depositAtm(AtmRequest atmRequest) {
        atmProducer.requestIncreaseBalance(atmRequest);

        String bankBookNum = atmRequest.getBankBookNum();
        long inputMoney = atmRequest.getInputMoney();

        log.info(ServiceLog.BANK_NUM.getValue() + bankBookNum +
                ServiceLog.DEPOSIT_SUM.getValue() + inputMoney);

        RecordRequest depositRequest = RecordRequest.builder()
                .title(RecordStatus.DEPOSIT_ATM.getValue())
                .bankBookNum(bankBookNum)
                .money(inputMoney)
                .recordState(RecordState.INCOME)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        atmProducer.requestSaveRecord(depositRequest);
    }

    public void withdrawAtm(AtmRequest atmRequest) {
        atmProducer.requestDecreaseBalance(atmRequest);

        String bankBookNum = atmRequest.getBankBookNum();
        long inputMoney = atmRequest.getInputMoney();

        log.info(ServiceLog.BANK_NUM.getValue() + bankBookNum +
                ServiceLog.WITHDRAW_SUM.getValue() + inputMoney);

        RecordRequest withdrawRequest = RecordRequest.builder()
                .title(RecordStatus.WITHDRAW_ATM.getValue())
                .bankBookNum(bankBookNum)
                .money(-inputMoney)
                .recordState(RecordState.EXPENSE)
                .createdYear(CommonUtils.createNowYear())
                .createdMonth(CommonUtils.createNowMonth())
                .build();
        atmProducer.requestSaveRecord(withdrawRequest);
    }
}
