package com.gd.clinic.security.mapper;

import com.gd.clinic.security.entity.User;
import com.gd.clinic.model.NewUserDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapNewUserDtoToUser {

    MapNewUserDtoToUser INSTANCE = Mappers.getMapper(MapNewUserDtoToUser.class);

    @Mapping(target = "username", source = "username",
            defaultExpression = "java(java.util.UUID.randomUUID().toString())")
    User newUserToUser(NewUserDto user);

}
