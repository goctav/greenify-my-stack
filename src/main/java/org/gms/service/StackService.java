package org.gms.service;

import org.gms.dto.StackDto;

import java.util.Collection;
import java.util.Optional;

public interface StackService {

    Collection<StackDto> getAll();

    Optional<StackDto> getById(Long id);

    void save(StackDto stack);
}
