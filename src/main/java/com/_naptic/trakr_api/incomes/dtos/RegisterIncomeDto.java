package com._naptic.trakr_api.incomes.dtos;

import com._naptic.trakr_api.incomes.models.IncomeSource;
import com._naptic.trakr_api.shared.validators.enums.IsEnum;
import com._naptic.trakr_api.transactions.dtos.RegisterTransactionDto;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class RegisterIncomeDto extends RegisterTransactionDto {
    @NotNull(message = "Expense source type is required")
    @IsEnum(enumClass = IncomeSource.class, message = "Invalid income source type provided")
    private String source;

    private String description;
}
