package com.internship.changeit.repository;

import com.internship.changeit.model.UserLogo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserLogoRepository extends JpaRepository<UserLogo, Long> {
}
