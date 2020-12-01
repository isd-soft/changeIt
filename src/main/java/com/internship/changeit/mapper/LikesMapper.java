package com.internship.changeit.mapper;

import com.internship.changeit.dto.LikesDto;
import com.internship.changeit.model.Likes;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface LikesMapper {

    LikesMapper INSTANCE = Mappers.getMapper(LikesMapper.class);

    LikesDto toDto(Likes likes);

    Likes fromDto(LikesDto likesDto);
}
