package com.example.personapi.mapper;

import com.example.personapi.controller.request.PersonFilterRequest;
import com.example.personapi.domain.PersonFilter;

public interface PersonFilterRequestToPersonFilterMapper {
    PersonFilter toDomain(PersonFilterRequest personFilterRequest);
}
