package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Diaper;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiaperRepo extends JpaRepository<Diaper, Long> {

    List<Diaper> findByChildId(Long id);
}
