package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Child;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ChildRepo extends JpaRepository<Child, Long> {

}
