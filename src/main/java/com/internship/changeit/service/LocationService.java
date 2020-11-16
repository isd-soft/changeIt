package com.internship.changeit.service;

import com.internship.changeit.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> getAllLocations();

    Location saveLocation(Location location);

    void deleteLocation(long id);
}
