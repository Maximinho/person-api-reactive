package com.example.personapi.mapper.impl;

import com.example.personapi.controller.request.PersonRequest;
import com.example.personapi.domain.Person;
import com.example.personapi.mapper.PersonRequestToPersonMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonRequestToPersonMapperImpl implements PersonRequestToPersonMapper {
    @Override
    public Person toDomain(PersonRequest personRequest) {
        return new Person(personRequest.firstName(), personRequest.lastName());
    }
}
