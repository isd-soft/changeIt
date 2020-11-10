package com.internship.changeit.controller;

import com.internship.changeit.model.Domain;
import com.internship.changeit.service.DomainService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/domain")
public class DomainController {

    private final DomainService domainService;

    public DomainController(DomainService domainService) {
        this.domainService = domainService;
    }

    @GetMapping
    public List<Domain> getAllDomains(){
        return domainService.getAllDomains();
    }
}
