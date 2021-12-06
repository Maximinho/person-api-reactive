package com.example.personapi.mapper;

import com.example.personapi.domain.Person;
import com.example.personapi.entity.PersonEntity;

public interface PersonToPersonEntityMapper {
    PersonEntity toEntity(Person person);
    Person toDomain(PersonEntity personEntity);
}
