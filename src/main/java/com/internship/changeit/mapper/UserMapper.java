package com.internship.changeit.mapper;

import com.internship.changeit.dto.UserDto;
import com.internship.changeit.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.ValueMapping;
import org.mapstruct.ValueMappings;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    UserDto toDto(User user);
    User fromDto(UserDto userDto);
}