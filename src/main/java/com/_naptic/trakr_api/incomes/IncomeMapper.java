package com._naptic.trakr_api.incomes;

import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.models.Income;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface IncomeMapper {
    Income toEntity(RegisterIncomeDto dto);

    IncomeResponse toResponse(Income income);

    default Page<IncomeResponse> toResponses(Page<Income> page) {
        return  page.map(this::toResponse);
    }
}
