package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ChildRepo extends JpaRepository<Child, Long> {
    Optional<Child> findByIdAndParentId(Long childId, Long parentId);
}
