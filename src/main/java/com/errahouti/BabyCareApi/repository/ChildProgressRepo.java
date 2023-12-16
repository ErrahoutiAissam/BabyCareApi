package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.ChildProgress;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChildProgressRepo extends JpaRepository<ChildProgress,Long> {


    List<ChildProgress> findByChild_Id(Long id);
}
