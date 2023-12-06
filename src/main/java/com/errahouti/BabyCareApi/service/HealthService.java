package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.healthCare.HealthCareDTO;
import com.errahouti.BabyCareApi.dto.healthCare.HealthCareMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.HealthCare;
import com.errahouti.BabyCareApi.model.ReminderState;
import com.errahouti.BabyCareApi.repository.HealthCareRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Service
@RequiredArgsConstructor
public class HealthService {

    private final HealthCareRepo healthCareRepo;
    private final HealthCareMapper healthCareMapper;


    public HealthCareDTO getHealthCareById(Long id){
        return healthCareMapper.toHealthCareDTO(healthCareRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public HealthCareDTO createHealthCareReminder(HealthCareDTO createRequest){
        HealthCare healthCare = healthCareMapper.createHealthCare(createRequest);
        healthCare.setReminderDate(createRequest.getReminderDate());
        healthCare.setReminderState(createRequest.getReminderState());
        healthCare.setReminderState(ReminderState.UPCOMING);
        return healthCareMapper.toHealthCareDTO(healthCareRepo.save(healthCare));
    }

    public HealthCareDTO updateHealthCare(HealthCareDTO updateRequest, Long id){
        HealthCare healthCare = healthCareRepo.findById(id).orElseThrow(NotFoundException::new);
        healthCareMapper.updateHealthCareFromDTO(updateRequest, healthCare);
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

}
