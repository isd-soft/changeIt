package com.internship.changeit.model;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Location extends BasicEntity {

    private String locationName;

    @JoinColumn(name = "district_id")
    @ManyToOne
    private District district;
}
