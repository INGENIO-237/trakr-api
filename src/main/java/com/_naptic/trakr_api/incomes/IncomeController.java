package com._naptic.trakr_api.incomes;

import com._naptic.trakr_api.incomes.dtos.GetIncomeQuery;
import com._naptic.trakr_api.incomes.dtos.IncomeResponse;
import com._naptic.trakr_api.incomes.dtos.RegisterIncomeDto;
import com._naptic.trakr_api.incomes.services.IncomeService;
import com._naptic.trakr_api.shared.responses.CustomApiResponse;
import com._naptic.trakr_api.shared.responses.PaginatedApiResponse;
import com._naptic.trakr_api.shared.responses.WrappedResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@Tag(name = "Incomes")
@RequestMapping("incomes")
public class IncomeController {
    private final IncomeService service;

    @PostMapping
    @Operation(summary = "Register an income")
    public ResponseEntity<CustomApiResponse<IncomeResponse>> register(@Valid @RequestBody RegisterIncomeDto dto) {
        IncomeResponse response = service.register(dto);

        return WrappedResponse.of(response, "Expense registered successfully", HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve a paginated list of incomes")
    public ResponseEntity<PaginatedApiResponse<IncomeResponse>> findAll(@ModelAttribute GetIncomeQuery query) {
        Page<IncomeResponse> page = service.findAll(query);

        return WrappedResponse.of(page, "Incomes retrieved successfully");
    }
}
