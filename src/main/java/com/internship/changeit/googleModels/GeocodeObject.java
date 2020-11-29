package com.internship.changeit.googleModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeObject {
    @JsonProperty("place_id")
    private String placeId;
    @JsonProperty("address_components")
    private List<GeoAddress> geoAddressComponents;
    @JsonProperty("formatted_address")
    private String formattedAddress;
    private GeocodeGeometry geometry;
}
