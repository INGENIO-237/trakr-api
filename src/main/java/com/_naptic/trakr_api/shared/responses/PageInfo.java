package com._naptic.trakr_api.shared.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PageInfo {
    private int page;
    private int size;
    private long total;
    private int pages;
    private boolean first;
    private boolean last;
    private boolean empty;
}