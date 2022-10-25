package org.gms.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import org.gms.controller.util.ValidResponseWrapper;
import org.gms.dto.RegionRatingDto;

import java.util.Collection;
import java.util.List;

@Controller("/emission")
public class EmissionsController {

    @Get("/region")
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<ValidResponseWrapper<?>> getCarbonEmissionRatingPerRegion() {
        Collection<RegionRatingDto> ratings = List.of(
                RegionRatingDto.builder()
                        .region("eu-north-1")
                        .time("fslkjsdfasd")
                        .rating(4.5)
                        .build(),
                RegionRatingDto.builder()
                        .region("eu-north-2")
                        .time("fslkjsdfasd")
                        .rating(54.5)
                        .build()
        );
        ValidResponseWrapper<?> response = ValidResponseWrapper.builder()
                .data(ratings)
                .build();
        return HttpResponse.ok(response);
    }
}
