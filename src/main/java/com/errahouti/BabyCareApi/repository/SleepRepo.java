package com.errahouti.BabyCareApi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SleepRepo extends JpaRepository<SleepRepo, Long> {
}
