package intelligent_bank_msa.atmservice.service;

import intelligent_bank_msa.atmservice.dto.atm.AtmRequest;
import intelligent_bank_msa.atmservice.dto.record.RecordRequest;
import intelligent_bank_msa.atmservice.dto.record.RecordState;
import intelligent_bank_msa.atmservice.mq.AtmProducer;
import intelligent_bank_msa.atmservice.utility.CommonUtils;
import intelligent_bank_msa.atmservice.dto.record.RecordStatus;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class AtmService {

    private final AtmProducer atmProducer;

    @Transactional
    public void depositAtm(AtmRequest atmRequest) {
        atmProducer.requestIncreaseBalance(atmRequest);

        String bankBookNum = atmRequest.getBankBookNum();
        long inputMoney = atmRequest.getInputMoney();

        log.info("통장 번호 : " + bankBookNum + " ATM 입금 금액 : " + inputMoney);

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

    @Transactional
    public void withdrawAtm(AtmRequest atmRequest) {
        atmProducer.requestDecreaseBalance(atmRequest);

        String bankBookNum = atmRequest.getBankBookNum();
        long inputMoney = atmRequest.getInputMoney();

        log.info("통장 번호 : " + bankBookNum + " ATM 출금 금액 : " + inputMoney);

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
