package com.internship.changeit.mapper;

import com.internship.changeit.dto.UserLogoDto;
import com.internship.changeit.model.UserLogo;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserLogoMapper {
    UserLogoMapper INSTANCE = Mappers.getMapper( UserLogoMapper.class);

    UserLogoDto toDto(final UserLogo userLogo);
    UserLogo fromDto(final UserLogoDto userLogoDto);

}
