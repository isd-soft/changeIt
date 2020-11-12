package com.internship.changeit.mapper;

import com.internship.changeit.dto.DistrictDto;
import com.internship.changeit.model.District;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DistrictMapper {

    DistrictMapper INSTANCE = Mappers.getMapper(DistrictMapper.class);

    DistrictDto toDto(District district);

    District fromDto(DistrictDto districtDto);
}
