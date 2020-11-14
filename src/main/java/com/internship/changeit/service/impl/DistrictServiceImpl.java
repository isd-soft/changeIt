package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.District;
import com.internship.changeit.repository.DistrictRepository;
import com.internship.changeit.service.DistrictService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DistrictServiceImpl implements DistrictService {

    private final DistrictRepository districtRepository;

    public DistrictServiceImpl(DistrictRepository districtRepository) {
        this.districtRepository = districtRepository;
    }

    @Override
    public List<District> getAllDistricts() {
        return districtRepository.findAll();
    }

    @Override
    public District saveDistrict(District district) {
        districtRepository.save(district);
        return district;
    }

    @Override
    public void deleteDistrict(long id) {
        districtRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.DISTRICT_NOT_FOUND));
        districtRepository.deleteById(id);
    }
}
