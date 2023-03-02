package intelligent_bank_msa.calculateservice.service;

import intelligent_bank_msa.calculateservice.dto.bankbook.InterestRequest;
import intelligent_bank_msa.calculateservice.dto.calculate.CalculateResponse;
import intelligent_bank_msa.calculateservice.dto.record.RecordRequest;
import intelligent_bank_msa.calculateservice.model.RecordState;
import intelligent_bank_msa.calculateservice.mq.CalculateProducer;
import intelligent_bank_msa.calculateservice.repository.CalculateRepository;
import intelligent_bank_msa.calculateservice.utility.CalculateMapper;
import intelligent_bank_msa.calculateservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.Month;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional(readOnly = true)
public class CalculateService {

    private final CalculateRepository calculateRepository;
    private final CalculateProducer calculateProducer;

    public CalculateResponse calculateThisMonth(String bankBookNum) {
        int nowYear = CommonUtils.createNowYear();
        Month nowMonth = CommonUtils.createNowMonth();

        Long sumExpense = calculateRepository.calculateThisMonthExpense(
                bankBookNum,
                nowYear,
                nowMonth
        );
        Long sumIncome = calculateRepository.calculateThisMonthIncome(
                bankBookNum,
                nowYear,
                nowMonth
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateThisYear(String bankBookNum) {
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = calculateRepository.calculateThisYearExpense(
                bankBookNum,
                nowYear
        );
        Long sumIncome = calculateRepository.calculateThisYearIncome(
                bankBookNum,
                nowYear
        );

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    public CalculateResponse calculateTotal(String bankBookNum) {
        Long sumExpense = calculateRepository.calculateTotalExpense(bankBookNum);
        Long sumIncome = calculateRepository.calculateTotalIncome(bankBookNum);

        return CalculateMapper.dtoBuilder(sumExpense, sumIncome);
    }

    @Transactional
    public void addInterest(String bankBookNum) {
        int nowYear = CommonUtils.createNowYear();

        Long sumExpense = calculateRepository.calculateThisYearExpense(
                bankBookNum,
                nowYear
        );
        Long sumIncome = calculateRepository.calculateThisYearIncome(
                bankBookNum,
                nowYear
        );

        long profit =  sumIncome + sumExpense;

        if (profit > 0) {
            long interest = (long) (profit * 0.01);

            InterestRequest interestRequest = InterestRequest.makeInterestRequest(
                    bankBookNum,
                    interest
            );
            calculateProducer.requestIncreaseBalance(interestRequest);
            log.info("이자 정산 완료");

            RecordRequest depositRequest = RecordRequest.builder()
                    .title("[입금] 연 이자 지급")
                    .bankBookNum(bankBookNum)
                    .money(interest)
                    .recordState(RecordState.INCOME)
                    .createdYear(CommonUtils.createNowYear())
                    .createdMonth(CommonUtils.createNowMonth())
                    .build();
            calculateProducer.requestSaveRecord(depositRequest);
        }
    }
}
