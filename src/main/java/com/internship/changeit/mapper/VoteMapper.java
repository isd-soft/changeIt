package com.internship.changeit.mapper;

import com.internship.changeit.dto.VoteDto;
import com.internship.changeit.model.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface VoteMapper {

    VoteMapper INSTANCE = Mappers.getMapper(VoteMapper.class);

    VoteDto toDto(Vote vote);

    Vote fromDto(VoteDto voteDto);
}
