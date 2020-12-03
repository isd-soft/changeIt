package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.District;
import com.internship.changeit.repository.DistrictRepository;
import com.internship.changeit.service.DistrictService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@AllArgsConstructor
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public District saveDistrict(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public District updateDistrict(District newDistrict, Long id) {
        Optional<District> optionalDistrict = districtRepository.findById(id);

        if(optionalDistrict.isPresent()){
            final District updatable = optionalDistrict.get();
            updatable.setDistrictName(newDistrict.getDistrictName());
            districtRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.DISTRICT_NOT_FOUND);
    }

    @Override
    @PreAuthorize("hasAnyAuthority('problem_properties:CRUD')")
    public void deleteDistrict(long id) {
        final District district = districtRepository.findById(id)
                .orElseThrow(() -> new ApplicationException(ExceptionType.DISTRICT_NOT_FOUND));
        districtRepository.delete(district);
    }
}
