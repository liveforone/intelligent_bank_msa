package intelligent_bank_msa.calculateservice.service.util;


import intelligent_bank_msa.calculateservice.dto.calculate.CalculateResponse;

public class CalculateMapper {

    public static CalculateResponse dtoBuilder(Long sumExpense, Long sumIncome) {
        return CalculateResponse.builder()
                .expense((sumExpense == null) ? 0 : sumExpense)
                .income((sumIncome == null) ? 0 : sumIncome)
                .build();
    }
}
