package intelligent_bank_msa.remitservice.client;

import intelligent_bank_msa.remitservice.dto.bankbook.BankBookResponse;
import intelligent_bank_msa.remitservice.dto.bankbook.PasswordCheckRequest;
import intelligent_bank_msa.remitservice.dto.bankbook.PasswordCheckResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bankbook-service")
public interface BankBookServiceClient {

    @PostMapping("/bank-info/{bankBookNum}")
    BankBookResponse getBankBookByBankBookNum(@PathVariable("bankBookNum") String bankBookNum);

    @PostMapping("/password/check")
    PasswordCheckResponse checkBankPassword(@RequestBody PasswordCheckRequest passwordCheckRequest);
}
