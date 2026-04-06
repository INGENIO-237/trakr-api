package com._naptic.trakr_api.incomes.services;

import com._naptic.trakr_api.incomes.dtos.GetIncomeQuery;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import org.springframework.data.domain.Page;

public interface IncomeService {
    IncomeResponse register(RegisterIncomeDto dto);

    Page<IncomeResponse> findAll(GetIncomeQuery query);
}
