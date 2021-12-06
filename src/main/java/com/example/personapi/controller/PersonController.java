package com.example.personapi.controller;

import com.example.personapi.controller.request.PersonFilterRequest;
import com.example.personapi.controller.request.PersonRequest;
import com.example.personapi.controller.response.PersonResponse;
import com.example.personapi.mapper.PersonFilterRequestToPersonFilterMapper;
import com.example.personapi.mapper.PersonRequestToPersonMapper;
import com.example.personapi.mapper.PersonToPersonResponseMapper;
import com.example.personapi.service.PersonService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/api/persons")
public class PersonController {

    private final PersonService personService;
    private final PersonRequestToPersonMapper personRequestToPersonMapper;
    private final PersonToPersonResponseMapper personToPersonResponseMapper;
    private final PersonFilterRequestToPersonFilterMapper personFilterRequestToPersonFilterMapper;

    public PersonController(
            PersonService personService,
            PersonRequestToPersonMapper personRequestToPersonMapper,
            PersonToPersonResponseMapper personToPersonResponseMapper,
            PersonFilterRequestToPersonFilterMapper personFilterRequestToPersonFilterMapper
    ) {
        this.personService = personService;
        this.personRequestToPersonMapper = personRequestToPersonMapper;
        this.personToPersonResponseMapper = personToPersonResponseMapper;
        this.personFilterRequestToPersonFilterMapper = personFilterRequestToPersonFilterMapper;
    }

    @PostMapping
    public Mono<ResponseEntity<PersonResponse>> createPerson(@RequestBody PersonRequest personRequest) {
        return personService.createPerson(personRequestToPersonMapper.toDomain(personRequest))
                .map(person -> ResponseEntity.status(HttpStatus.CREATED)
                        .body(personToPersonResponseMapper.toResponse(person))
                );
    }

    @GetMapping("/{id}")
    public Mono<ResponseEntity<PersonResponse>> getPerson(@PathVariable long id) {
        return personService.findPersonById(id)
                .map(person -> ResponseEntity.ok(personToPersonResponseMapper.toResponse(person)));
    }

    @GetMapping
    public Flux<PersonResponse> getPersons(PersonFilterRequest personFilterRequest) {
        return personService.findPersons(personFilterRequestToPersonFilterMapper.toDomain(personFilterRequest))
                .map(personToPersonResponseMapper::toResponse);
    }

    @PutMapping("/{id}")
    public Mono<ResponseEntity<PersonResponse>> updatePerson(@PathVariable long id, @RequestBody PersonRequest personRequest) {
        return personService.updatePerson(id, personRequestToPersonMapper.toDomain(personRequest))
                .map(person -> ResponseEntity.ok(personToPersonResponseMapper.toResponse(person)));
    }

    @DeleteMapping("/{id}")
    public Mono<ResponseEntity<Void>> deletePerson(@PathVariable long id) {
        return personService.deletePerson(id)
                .thenReturn(ResponseEntity.noContent().build());
    }
}
