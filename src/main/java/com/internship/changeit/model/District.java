package com.internship.changeit.model;

import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@Data
@Entity
@Table(name = "district")
public class District  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long district_id;
    private String districtName;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private Set<Location> locations;

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    private List<Problem> problems = new ArrayList<>();
}
