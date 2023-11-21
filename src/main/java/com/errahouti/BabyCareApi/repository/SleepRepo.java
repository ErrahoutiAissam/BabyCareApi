package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Sleep;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepo extends JpaRepository<Sleep, Long> {
}
