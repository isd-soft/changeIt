package com.internship.changeit.model;

import lombok.Data;
import org.hibernate.annotations.CacheConcurrencyStrategy;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Data
@Entity
@Cacheable
@org.hibernate.annotations.Cache(usage = CacheConcurrencyStrategy.READ_WRITE)
@Table(name = "district")
public class District  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long district_id;
    private String districtName;

    @OneToMany(mappedBy = "district", fetch = FetchType.EAGER)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Location> locations = new ArrayList<>();

    @OneToMany(mappedBy = "district", fetch = FetchType.LAZY)
    @org.hibernate.annotations.Cache(
            usage = CacheConcurrencyStrategy.READ_WRITE)
    private List<Problem> problems = new ArrayList<>();
}
