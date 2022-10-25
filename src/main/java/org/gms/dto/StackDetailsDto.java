package org.gms.dto;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

import java.util.Collection;

@Data
@Builder
public class StackDetailsDto {

    @NonNull
    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String region;

    @NonNull
    private Collection<ResourceDto> resources;

    private double carbonEmissionRating;
}
