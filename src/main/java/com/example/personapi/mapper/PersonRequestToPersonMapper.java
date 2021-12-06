package com.example.personapi.mapper;

import com.example.personapi.controller.request.PersonRequest;
import com.example.personapi.domain.Person;

public interface PersonRequestToPersonMapper {
    Person toDomain(PersonRequest personRequest);
}
