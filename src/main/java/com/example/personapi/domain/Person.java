package com.example.personapi.domain;

public record Person(Long id, String firstName, String lastName) {
    public Person(String firstName, String lastName) {
        this(null, firstName, lastName);
    }
}
