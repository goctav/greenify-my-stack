package org.gms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties
public class StackDto {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String region;

    @NonNull
    private Collection<ResourceDto> resources;
}
