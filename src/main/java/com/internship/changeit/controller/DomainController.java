package com.internship.changeit.controller;

import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.mapper.DomainMapper;
import com.internship.changeit.model.Domain;
import com.internship.changeit.service.DomainService;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@AllArgsConstructor
@RequestMapping("/api/v1/domain")
public class DomainController {

    private final DomainService domainService;

    @GetMapping
    public List<DomainDto> getAllDomains(){
        return domainService.getAllDomains().stream()
                .map(DomainMapper.INSTANCE::toDto)
                .collect(Collectors.toList());
    }

    @PostMapping
    public Domain saveDomainDto(@RequestBody DomainDto domainDto){
        Domain domain = DomainMapper.INSTANCE.fromDto(domainDto);
        domainService.saveDomain(domain);
        return domainService.saveDomain(domain);
    }

    @PutMapping("/{id}")
    public DomainDto replaceDomain(@RequestBody DomainDto newDomainDto, @PathVariable Long id) {
        Domain newDomain = DomainMapper.INSTANCE.fromDto(newDomainDto);
        domainService.updateDomain(newDomain, id);
        return newDomainDto;
    }

    @DeleteMapping("/{id}")
    void deleteDomain(@PathVariable Long id){
        domainService.deleteDomain(id);
    }

}
