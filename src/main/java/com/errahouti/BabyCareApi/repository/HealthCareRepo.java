package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.HealthCare;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HealthCareRepo extends JpaRepository<HealthCare, Long> {

    List<HealthCare> findByChildId(Long id);
}
