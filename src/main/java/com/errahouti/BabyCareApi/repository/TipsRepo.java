package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Tips;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipsRepo extends JpaRepository<Tips, Long> {
}
