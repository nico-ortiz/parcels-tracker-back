package com.goldeng.mapper;

import org.mapstruct.Mapper;

import org.mapstruct.factory.Mappers;

import com.goldeng.dto.PersonDTO;
import com.goldeng.model.Person;

@Mapper(uses = {CustomerMapper.class})
public interface PersonMapper {
    PersonMapper INSTANCE = Mappers.getMapper(PersonMapper.class);

    PersonDTO personToPersonDTO(Person person);
}
