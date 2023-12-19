package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.activity.ActivityDTO;
import com.errahouti.BabyCareApi.dto.activity.ActivityMapper;
import com.errahouti.BabyCareApi.exception.ActivityNotFoundException;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Activity;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.Diaper;
import com.errahouti.BabyCareApi.repository.ActivityRepo;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
    private final ActivityMapper activityMapper;
    private final ChildRepo childRepo;
    private final ReminderService reminderService;


    public ActivityDTO createActivity(ActivityDTO activityDTO){
        Child child = childRepo.findById(activityDTO.getChildId()).orElseThrow(NotFoundException::new);
        Activity activity = activityMapper.createActivity(activityDTO);
        activity.setChild(child);
        activity.setReminderState(activityDTO.getReminderState());
        activity.setReminderDate(activityDTO.getReminderDate());

        Activity createdActivity = activityRepo.save(activity);

        child.getActivityReminders().add(createdActivity);
        childRepo.save(child);

        return activityMapper.toActivityDTO(createdActivity);

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
