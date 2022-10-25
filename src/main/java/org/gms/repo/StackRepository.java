package org.gms.repo;

import io.micronaut.context.annotation.Executable;
import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.gms.repo.domain.Stack;

import java.util.Optional;

@Repository
public interface StackRepository extends CrudRepository<Stack, Long> {

    @Executable
    Optional<Stack> findByName(String name);
}
