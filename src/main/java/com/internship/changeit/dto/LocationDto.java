package com.internship.changeit.dto;

import lombok.Data;

@Data
public class LocationDto {
    private Long location_id;
    private String locationName;
    private DistrictDto district;

}
