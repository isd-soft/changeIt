package com.internship.changeit.mapper;

import com.internship.changeit.dto.LocationDto;
import com.internship.changeit.model.Location;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LocationMapper {

    LocationMapper INSTANCE = Mappers.getMapper(LocationMapper.class);

    LocationDto toDto(Location location);

    Location fromDto(LocationDto locationDto);
}
