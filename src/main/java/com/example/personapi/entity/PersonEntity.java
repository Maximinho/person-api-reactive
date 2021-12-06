package com.example.personapi.entity;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.PersistenceConstructor;
import org.springframework.data.relational.core.mapping.Table;

@Table("person")
public class PersonEntity {
    @Id
    private final Long id;

    private final String firstName;

    private final String lastName;

    public PersonEntity(String firstName, String lastName) {
        this(null, firstName, lastName);
    }

    @PersistenceConstructor
    PersonEntity(Long id, String firstName, String lastName) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public PersonEntity withId(long id) {
        return new PersonEntity(id, this.firstName, this.lastName);
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
