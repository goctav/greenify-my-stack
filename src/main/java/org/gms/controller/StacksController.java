package org.gms.controller;

import io.micronaut.http.HttpResponse;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import org.gms.controller.util.ValidResponseWrapper;
import org.gms.dto.StackDetailsDto;
import org.gms.dto.StackDto;
import org.gms.service.CarbonEmissionsService;
import org.gms.service.StackService;

import javax.validation.constraints.NotNull;
import java.util.Collection;
import java.util.stream.Collectors;

@Controller("/stack")
public class StacksController {

    @Inject
    private StackService stackService;

    @Inject
    private CarbonEmissionsService carbonEmissionsService;

    @Get
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<ValidResponseWrapper<Collection<StackDetailsDto>>> getAll() {

        Collection<StackDetailsDto> stacks = stackService.getAll().stream()
                .map(this::toStackDetailsDto)
                .collect(Collectors.toList());
        ValidResponseWrapper<Collection<StackDetailsDto>> response = ValidResponseWrapper.<Collection<StackDetailsDto>>builder()
                .data(stacks)
                .build();
        return HttpResponse.ok(response);
    }

    private StackDetailsDto toStackDetailsDto(StackDto stack) {
        double rating = carbonEmissionsService.getRatingForRegion(stack.getRegion()).getRating();
        return StackDetailsDto.builder()
                .id(stack.getId())
                .name(stack.getName())
                .region(stack.getRegion())
                .resources(stack.getResources())
                .carbonEmissionRating(rating)
                .build();
    }

    @Get("/{id}")
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<ValidResponseWrapper<StackDto>> getById(@NotNull Long id) {
        return stackService.getById(id)
                .map(s -> ValidResponseWrapper.<StackDto>builder().data(s).build())
                .map(HttpResponse::ok)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find stack with id: '%s'", id)));
    }
}
