package com.internship.changeit.service;

import com.internship.changeit.model.District;

import java.util.List;

public interface DistrictService {

    List<District> getAllDistricts();

    District saveDistrict(District district);

    void deleteDistrict(long id);
}
