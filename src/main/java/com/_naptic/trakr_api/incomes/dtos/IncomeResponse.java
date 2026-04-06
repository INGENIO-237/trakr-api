package com._naptic.trakr_api.incomes.dtos;

import com._naptic.trakr_api.incomes.models.IncomeSource;
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
public class IncomeResponse extends TransactionResponse {
    private IncomeSource source;
}
