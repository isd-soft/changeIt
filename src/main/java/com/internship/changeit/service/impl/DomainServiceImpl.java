package com.internship.changeit.service.impl;

import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.mapper.DomainMapper;
import com.internship.changeit.model.Domain;
import com.internship.changeit.repository.DomainRepository;
import com.internship.changeit.service.DomainService;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public Domain saveDomain(DomainDto domainDto){
        Domain domain = DomainMapper.INSTANCE.fromDto(domainDto);
        domainRepository.save(domain);
        return domain;
    }

    @Override
    public Domain saveDomain(Domain domain){
        domainRepository.save(domain);
        return domain;
    }
}
