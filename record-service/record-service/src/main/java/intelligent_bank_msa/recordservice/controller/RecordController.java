package intelligent_bank_msa.recordservice.controller;

import intelligent_bank_msa.recordservice.aop.stopwatch.LogExecutionTime;
import intelligent_bank_msa.recordservice.dto.RecordResponse;
import intelligent_bank_msa.recordservice.service.RecordService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.data.web.SortDefault;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class RecordController {

    private final RecordService recordService;

    @GetMapping("/my-record/{bankBookNum}")
    @LogExecutionTime
    public ResponseEntity<?> getMyRecord(
            @PageableDefault(page = 0, size = 10)
            @SortDefault.SortDefaults({
                    @SortDefault(sort = "id", direction = Sort.Direction.DESC)
            }) Pageable pageable,
            @PathVariable String bankBookNum
    ) {
        Page<RecordResponse> records = recordService.getRecordsByBankBookNum(bankBookNum, pageable);

        return ResponseEntity.ok(records);
    }
}
