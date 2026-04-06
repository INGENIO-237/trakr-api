package com._naptic.trakr_api.transactions.dtos;

import com._naptic.trakr_api.shared.dtos.PaginatedRequest;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.time.LocalDate;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionQuery extends PaginatedRequest {
    private TransactionPeriod period;

    private Integer months;

    private LocalDate from;

    private LocalDate to;
}
