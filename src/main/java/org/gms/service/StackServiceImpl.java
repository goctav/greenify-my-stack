package org.gms.service;

import com.fasterxml.jackson.core.type.TypeReference;
import jakarta.inject.Inject;
import jakarta.inject.Singleton;
import org.gms.dto.StackDto;
import org.gms.repo.StackRepository;
import org.gms.repo.domain.Resource;
import org.gms.repo.domain.Stack;

import java.util.Collection;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import static org.gms.util.Converters.convertObjectToType;

@Singleton
public class StackServiceImpl implements StackService {

    @Inject
    private StackRepository stackRepository;

    @Override
    public Collection<StackDto> getAll() {
        TypeReference<StackDto> typeReference = new TypeReference<>() {};
        return StreamSupport.stream(stackRepository.findAll().spliterator(), false)
                .map(stack -> convertObjectToType(stack, typeReference))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<StackDto> getById(Long id) {
        TypeReference<StackDto> typeReference = new TypeReference<>() {};
        return stackRepository.findById(id)
                .map(s -> convertObjectToType(s, typeReference));
    }

    @Override
    public void save(StackDto stackDto) {
        TypeReference<Stack> typeReference = new TypeReference<>() {};
        Stack stack = convertObjectToType(stackDto, typeReference);
        Optional<Stack> optional = stackRepository.findByName(stackDto.getName());
        optional.map(e -> stackRepository.update(mergeStackInformation(e, stack)))
                .orElseGet(() -> stackRepository.save(stack));
    }

    private static Stack mergeStackInformation(Stack original, Stack updated) {
        return Stack.builder()
                .id(original.getId())
                .name(original.getName())
                .region(updated.getRegion())
                .resources(mergeResourcesInformation(original.getResources(), updated.getResources()))
                .build();
    }

    // TODO
    private static Collection<Resource> mergeResourcesInformation(Collection<Resource> original,
                                                                  Collection<Resource> updated) {
        Map<String, Resource> originalResourcesByPID = original.stream()
                .collect(Collectors.toMap(
                        Resource::getName,
                        Function.identity()));
        Map<String, Resource> updatedResourcesByPID = updated.stream()
                .collect(Collectors.toMap(
                        Resource::getName,
                        Function.identity()));
        Set<String> commonPIDs = originalResourcesByPID.keySet().stream()
                .filter(updatedResourcesByPID::containsKey)
                .collect(Collectors.toSet());
        Set<String> newPIDs = updatedResourcesByPID.keySet().stream()
                .filter(Predicate.not(commonPIDs::contains))
                .collect(Collectors.toSet());
        List<Resource> resources = new LinkedList<>();
        commonPIDs.forEach(pid -> resources.add(Resource.builder()
                .id(originalResourcesByPID.get(pid).getId())
                .name(originalResourcesByPID.get(pid).getName())
                .resourceType(originalResourcesByPID.get(pid).getResourceType())
                .status(updatedResourcesByPID.get(pid).getStatus())
                .build()));
        newPIDs.forEach(pid -> resources.add(updatedResourcesByPID.get(pid)));
        return resources;
    }
}
