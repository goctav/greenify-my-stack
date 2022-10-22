package org.gms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

import java.util.Collection;


@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"id"})
public class StackDto {

    @NonNull
    private String name;

    @NonNull
    private Collection<ResourceDto> resources;
}
