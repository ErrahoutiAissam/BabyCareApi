package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.activity.ActivityDTO;
import com.errahouti.BabyCareApi.dto.activity.ActivityMapper;
import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.repository.ActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final ActivityMapper activityMapper;

    public ActivityDTO createActivity(ActivityDTO activityDTO){
        return activityMapper.toActivityDTO(activityRepo
                .save(activityMapper.createActivity(activityDTO)));
    }

    public ActivityDTO getActivityById(Long id) throws NotFoundException {
        return activityMapper.toActivityDTO(activityRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<ActivityDTO> getAllNutrition(){
        return activityRepo.findAll().stream()
                .map(activityMapper::toActivityDTO).toList();
    }

}
