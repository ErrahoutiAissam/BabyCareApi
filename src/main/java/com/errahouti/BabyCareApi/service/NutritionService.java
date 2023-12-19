package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.Nutrition;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.NutritionRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class NutritionService {

    private final NutritionRepo nutritionRepo;
    private final NutritionMapper nutritionMapper;
    private final ChildRepo childRepo;

    public NutritionDTO createNutrition(NutritionDTO nutritionDTO) {
        Child child = childRepo.findById(nutritionDTO.getChildId()).orElseThrow(NotFoundException::new);

        Nutrition nutrition = nutritionMapper.createNutrition(nutritionDTO);

        nutrition.setChild(child);
        nutrition.setReminderState(nutritionDTO.getReminderState());
        nutrition.setReminderDate(nutritionDTO.getReminderDate());

        Nutrition createdNutrition = nutritionRepo.save(nutrition);

        child.getNutritionReminders().add(createdNutrition);
        childRepo.save(child);

        System.out.println(createdNutrition);
        return nutritionMapper.toNutritionDTO(createdNutrition);
    }


    public NutritionDTO updateNutrition(NutritionDTO updateRequest, Long id){
        Nutrition nutrition = nutritionRepo.findById(id).orElseThrow(NotFoundException::new);
        nutritionMapper.updateNutritionFromDTO(updateRequest, nutrition);
        nutrition.setReminderState(updateRequest.getReminderState());
        nutrition.setReminderDate(updateRequest.getReminderDate());
        nutrition.setId(id);

        return nutritionMapper.toNutritionDTO(nutritionRepo.save(nutrition));
    }

    public void deleteNutrition(Long id){
        Nutrition nutrition = nutritionRepo.findById(id).orElseThrow(NotFoundException::new);
        nutritionRepo.delete(nutrition);
    }

    public NutritionDTO getNutritionById(Long id) throws NotFoundException {
        return nutritionMapper.toNutritionDTO(nutritionRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<NutritionDTO> getAllNutrition(){
        return nutritionRepo.findAll().stream()
                .map(nutritionMapper::toNutritionDTO).toList();
    }
    public List<NutritionDTO> getChildNutritions(Long id){
        return nutritionRepo.findByChildId(id).stream()
                .map(nutritionMapper::toNutritionDTO).toList();
    }
}
