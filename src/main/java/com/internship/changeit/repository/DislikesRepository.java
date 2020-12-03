package com.internship.changeit.repository;

import com.internship.changeit.model.Dislikes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DislikesRepository extends JpaRepository<Dislikes, Long> {
}
