package com.example.personapi.mapper.impl;

import com.example.personapi.domain.Person;
import com.example.personapi.entity.PersonEntity;
import com.example.personapi.mapper.PersonToPersonEntityMapper;
import org.springframework.stereotype.Component;

@Component
public class PersonToPersonEntityMapperImpl implements PersonToPersonEntityMapper {
    @Override
    public PersonEntity toEntity(Person person) {
        return new PersonEntity(person.firstName(), person.lastName());
    }

    @Override
    public Person toDomain(PersonEntity personEntity) {
        return new Person(personEntity.getId(), personEntity.getFirstName(), personEntity.getLastName());
    }
}
