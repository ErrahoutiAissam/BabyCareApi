package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Diaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DiaperRepo extends JpaRepository<Diaper, Long> {
}
