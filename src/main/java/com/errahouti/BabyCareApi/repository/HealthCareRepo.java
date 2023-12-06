package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.HealthCare;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HealthCareRepo extends JpaRepository<HealthCare, Long> {
}
