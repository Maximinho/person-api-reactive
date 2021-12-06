package com.example.personapi.mapper.impl;

import com.example.personapi.controller.response.PersonResponse;
import com.example.personapi.domain.Person;
import com.example.personapi.mapper.PersonToPersonResponseMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonResponseMapperImpl implements PersonToPersonResponseMapper {
    @Override
    public PersonResponse toResponse(Person person) {
        return new PersonResponse(person.id(), person.firstName(), person.lastName());
    }
}
