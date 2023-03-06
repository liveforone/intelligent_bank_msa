package intelligent_bank_msa.recordservice.controller;

import intelligent_bank_msa.recordservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.recordservice.dto.RecordResponse;
import intelligent_bank_msa.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/my-record/{bankBookNum}")
    @LogExecutionTime
    public ResponseEntity<?> getMyRecord(
            @PathVariable String bankBookNum,
            @RequestParam(name = "lastId") Long lastId,
            @RequestParam(name = "pageSize") int pageSize
    ) {
        List<RecordResponse> records = recordService.getRecordsByBankBookNum(bankBookNum, lastId, pageSize);

        return ResponseEntity.ok(records);
    }
}
