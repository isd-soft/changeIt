package com.internship.changeit.dto;

import com.internship.changeit.model.Status;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ProblemDto {

    private Long problem_id;
    private String title;
    private String description;
    private Integer votes;
    private Date created_at;
    private Date updated_at;
    private String status;



}