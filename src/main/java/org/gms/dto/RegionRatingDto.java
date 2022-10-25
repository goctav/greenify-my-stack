package org.gms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class RegionRatingDto {

    @NonNull
    private String region;

    @NonNull
    private String time;

    private double rating;
}
