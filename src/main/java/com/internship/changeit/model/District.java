package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class District extends BasicEntity {

    private String districtName;
}
