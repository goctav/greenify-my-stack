package org.gms.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import org.gms.service.StackDataCollectionService;
import org.gms.service.StackService;

@Controller("/explore")
public class ExploreController {

    @Inject
    private StackDataCollectionService stackDataCollectionService;

    @Inject
    private StackService stackService;

    @Post
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<?> run() {
        stackDataCollectionService.explore()
                .forEach(stackService::save);
        return HttpResponse.accepted();
    }
}
