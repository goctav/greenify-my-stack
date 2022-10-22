package org.gms.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import io.micronaut.http.MediaType;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Get;
import io.micronaut.http.annotation.Produces;
import jakarta.inject.Inject;
import org.gms.repo.domain.Stack;
import org.gms.dto.StackDto;
import org.gms.repo.StackRepository;
import org.gms.util.Converters;

import java.util.Collection;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller("/stack")
public class StacksController {

    @Inject
    private StackRepository stackRepository;

    @Get
    @Produces(MediaType.TEXT_JSON)
    public Collection<StackDto> getAll() throws JsonProcessingException {

        TypeReference<StackDto> typeReference = new TypeReference<>() {};

        Iterable<Stack> stacks = stackRepository.findAll();
        return StreamSupport.stream(stacks.spliterator(), false)
                .map(stack -> Converters.convertObjectTo(stack, typeReference))
                .collect(Collectors.toList());
    }
}
