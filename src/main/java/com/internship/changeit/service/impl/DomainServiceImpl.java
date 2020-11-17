package com.internship.changeit.service.impl;

import com.internship.changeit.exception.ApplicationException;
import com.internship.changeit.exception.ExceptionType;
import com.internship.changeit.model.Domain;
import com.internship.changeit.model.Problem;
import com.internship.changeit.repository.DomainRepository;
import com.internship.changeit.service.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DomainServiceImpl implements DomainService {
    private final  DomainRepository domainRepository;

    public DomainServiceImpl(DomainRepository domainRepository) {
        this.domainRepository = domainRepository;
    }

    @Override
    public List<Domain> getAllDomains(){
        return domainRepository.findAll();
    }

    @Override
    public Domain saveDomain(Domain domain){
        domainRepository.save(domain);
        return domain;
    }

    @Override
    public Domain updateDomain(Domain newDomain, Long id) {

        Optional<Domain> optionalDomain = domainRepository.findById(id);

        if(optionalDomain.isPresent()){
            Domain updatable = optionalDomain.get();
            updatable.setDomainName(newDomain.getDomainName());
            domainRepository.save(updatable);
            return updatable;
        } else throw new ApplicationException(ExceptionType.DOMAIN_NOT_FOUND);
    }

    @Override
    public void deleteDomain(Long id) {
        domainRepository.findById(id).
                orElseThrow(() -> new ApplicationException(ExceptionType.DOMAIN_NOT_FOUND));
        domainRepository.deleteById(id);
    }
}
