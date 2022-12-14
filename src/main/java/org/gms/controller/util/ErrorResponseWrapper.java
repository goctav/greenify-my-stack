package org.gms.controller.util;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.micronaut.http.HttpStatus;
import lombok.Builder;
import lombok.Data;
import lombok.NonNull;

@Data
@Builder
public class ErrorResponseWrapper {

    @NonNull
    private ErrorResponse error;

    static ErrorResponseWrapper from(@NonNull HttpStatus status,
                                     @NonNull String... messages) {
        return ErrorResponseWrapper.builder()
                .error(ErrorResponse.builder()
                        .type(status)
                        .messages(messages)
                        .build())
                .build();
    }

    @Data
    @Builder
    private static class ErrorResponse {

        @NonNull
        private HttpStatus type;

        @NonNull
        @JsonInclude(JsonInclude.Include.NON_NULL)
        private String[] messages;
    }
}
