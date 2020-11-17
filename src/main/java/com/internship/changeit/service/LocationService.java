package com.internship.changeit.service;

import com.internship.changeit.model.Location;

import java.util.List;

public interface LocationService {

    List<Location> getAllLocations();

    Location saveLocation(Location location);

    Location updateLocation(Location location, Long id);

    void deleteLocation(long id);
}
