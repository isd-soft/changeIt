package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Location;
import com.internship.changeit.repository.LocationRepository;
import com.internship.changeit.service.LocationService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class LocationServiceImpl implements LocationService {

    private final LocationRepository locationRepository;

    public LocationServiceImpl(LocationRepository locationRepository) {
        this.locationRepository = locationRepository;
    }

    @Override
    public List<Location> getAllLocations() {
        return locationRepository.findAll();
    }

    @Override
    public Location saveLocation(Location location) {
        locationRepository.save(location);
        return location;
    }

    @Override
    public Location updateLocation(Location newLocation, Long id) {

        Optional<Location> optionalLocation = locationRepository.findById(id);

        if(optionalLocation.isPresent()){
            Location updatable = optionalLocation.get();
            updatable.setLocationName(newLocation.getLocationName());
            locationRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.LOCATION_NOT_FOUND);
    }

    @Override
    public void deleteLocation(long id) {
        locationRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.LOCATION_NOT_FOUND));
        locationRepository.deleteById(id);
    }

}
