package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.healthCare.HealthCareDTO;
import com.errahouti.BabyCareApi.dto.healthCare.HealthCareMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.HealthCare;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.HealthCareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final HealthCareRepo healthCareRepo;
    private final ChildRepo childRepo;
    private final ReminderService reminderService;
    private final HealthCareMapper healthCareMapper;


    public HealthCareDTO getHealthCareById(Long id){
        return healthCareMapper.toHealthCareDTO(healthCareRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public HealthCareDTO create(HealthCareDTO healthCareDTO) {
        Child child = childRepo.findById(healthCareDTO.getChildId()).orElseThrow(NotFoundException::new);

        HealthCare healthCare = healthCareMapper.createHealthCare(healthCareDTO);

        healthCare.setChild(child);
        Date currentDate = new Date();
        Date startDate = healthCareDTO.getStartDate();
        healthCare.setReminderState(reminderService.determineReminderState(currentDate, startDate));
        healthCare.setReminderDate(startDate);
        healthCare.setHealthCareType(healthCareDTO.getHealthCareType());
        healthCare.setNotes(healthCareDTO.getNotes());
        HealthCare createdHealthCare = healthCareRepo.save(healthCare);

        child.getHealthCareReminders().add(createdHealthCare);
        childRepo.save(child);

        System.out.println(createdHealthCare);
        return healthCareMapper.toHealthCareDTO(createdHealthCare);
    }
    public HealthCareDTO updateHealthCare(HealthCareDTO updateRequest, Long id){
        HealthCare healthCare = healthCareRepo.findById(id).orElseThrow(NotFoundException::new);
        healthCareMapper.updateHealthCareFromDTO(updateRequest, healthCare);
        Date startDate = updateRequest.getStartDate();
        healthCare.setReminderDate(startDate);
        healthCare.setReminderState(reminderService.determineReminderState(new Date(), startDate));
        healthCare.setId(id);
        return healthCareMapper.toHealthCareDTO(healthCareRepo.save(healthCare));
    }

    public void deleteHealthCare(Long id){
        HealthCare healthCare = healthCareRepo.findById(id).orElseThrow(NotFoundException::new);
        healthCareRepo.delete(healthCare);
    }

    public List<HealthCareDTO> getAllHealthCares(){
        return healthCareRepo.findAll().stream()
                .map(healthCareMapper::toHealthCareDTO).toList();
    }

    public List<HealthCareDTO> getChildHealthCare(Long id){
        return healthCareRepo.findByChildId(id).stream()
                .map(healthCareMapper::toHealthCareDTO).toList();
    }

}
