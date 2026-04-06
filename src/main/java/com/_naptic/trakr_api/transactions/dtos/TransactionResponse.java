package com._naptic.trakr_api.transactions.dtos;

import com._naptic.trakr_api.shared.dtos.BaseResponse;
import com._naptic.trakr_api.users.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(callSuper = true)
public class TransactionResponse extends BaseResponse {
    private long amount;

    private String description;
}
