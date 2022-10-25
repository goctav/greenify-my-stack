package org.gms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties
public class ResourceDto {

    private Long id;

    @NonNull
    private String name;

    @NonNull
    private String resourceType;

    @NonNull
    private String status;
}
