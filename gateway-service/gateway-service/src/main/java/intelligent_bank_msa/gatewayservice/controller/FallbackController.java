package intelligent_bank_msa.gatewayservice.controller;

import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping(FallbackUrl.BASE)
public class FallbackController {

    @GetMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserGet() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PostMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPost() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @PatchMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserPatch() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @DeleteMapping(FallbackUrl.USER)
    public Mono<String> fallbackUserDelete() {
        return Mono.just(FallbackMessage.USER_LOG);
    }

    @GetMapping(FallbackUrl.BANKBOOK)
    public Mono<String> fallbackBankbookGet() {
        return Mono.just(FallbackMessage.BANKBOOK_LOG);
    }

    @PostMapping(FallbackUrl.BANKBOOK)
    public Mono<String> fallbackBankbookPost() {
        return Mono.just(FallbackMessage.BANKBOOK_LOG);
    }

    @PatchMapping(FallbackUrl.BANKBOOK)
    public Mono<String> fallbackBankbookPatch() {
        return Mono.just(FallbackMessage.BANKBOOK_LOG);
    }

    @GetMapping(FallbackUrl.RECORD)
    public Mono<String> fallbackRecord() {
        return Mono.just(FallbackMessage.RECORD_LOG);
    }

    @PostMapping(FallbackUrl.ATM)
    public Mono<String> fallbackAtm() {
        return Mono.just(FallbackMessage.ATM_LOG);
    }

    @PostMapping(FallbackUrl.REMIT)
    public Mono<String> fallbackRemit() {
        return Mono.just(FallbackMessage.REMIT_LOG);
    }

    @GetMapping(FallbackUrl.CALCULATE)
    public Mono<String> fallbackCalculateGet() {
        return Mono.just(FallbackMessage.CALCULATE_LOG);
    }

    @PostMapping(FallbackUrl.CALCULATE)
    public Mono<String> fallbackCalculatePost() {
        return Mono.just(FallbackMessage.CALCULATE_LOG);
    }
}
