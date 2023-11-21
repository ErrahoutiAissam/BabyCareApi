package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Nutrition;
import com.errahouti.BabyCareApi.repository.NutritionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final NutritionRepo nutritionRepo;
    private final NutritionMapper nutritionMapper;

    public NutritionDTO createNutrition(NutritionDTO nutritionDTO){
        return nutritionMapper.toNutritionDTO(nutritionRepo
                .save(nutritionMapper.createNutrition(nutritionDTO)));
    }

    public NutritionDTO getNutritionById(Long id) throws NotFoundException {
        return nutritionMapper.toNutritionDTO(nutritionRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<NutritionDTO> getAllNutrition(){
        return nutritionRepo.findAll().stream()
                .map(nutritionMapper::toNutritionDTO).toList();
    }
}
