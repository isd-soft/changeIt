package com.internship.changeit.dto;

import com.internship.changeit.model.Address;
import lombok.Data;

import java.util.Date;
import java.util.List;

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
    private Address address;
    private List<DomainDto> domains;

}
