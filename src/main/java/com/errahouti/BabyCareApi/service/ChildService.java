package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.activity.ActivityDTO;
import com.errahouti.BabyCareApi.dto.activity.ActivityMapper;
import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.dto.diaper.DiaperMapper;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionMapper;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepo childRepo;
    private final ChildMapper childMapper;
    private final NutritionMapper nutritionMapper;
    private final SleepMapper sleepMapper;
    private final ActivityMapper activityMapper;
    private final DiaperMapper diaperMapper;


    public ChildDTO getChildById(Long id){
        return childMapper.toChildDTO(childRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public ChildDTO createChild(ChildDTO childDTO){
        return childMapper.toChildDTO(childRepo
                .save(childMapper.toChild(childDTO)));
    }

    public List<ChildDTO> getAllChildren(){
        return childRepo.findAll().stream()
                .map(childMapper::toChildDTO).toList();
    }

    public ChildDTO updateChild(ChildDTO updateRequest, Long id){
        Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
        childMapper.update(updateRequest, child);
        child.setId(id);

        return childMapper.toChildDTO(childRepo.save(child));
    }

    public void deleteChild(Long id){
        Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
        childRepo.delete(child);
    }

    @Transactional
    public void addNutritionReminder(NutritionDTO nutritionDTO, ChildDTO childDTO){

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getNutritionReminders().add(nutritionMapper.createNutrition(nutritionDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addSleepReminder(CreateSleepDTO sleepDTO, ChildDTO childDTO) {

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getSleepReminders().add(sleepMapper.createSleep(sleepDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addActivityReminder(ActivityDTO activityDTO, ChildDTO childDTO){

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getActivityReminders().add(activityMapper.createActivity(activityDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addDiaperReminder(DiaperDTO diaperDTO, ChildDTO childDTO){

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getDiaperReminders().add(diaperMapper.createDiaper(diaperDTO));
        childRepo.save(child);
    }



}
