package com.internship.changeit.googleModels;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import java.util.List;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
public class GeocodeResult {
    List<GeocodeObject> results;
    String status;
}
