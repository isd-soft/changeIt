package com.internship.changeit.dto;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class DomainDto {
    private Long domain_id;
    private String domainName;
    private List<ProblemDto> problems = new ArrayList<>();
}