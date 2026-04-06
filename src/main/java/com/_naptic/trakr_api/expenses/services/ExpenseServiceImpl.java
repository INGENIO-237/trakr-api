package com._naptic.trakr_api.expenses.services;

import com._naptic.trakr_api.auth.services.CurrentUserService;
import com._naptic.trakr_api.expenses.ExpenseMapper;
import com._naptic.trakr_api.expenses.ExpenseRepository;
import com._naptic.trakr_api.expenses.dtos.ExpenseResponse;
import com._naptic.trakr_api.expenses.dtos.GetExpenseQuery;
import com._naptic.trakr_api.expenses.dtos.RegisterExpenseDto;
import com._naptic.trakr_api.expenses.models.Expense;
import com._naptic.trakr_api.transactions.services.TransactionService;
import com._naptic.trakr_api.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl extends TransactionService implements ExpenseService {
    private final ExpenseMapper mapper;
    private final ExpenseRepository repository;
    private final CurrentUserService currentUserService;

    @Override
    public ExpenseResponse register(RegisterExpenseDto dto) {
        Expense entity = mapper.toEntity(dto);

        entity.setUser(currentUserService.getUser());
        Expense savedExpense = repository.save(entity);

        return mapper.toResponse(savedExpense);
    }

    @Override
    public Page<ExpenseResponse> findAll(GetExpenseQuery query) {
        User user = currentUserService.getUser();
        Pageable pageable = query.toPageable();

        if (query.getPeriod() == null && query.getFrom() == null) {
            return mapper.toResponses(repository.findByUser(user, pageable));
        }

        LocalDateTime[] range = resolveRange(query);
        return mapper.toResponses(repository.findByUserAndCreatedAtBetween(user, range[0], range[1], pageable));
    }
}
