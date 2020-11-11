package com.internship.changeit.mapper;

import com.internship.changeit.dto.CommentDto;
import com.internship.changeit.model.Comment;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {

    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    CommentDto toDto(Comment comment);

    Comment fromDto(CommentDto commentDto);
}
