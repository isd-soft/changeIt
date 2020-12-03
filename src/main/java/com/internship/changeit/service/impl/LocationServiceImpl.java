package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Location;
import com.internship.changeit.repository.LocationRepository;
import com.internship.changeit.service.LocationService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public Location saveLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public Location updateLocation(Location newLocation, Long id) {
        Optional<Location> optionalLocation = locationRepository.findById(id);

        if(optionalLocation.isPresent()){
            locationRepository.save(newLocation);
            return newLocation;
        } else throw new ApplicationException(ExceptionType.LOCATION_NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public void deleteLocation(long id) {
        final Location location = locationRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.LOCATION_NOT_FOUND));
        locationRepository.delete(location);
    }
}
