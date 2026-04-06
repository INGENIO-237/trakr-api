package com._naptic.trakr_api.expenses.models;

import com._naptic.trakr_api.transactions.Transaction;
import jakarta.persistence.Entity;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@Entity
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class Expense extends Transaction {
    public static final String ID_PREFIX = "xpn";

    private ExpenseReason reason;

    @Override
    protected String getIdPrefix() {
        return ID_PREFIX;
    }
}
