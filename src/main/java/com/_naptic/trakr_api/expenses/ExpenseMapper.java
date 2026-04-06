package com._naptic.trakr_api.expenses;

import com._naptic.trakr_api.expenses.dtos.ExpenseResponse;
import com._naptic.trakr_api.expenses.dtos.RegisterExpenseDto;
import com._naptic.trakr_api.expenses.models.Expense;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.springframework.data.domain.Page;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE, nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface ExpenseMapper {
    Expense toEntity(RegisterExpenseDto dto);

    ExpenseResponse toResponse(Expense income);

    default Page<ExpenseResponse> toResponses(Page<Expense> page) {
        return  page.map(this::toResponse);
    }
}
