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
import org.gms.dto.StackSummaryDto;
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
    public HttpResponse<ValidResponseWrapper<Collection<StackSummaryDto>>> getAll() {

        Collection<StackSummaryDto> stacks = stackService.getAll().stream()
                .map(this::toStackSummaryDto)
                .collect(Collectors.toList());
        ValidResponseWrapper<Collection<StackSummaryDto>> response = ValidResponseWrapper.<Collection<StackSummaryDto>>builder()
                .data(stacks)
                .build();
        return HttpResponse.ok(response);
    }

    private StackSummaryDto toStackSummaryDto(StackDto stack) {
        return StackSummaryDto.builder()
                .id(stack.getId())
                .name(stack.getName())
                .region(stack.getRegion())
                .build();
    }

    @Get("/{id}")
    @Produces(MediaType.TEXT_JSON)
    public HttpResponse<ValidResponseWrapper<StackDetailsDto>> getById(@NotNull Long id) {
        return stackService.getById(id)
                .map(this::toStackDetailsDto)
                .map(s -> ValidResponseWrapper.<StackDetailsDto>builder().data(s).build())
                .map(HttpResponse::ok)
                .orElseThrow(() -> new RuntimeException(String.format("Could not find stack with id: '%s'", id)));
    }

    private StackDetailsDto toStackDetailsDto(StackDto stack) {
        return StackDetailsDto.builder()
                .id(stack.getId())
                .name(stack.getName())
                .region(stack.getRegion())
                .resources(stack.getResources())
                .carbonEmissionRating(carbonEmissionsService.getRatingForRegion(stack.getRegion()).getRating())
                .build();
    }
}
