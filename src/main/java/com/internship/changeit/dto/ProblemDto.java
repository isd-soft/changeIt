package com.internship.changeit.dto;

import com.internship.changeit.controller.DomainController;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Data
public class ProblemDto {

    private Long problem_id;
    private String title;
    private String description;
    private Integer votesCount;
    private Date created_at;
    private Date updated_at;
    private String status;
    private LocationDto location;
    private List<DomainDto> domains;

}
