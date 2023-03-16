package intelligent_bank_msa.remitservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    REMIT_CIRCUIT("remit-service-circuit"),
    REMIT_SUCCESS("송금 완료");

    private final String value;
}
