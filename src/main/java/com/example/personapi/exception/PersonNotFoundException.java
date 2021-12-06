package com.example.personapi.exception;

public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException(long id) {
        super(String.format("Person(id=%d) not found", id));
    }
}
