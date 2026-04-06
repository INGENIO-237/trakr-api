package com._naptic.trakr_api.expenses;

import com._naptic.trakr_api.expenses.models.Expense;
import com._naptic.trakr_api.transactions.TransactionRepository;

public interface ExpenseRepository extends TransactionRepository<Expense> {
}
