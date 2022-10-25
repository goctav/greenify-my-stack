package org.gms.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import org.gms.dto.StackDto;
import org.gms.repo.StackRepository;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.gms.util.Converters.convertObjectToType;

@Controller("/stack")
public class StacksController {

    @Inject
    private StackRepository stackRepository;

    @Get
    @Produces(MediaType.TEXT_JSON)
    public ValidResponseWrapper<Collection<StackDto>> getAll() {

        TypeReference<StackDto> typeReference = new TypeReference<>() {};

        List<StackDto> stacks = StreamSupport.stream(stackRepository.findAll().spliterator(), false)
                .map(stack -> convertObjectToType(stack, typeReference))
                .collect(Collectors.toList());
        return ValidResponseWrapper.<Collection<StackDto>>builder()
                .data(stacks)
                .build();
    }
}
