package org.gms.controller.util;

import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ValidResponseWrapper<T> {

    @NonNull
    private T data;
}
