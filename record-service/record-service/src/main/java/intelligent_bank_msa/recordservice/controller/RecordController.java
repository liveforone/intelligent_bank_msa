package intelligent_bank_msa.recordservice.controller;

import intelligent_bank_msa.recordservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.recordservice.controller.constant.ParamConstant;
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

    @GetMapping(RecordUrl.MY_RECORD)
    @LogExecutionTime
    public ResponseEntity<?> getMyRecord(
            @PathVariable String bankBookNum,
            @RequestParam(name = ParamConstant.LAST_ID) Long lastId,
            @RequestParam(name = ParamConstant.PAGE_SIZE) int pageSize
    ) {
        List<RecordResponse> records = recordService.getRecordsByBankBookNum(bankBookNum, lastId, pageSize);

        return ResponseEntity.ok(records);
    }
}
