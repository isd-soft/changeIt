package com.internship.changeit.dto;

import com.internship.changeit.model.Problem;
import lombok.Data;
import java.util.ArrayList;
import java.util.List;

@Data
public class DomainDto {
    private Long domain_id;
    private String domainName;
    private List<Problem> problems = new ArrayList<>();
}