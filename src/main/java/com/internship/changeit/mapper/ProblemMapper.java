package com.internship.changeit.mapper;

import com.internship.changeit.dto.ProblemDto;
import com.internship.changeit.model.Problem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ProblemMapper {

    ProblemMapper INSTANCE = Mappers.getMapper( ProblemMapper.class );

    ProblemDto toDto(Problem problem);

    Problem fromDto(ProblemDto problemDto);
}