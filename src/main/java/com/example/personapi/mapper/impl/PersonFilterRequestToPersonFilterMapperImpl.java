package com.example.personapi.mapper.impl;

import com.example.personapi.controller.request.PersonFilterRequest;
import com.example.personapi.domain.PersonFilter;
import com.example.personapi.mapper.PersonFilterRequestToPersonFilterMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonFilterRequestToPersonFilterMapperImpl implements PersonFilterRequestToPersonFilterMapper {
    @Override
    public PersonFilter toDomain(PersonFilterRequest personFilterRequest) {
        return new PersonFilter(personFilterRequest.firstName(), personFilterRequest.lastName());
    }
}
