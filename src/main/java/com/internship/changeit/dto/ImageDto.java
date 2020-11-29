package com.internship.changeit.dto;

import lombok.Data;

@Data
public class ImageDto {

    private Long id;
    private ProblemDto problem;
    private byte[] imageFile;
}
