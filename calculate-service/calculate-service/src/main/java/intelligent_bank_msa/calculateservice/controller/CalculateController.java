package intelligent_bank_msa.calculateservice.controller;

import intelligent_bank_msa.calculateservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.calculateservice.controller.constant.ControllerLog;
import intelligent_bank_msa.calculateservice.controller.constant.PathVarConstant;
import intelligent_bank_msa.calculateservice.controller.restResponse.RestResponse;
import intelligent_bank_msa.calculateservice.dto.calculate.CalculateResponse;
import intelligent_bank_msa.calculateservice.service.CalculateService;
import intelligent_bank_msa.calculateservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Month;

@RestController
@RequiredArgsConstructor
@Slf4j
public class CalculateController {

    private final CalculateService calculateService;

    @GetMapping(CalculateUrl.CALCULATE_MONTH)
    @LogExecutionTime
    public ResponseEntity<CalculateResponse> calculateThisMonth(
            @PathVariable(PathVarConstant.BANKBOOK_NUM) String bankBookNum
    ) {
        CalculateResponse calculateThisMonth = calculateService.calculateThisMonth(bankBookNum);
        return ResponseEntity.ok(calculateThisMonth);
    }

    @GetMapping(CalculateUrl.CALCULATE_YEAR)
    @LogExecutionTime
    public ResponseEntity<CalculateResponse> calculateThisYear(
            @PathVariable(PathVarConstant.BANKBOOK_NUM) String bankBookNum
    ) {
        CalculateResponse calculateThisYear = calculateService.calculateThisYear(bankBookNum);
        return ResponseEntity.ok(calculateThisYear);
    }

    @GetMapping(CalculateUrl.CALCULATE_TOTAL)
    @LogExecutionTime
    public ResponseEntity<CalculateResponse> calculateTotal(
            @PathVariable(PathVarConstant.BANKBOOK_NUM) String bankBookNum
    ) {
        CalculateResponse calculateTotal = calculateService.calculateTotal(bankBookNum);
        return ResponseEntity.ok(calculateTotal);
    }

    @PostMapping(CalculateUrl.CALCULATE_INTEREST)
    public ResponseEntity<?> calculateInterest(
            @PathVariable(PathVarConstant.BANKBOOK_NUM) String bankBookNum
    ) {
        if (!CommonUtils.createNowMonth().equals(Month.DECEMBER)) {
            return RestResponse.cantRequestInterest();
        }

        calculateService.addInterest(bankBookNum);
        log.info(ControllerLog.CALCULATE_INTEREST_SUCCESS.getValue());

        return RestResponse.calculateInterestSuccess();
    }
}
