package com.example.personapi.service;

import com.example.personapi.domain.Person;
import com.example.personapi.domain.PersonFilter;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

public interface PersonService {
    Mono<Person> createPerson(Person person);
    Mono<Person> findPersonById(long id);
    Flux<Person> findPersons(PersonFilter personFilter);
    Mono<Person> updatePerson(long id, Person person);
    Mono<Void> deletePerson(long id);
}
