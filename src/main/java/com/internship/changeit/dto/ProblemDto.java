package com.internship.changeit.dto;

import lombok.Data;

import java.util.Date;

@Data
public class ProblemDto {

    private Long problem_id;
    private String title;
    private String description;
    private Integer votesCount;
    private Date created_at;
    private Date updated_at;
    private String status;
    private LocationDto location;;

}
