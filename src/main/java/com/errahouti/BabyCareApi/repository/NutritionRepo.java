package com.errahouti.BabyCareApi.repository;

import com.errahouti.BabyCareApi.model.Nutrition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NutritionRepo extends JpaRepository<Nutrition, Long> {

    List<Nutrition> findByChildId(Long id);
}
