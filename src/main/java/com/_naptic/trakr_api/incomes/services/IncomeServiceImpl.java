package com._naptic.trakr_api.incomes.services;

import com._naptic.trakr_api.auth.services.CurrentUserService;
import com._naptic.trakr_api.incomes.IncomeMapper;
import com._naptic.trakr_api.incomes.IncomeRepository;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.models.Income;
import com._naptic.trakr_api.incomes.models.IncomeSource;
import com._naptic.trakr_api.shared.exceptions.common.BadRequestException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class IncomeServiceImpl implements IncomeService {
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
}
