package intelligent_bank_msa.remitservice.feign;

import intelligent_bank_msa.remitservice.dto.feign.BankInfoRemitDto;
import intelligent_bank_msa.remitservice.dto.remit.RemitRequest;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "bankbook-service")
public interface BankBookFeignService {

    @PostMapping("/bank-info/remit")
    BankInfoRemitDto getBankBook(@RequestBody RemitRequest remitRequest);
}
