package com.internship.changeit.service;

import com.internship.changeit.model.Domain;
import java.util.List;


public interface DomainService {

    List<Domain> getAllDomains();
    public Domain saveDomain(Domain domain);

}
