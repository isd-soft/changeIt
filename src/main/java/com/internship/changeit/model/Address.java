package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.Embeddable;

@Data
@Embeddable
public class Address {

    private String address;
    private String lng;
    private String lat;
}
