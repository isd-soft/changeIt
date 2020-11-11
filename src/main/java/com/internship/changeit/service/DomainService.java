package com.internship.changeit.service;

import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.model.Domain;
import org.springframework.stereotype.Service;

import java.util.List;


public interface DomainService {

    List<Domain> getAllDomains();
    public Domain saveDomain(DomainDto domainDto);
    public Domain saveDomain(Domain domain);
}
