package com.example.personapi.mapper;

import com.example.personapi.controller.response.PersonResponse;
import com.example.personapi.domain.Person;

public interface PersonToPersonResponseMapper {
    PersonResponse toResponse(Person person);
}
