package com.internship.changeit.controller;

import com.internship.changeit.dto.DistrictDto;
import com.internship.changeit.mapper.DistrictMapper;
import com.internship.changeit.model.District;
import com.internship.changeit.service.DistrictService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/district")
public class DistrictController {

    private final DistrictService districtService;

    public DistrictController(DistrictService districtService) {
        this.districtService = districtService;
    }

    @GetMapping
    public List<DistrictDto> getAllDistricts(){
        return districtService.getAllDistricts().stream()
                .map(DistrictMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public District createDistrict(@RequestBody DistrictDto districtDto){
        District district = DistrictMapper.INSTANCE.fromDto(districtDto);
        districtService.saveDistrict(district);
        return districtService.saveDistrict(district);
    }

    @PutMapping("/{id}")
    DistrictDto replaceDistrict(@RequestBody DistrictDto newDistrictDto, @PathVariable Long id) {
        District newDistrict = DistrictMapper.INSTANCE.fromDto(newDistrictDto);
        districtService.updateDistrict(newDistrict, id);
        return newDistrictDto;
    }

    @DeleteMapping("/{id}")
    void deleteDistrict(@PathVariable Long id){
        districtService.deleteDistrict(id);
    }

}
