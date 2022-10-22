package org.gms.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder
@JsonIgnoreProperties({"id"})
public class ResourceDto {

    @NonNull
    private String name;

    @NonNull
    private String resourceType;

    @NonNull
    private String status;
}
