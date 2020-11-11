package com.internship.changeit;

import com.internship.changeit.dto.DomainDto;
import com.internship.changeit.model.Domain;
import com.internship.changeit.service.DomainService;
import com.internship.changeit.mapper.DomainMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ChangeitApplication {

    public static void main(String[] args) {
        SpringApplication.run(ChangeitApplication.class, args);
    }

}
