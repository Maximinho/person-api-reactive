package com.example.personapi.service.impl;

import com.example.personapi.domain.Person;
import com.example.personapi.domain.PersonFilter;
import com.example.personapi.entity.PersonEntity;
import com.example.personapi.exception.PersonNotFoundException;
import com.example.personapi.mapper.PersonToPersonEntityMapper;
import com.example.personapi.repository.PersonRepository;
import com.example.personapi.service.PersonService;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import static org.springframework.data.domain.ExampleMatcher.GenericPropertyMatchers.startsWith;

@Service
public class PersonServiceImpl implements PersonService {

    private static final ExampleMatcher EXAMPLE_MATCHER = ExampleMatcher.matching()
            .withStringMatcher(ExampleMatcher.StringMatcher.STARTING);

    private final PersonRepository personRepository;
    private final PersonToPersonEntityMapper personToPersonEntityMapper;

    public PersonServiceImpl(PersonRepository personRepository, PersonToPersonEntityMapper personToPersonEntityMapper) {
        this.personRepository = personRepository;
        this.personToPersonEntityMapper = personToPersonEntityMapper;
    }

    @Transactional
    @Override
    public Mono<Person> createPerson(Person person) {
        return personRepository.save(personToPersonEntityMapper.toEntity(person))
                .map(personToPersonEntityMapper::toDomain);
    }

    @Override
    public Mono<Person> findPersonById(long id) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new PersonNotFoundException(id)))
                .map(personToPersonEntityMapper::toDomain);
    }

    @Override
    public Flux<Person> findPersons(PersonFilter personFilter) {
        final Example<PersonEntity> personExample = Example.of(new PersonEntity(personFilter.firstName(), personFilter.lastName()), EXAMPLE_MATCHER);

        return personRepository.findAll(personExample)
                .map(personToPersonEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public Mono<Person> updatePerson(long id, Person person) {
        return personRepository.findById(id)
                .switchIfEmpty(Mono.error(new PersonNotFoundException(id)))
                .map(personEntity -> personToPersonEntityMapper.toEntity(person)
                        .withId(personEntity.getId())
                )
                .flatMap(personRepository::save)
                .map(personToPersonEntityMapper::toDomain);
    }

    @Transactional
    @Override
    public Mono<Void> deletePerson(long id) {
        return personRepository.deleteById(id)
                .handle((deleted, sink) -> {
                    if (Boolean.TRUE.equals(deleted)) {
                        sink.next(deleted);
                    } else {
                        sink.error(new PersonNotFoundException(id));
                    }
                })
                .then();
    }
}
