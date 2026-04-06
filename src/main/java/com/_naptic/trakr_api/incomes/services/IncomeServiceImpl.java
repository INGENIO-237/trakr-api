package com._naptic.trakr_api.incomes.services;

import com._naptic.trakr_api.auth.services.CurrentUserService;
import com._naptic.trakr_api.incomes.IncomeMapper;
import com._naptic.trakr_api.incomes.IncomeRepository;
import com._naptic.trakr_api.incomes.dtos.GetIncomeQuery;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.models.Income;
import com._naptic.trakr_api.incomes.models.IncomeSource;
import com._naptic.trakr_api.shared.exceptions.common.BadRequestException;
import com._naptic.trakr_api.transactions.services.TransactionService;
import com._naptic.trakr_api.users.User;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl extends TransactionService implements IncomeService {
    private final IncomeMapper mapper;
    private final IncomeRepository repository;
    private final CurrentUserService currentUserService;

    @Override
    public IncomeResponse register(RegisterIncomeDto dto) {
        if (dto.getSource().equals(IncomeSource.OTHER.toString()) && dto.getDescription() == null) {
            throw new BadRequestException("When income source type is %s, a description is required".formatted(dto.getSource()));
        }

        Income entity = mapper.toEntity(dto);

        entity.setUser(currentUserService.getUser());
        Income savedIncome = repository.save(entity);

        return mapper.toResponse(savedIncome);
    }

    @Override
    public Page<IncomeResponse> findAll(GetIncomeQuery query) {
        User user = currentUserService.getUser();
        Pageable pageable = query.toPageable();

        if (query.getPeriod() == null && query.getFrom() == null) {
            return mapper.toResponses(repository.findByUser(user, pageable));
        }

        LocalDateTime[] range = resolveRange(query);
        return mapper.toResponses(repository.findByUserAndCreatedAtBetween(user, range[0], range[1], pageable));
    }
}
