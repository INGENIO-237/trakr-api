package com._naptic.trakr_api.expenses.services;

import com._naptic.trakr_api.expenses.dtos.GetExpenseQuery;
import com._naptic.trakr_api.expenses.dtos.ExpenseResponse;
import com._naptic.trakr_api.expenses.dtos.RegisterExpenseDto;
import org.springframework.data.domain.Page;

public interface ExpenseService {
    ExpenseResponse register(RegisterExpenseDto dto);

    Page<ExpenseResponse> findAll(GetExpenseQuery query);
}
