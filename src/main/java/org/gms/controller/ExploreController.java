package org.gms.controller;

import com.fasterxml.jackson.core.type.TypeReference;
import io.micronaut.http.annotation.Controller;
import io.micronaut.http.annotation.Post;
import jakarta.inject.Inject;
import org.gms.dto.StackDto;
import org.gms.repo.domain.Stack;
import org.gms.repo.StackRepository;
import org.gms.service.StackService;
import org.gms.util.Converters;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

@Controller("/explore")
public class ExploreController {

    @Inject
    private StackService stackService;

    @Inject
    private StackRepository stackRepository;

    @Post
    public void run() {
        Collection<StackDto> discoveredStacks = stackService.explore();
        TypeReference<Stack> ref = new TypeReference<>() {};
        List<Stack> stacks = discoveredStacks.stream()
                .map(stack -> Converters.convertObjectTo(stack, ref))
                .collect(Collectors.toList());
        stackRepository.saveAll(stacks);
    }
}
