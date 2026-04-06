package com._naptic.trakr_api.expenses.dtos;

import com._naptic.trakr_api.expenses.models.ExpenseReason;
import com._naptic.trakr_api.transactions.dtos.TransactionResponse;
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
public class ExpenseResponse extends TransactionResponse {
    private ExpenseReason reason;
}
