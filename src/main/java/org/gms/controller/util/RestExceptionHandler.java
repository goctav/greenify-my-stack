package org.gms.controller.util;

import io.micronaut.http.HttpRequest;
import io.micronaut.http.HttpStatus;
import io.micronaut.http.annotation.Produces;
import io.micronaut.http.server.exceptions.ExceptionHandler;
import jakarta.inject.Singleton;

@Produces
@Singleton
public class RestExceptionHandler implements ExceptionHandler<RuntimeException, ErrorResponseWrapper> {

    @Override
    public ErrorResponseWrapper handle(HttpRequest request, RuntimeException exception) {
        if (exception instanceof StackNotFoundException) {
            return ErrorResponseWrapper.from(HttpStatus.NOT_FOUND, exception.getMessage());
        }
        exception.printStackTrace();
        return ErrorResponseWrapper.from(HttpStatus.BAD_REQUEST, exception.getMessage());
    }
}
