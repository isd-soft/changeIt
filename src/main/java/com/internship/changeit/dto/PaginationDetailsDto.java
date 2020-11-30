package com.internship.changeit.dto;

import lombok.Data;

@Data
public class PaginationDetailsDto {
    private int page;
    private int size;
    private String sortDir;
    private String sort;
}
