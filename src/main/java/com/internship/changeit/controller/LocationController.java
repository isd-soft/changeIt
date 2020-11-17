package com.internship.changeit.controller;

import com.internship.changeit.dto.LocationDto;
import com.internship.changeit.mapper.LocationMapper;
import com.internship.changeit.model.Location;
import com.internship.changeit.service.LocationService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/location")
public class LocationController {

    private final LocationService locationService;

    public LocationController(LocationService locationService) {
        this.locationService = locationService;
    }

    @GetMapping
    public List<LocationDto> getAllLocations(){
        return locationService.getAllLocations().stream()
                .map(LocationMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Location createLocation(@RequestBody LocationDto locationDto){
        Location location = LocationMapper.INSTANCE.fromDto(locationDto);
        locationService.saveLocation(location);
        return locationService.saveLocation(location);
    }

    @PutMapping("/{id}")
    LocationDto replaceLocation(@RequestBody LocationDto newLocationDto, @PathVariable Long id) {
        Location newLocation = LocationMapper.INSTANCE.fromDto(newLocationDto);
        locationService.updateLocation(newLocation, id);
        return newLocationDto;
    }

    @DeleteMapping("/{id}")
    void deleteLocation(@PathVariable Long id){
        locationService.deleteLocation(id);
    }

}
