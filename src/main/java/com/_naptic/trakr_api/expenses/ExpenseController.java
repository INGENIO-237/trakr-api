package com._naptic.trakr_api.expenses;

import com._naptic.trakr_api.expenses.dtos.ExpenseResponse;
import com._naptic.trakr_api.expenses.dtos.GetExpenseQuery;
import com._naptic.trakr_api.expenses.dtos.RegisterExpenseDto;
import com._naptic.trakr_api.expenses.services.ExpenseService;
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
@Tag(name = "Expenses")
@RequestMapping("expenses")
public class ExpenseController {
    private final ExpenseService service;

    @PostMapping
    @Operation(summary = "Register an expense")
    public ResponseEntity<CustomApiResponse<ExpenseResponse>> register(@Valid @RequestBody RegisterExpenseDto dto) {
        ExpenseResponse response = service.register(dto);

        return WrappedResponse.of(response, "Expense registered successfully", HttpStatus.CREATED);
    }

    @GetMapping
    @Operation(summary = "Retrieve a paginated list of expenses")
    public ResponseEntity<PaginatedApiResponse<ExpenseResponse>> findAll(@ModelAttribute GetExpenseQuery query) {
        Page<ExpenseResponse> page = service.findAll(query);

        return WrappedResponse.of(page, "Expenses retrieved successfully");
    }
}
