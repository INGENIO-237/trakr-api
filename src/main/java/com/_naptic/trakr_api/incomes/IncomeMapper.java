package com._naptic.trakr_api.incomes;

import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.models.Income;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IncomeMapper {
    Income toEntity(RegisterIncomeDto dto);

    IncomeResponse toResponse(Income income);
}
