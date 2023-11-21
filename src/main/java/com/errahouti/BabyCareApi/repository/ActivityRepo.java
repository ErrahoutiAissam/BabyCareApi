package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Activity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ActivityRepo extends JpaRepository<Activity, Long> {

}
