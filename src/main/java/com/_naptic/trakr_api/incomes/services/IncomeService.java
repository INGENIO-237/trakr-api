package com._naptic.trakr_api.incomes.services;

import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;

public interface IncomeService {
    IncomeResponse register(RegisterIncomeDto dto);
}
