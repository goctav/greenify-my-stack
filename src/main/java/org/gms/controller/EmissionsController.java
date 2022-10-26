package org.gms.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import org.gms.controller.util.ValidResponseWrapper;
import org.gms.dto.RegionRatingDto;

import java.time.Instant;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/emission")
public class EmissionsController {

    private static final List<String> knownRegions = List.of(
            "us-east-2",
            "us-east-1",
            "us-west-1",
            "us-west-2",
            "af-south-1",
            "ap-east-1",
            "ap-southeast-3",
            "ap-south-1",
            "ap-northeast-3",
            "ap-northeast-2",
            "ap-southeast-1",
            "ap-southeast-2",
            "ap-northeast-1",
            "ca-central-1",
            "eu-central-1",
            "eu-west-1",
            "eu-west-2",
            "eu-south-1",
            "eu-west-3",
            "eu-north-1",
            "me-south-1",
            "me-central-1",
            "sa-east-1"
    );

    @Get("/region")
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<ValidResponseWrapper<Collection<RegionRatingDto>>> getCarbonEmissionRatingPerRegion() {
        List<RegionRatingDto> ratings = knownRegions.stream()
                .map(r -> RegionRatingDto.builder()
                        .region(r)
                        .rating(Math.random() * 100)
                        .time(Instant.now().toString())
                        .build())
                .collect(Collectors.toList());
        ValidResponseWrapper<Collection<RegionRatingDto>> response = ValidResponseWrapper.<Collection<RegionRatingDto>>builder()
                .data(ratings)
                .build();
        return HttpResponse.ok(response);
    }
}
