package intelligent_bank_msa.calculateservice.controller;

import intelligent_bank_msa.calculateservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.calculateservice.dto.calculate.CalculateResponse;
import intelligent_bank_msa.calculateservice.service.CalculateService;
import intelligent_bank_msa.calculateservice.utility.CommonUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
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

    @GetMapping("/month/{bankBookNum}")
    @LogExecutionTime
    public ResponseEntity<?> calculateThisMonth(@PathVariable String bankBookNum) {
        CalculateResponse calculateThisMonth = calculateService.calculateThisMonth(bankBookNum);
        return ResponseEntity.ok(calculateThisMonth);
    }

    @GetMapping("/year/{bankBookNum}")
    @LogExecutionTime
    public ResponseEntity<?> calculateThisYear(@PathVariable String bankBookNum) {
        CalculateResponse calculateThisYear = calculateService.calculateThisYear(bankBookNum);
        return ResponseEntity.ok(calculateThisYear);
    }

    @GetMapping("/total/{bankBookNum}")
    @LogExecutionTime
    public ResponseEntity<?> calculateTotal(@PathVariable String bankBookNum) {
        CalculateResponse calculateTotal = calculateService.calculateTotal(bankBookNum);
        return ResponseEntity.ok(calculateTotal);
    }

    @PostMapping("/interest/{bankBookNum}")
    public ResponseEntity<?> calculateInterest(@PathVariable String bankBookNum) {
        if (!CommonUtils.createNowMonth().equals(Month.DECEMBER)) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("이자 신청은 12월에만 가능합니다.");
        }

        calculateService.addInterest(bankBookNum);
        log.info("이자 지금 완료");

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body("이자 지급이 완료되었습니다.");
    }
}
