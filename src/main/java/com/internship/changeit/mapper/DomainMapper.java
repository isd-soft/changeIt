package com.internship.changeit.mapper;

import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.model.Domain;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DomainMapper {
    DomainMapper INSTANCE = Mappers.getMapper(DomainMapper.class);

//    @Mapping(source = "domainName", target = "name")
    DomainDto toDto(Domain domain);

    Domain fromDto(DomainDto domainDto);
}
