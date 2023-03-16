package intelligent_bank_msa.atmservice.controller.constant;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum ControllerLog {

    ATM_CIRCUIT("atm-service-circuit"),
    DEPOSIT_SUCCESS("ATM 입금 성공"),
    WITHDRAW_SUCCESS("ATM 출금 성공");

    private final String value;
}
