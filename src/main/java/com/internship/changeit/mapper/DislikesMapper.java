package com.internship.changeit.mapper;

import com.internship.changeit.dto.DislikesDto;
import com.internship.changeit.model.Dislikes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DislikesMapper {

    DislikesMapper INSTANCE = Mappers.getMapper(DislikesMapper.class);

    DislikesDto toDto(Dislikes dislikes);

    Dislikes fromDto(DislikesDto dislikesDto);
}
