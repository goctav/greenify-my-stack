package org.gms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class StackSummaryDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String region;
}
