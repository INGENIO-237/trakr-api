package com._naptic.trakr_api.transactions.services;

import com._naptic.trakr_api.shared.exceptions.common.BadRequestException;
import com._naptic.trakr_api.transactions.dtos.TransactionPeriod;
import com._naptic.trakr_api.transactions.dtos.TransactionQuery;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.YearMonth;

public class TransactionService {
    protected LocalDateTime[] resolveRange(TransactionQuery query) {
        LocalDate now = LocalDate.now();

        if (query.getPeriod() == TransactionPeriod.CUSTOM || query.getPeriod() == null) {
            if (query.getFrom() == null || query.getTo() == null)
                throw new BadRequestException("Both 'from' and 'to' are required for a custom period");
            return new LocalDateTime[]{query.getFrom().atStartOfDay(), query.getTo().atTime(23, 59, 59)};
        }

        LocalDate from = switch (query.getPeriod()) {
            case THIS_MONTH -> now.withDayOfMonth(1);
            case LAST_MONTH -> now.minusMonths(1).withDayOfMonth(1);
            case LAST_3_MONTHS -> now.minusMonths(3).withDayOfMonth(1);
            case LAST_6_MONTHS -> now.minusMonths(6).withDayOfMonth(1);
            case THIS_YEAR -> now.withDayOfYear(1);
            case LAST_N_MONTHS -> {
                if (query.getMonths() == null || query.getMonths() < 1)
                    throw new BadRequestException("'months' must be a positive number when period is LAST_N_MONTHS");
                yield now.minusMonths(query.getMonths()).withDayOfMonth(1);
            }
            default -> throw new BadRequestException("Unsupported period: " + query.getPeriod());
        };

        LocalDate to = query.getPeriod() == TransactionPeriod.LAST_MONTH
                ? YearMonth.from(now.minusMonths(1)).atEndOfMonth()
                : now;

        return new LocalDateTime[]{from.atStartOfDay(), to.atTime(23, 59, 59)};
    }
}
