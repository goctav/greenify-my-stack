package org.gms.controller.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ValidResponseWrapper<T> {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    @NonNull
    private T data;
}
