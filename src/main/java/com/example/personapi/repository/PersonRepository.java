package com.example.personapi.repository;

import com.example.personapi.entity.PersonEntity;
import org.springframework.data.repository.query.ReactiveQueryByExampleExecutor;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface PersonRepository extends ReactiveCrudRepository<PersonEntity, Long>, ReactiveQueryByExampleExecutor<PersonEntity> {
    Mono<Boolean> deleteById(long id);
}
