package com.internship.changeit.service;

import com.internship.changeit.model.District;

import java.util.List;

public interface DistrictService {

    List<District> getAllDistricts();

    District saveDistrict(District district);

    District updateDistrict(District district, Long id);

    void deleteDistrict(long id);
}
