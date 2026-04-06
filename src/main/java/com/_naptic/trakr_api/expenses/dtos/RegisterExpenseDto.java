package com._naptic.trakr_api.expenses.dtos;

import com._naptic.trakr_api.expenses.models.ExpenseReason;
import com._naptic.trakr_api.shared.validators.enums.IsEnum;
import com._naptic.trakr_api.transactions.dtos.RegisterTransactionDto;
import jakarta.validation.constraints.NotBlank;
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
public class RegisterExpenseDto extends RegisterTransactionDto {
    @NotNull(message = "Expense reason type is required")
    @IsEnum(enumClass = ExpenseReason.class, message = "Invalid expense reason type provided")
    private String reason;

    @NotBlank(message = "Expense description is required")
    private String description;
}
