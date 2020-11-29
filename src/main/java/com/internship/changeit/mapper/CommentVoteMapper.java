package com.internship.changeit.mapper;

import com.internship.changeit.dto.CommentVoteDto;
import com.internship.changeit.model.CommentVote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentVoteMapper {

    CommentVoteMapper INSTANCE = Mappers.getMapper(CommentVoteMapper.class);

    CommentVoteDto toDto(CommentVote commentVote);

    CommentVote fromDto(CommentVoteDto commentVoteDto);
}
