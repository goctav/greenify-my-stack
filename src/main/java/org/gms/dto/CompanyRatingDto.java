package org.gms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class CompanyRatingDto {

    @NonNull
    private String name;

    private double rating;
}
