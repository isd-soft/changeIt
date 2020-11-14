package com.internship.changeit.dto;

import com.internship.changeit.model.District;
import lombok.Data;

@Data
public class LocationDto {
    private Long location_id;
    private String locationName;
    private District district;

}
