package com.internship.changeit.mapper;

import com.internship.changeit.dto.ImageDto;
import com.internship.changeit.model.Image;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ImageMapper {
    ImageMapper INSTANCE = Mappers.getMapper(ImageMapper.class);

    ImageDto toDto(final Image image);
    Image fromDto(final ImageDto imageDto);
}
