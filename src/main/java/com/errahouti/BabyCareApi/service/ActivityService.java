package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.activity.ActivityDTO;
import com.errahouti.BabyCareApi.dto.activity.ActivityMapper;
import com.errahouti.BabyCareApi.exception.ActivityNotFoundException;
import com.errahouti.BabyCareApi.model.Activity;
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

    public ActivityDTO getActivityById(Long id){
        return activityMapper.toActivityDTO(findActivityById(id));
    }

    public List<ActivityDTO> getAll(){
        return activityRepo.findAll().stream()
                .map(activityMapper::toActivityDTO).toList();
    }
    public void deleteActivity(Long id) {
        Activity activity = findActivityById(id);
        activityRepo.delete(activity);
    }

    public ActivityDTO update(ActivityDTO updateRequest, Long id) {
        Activity activity = findActivityById(id);
        activityMapper.updateFromDTO(updateRequest, activity);
        activity.setReminderState(updateRequest.getReminderState());
        activity.setReminderDate(updateRequest.getReminderDate());
        activity.setId(id);

        return activityMapper.toActivityDTO(activityRepo.save(activity));
    }

    private Activity findActivityById(Long id) {
        return activityRepo.findById(id)
                .orElseThrow(() -> new ActivityNotFoundException(id));
    }
}
