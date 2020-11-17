package com.internship.changeit.service;

import com.internship.changeit.model.Domain;
import com.internship.changeit.model.Problem;

import java.util.List;


public interface DomainService {

    List<Domain> getAllDomains();

    Domain saveDomain(Domain domain);

    Domain updateDomain(Domain p, Long id);

    void deleteDomain(Long id);
}
