package org.gms.repo;

import io.micronaut.data.annotation.Repository;
import io.micronaut.data.repository.CrudRepository;
import org.gms.repo.domain.Stack;

@Repository
public interface StackRepository extends CrudRepository<Stack, Long> {

//    @Executable
//    Stack find(String id);
}
