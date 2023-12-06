package com.errahouti.BabyCareApi.service;



import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Reminder;
import com.errahouti.BabyCareApi.repository.ReminderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepo reminderRepo;
    private final ReminderMapper reminderMapper;

    public ReminderDTO createReminder(ReminderDTO reminderDTO){
        Reminder reminder = reminderMapper.createReminder(reminderDTO);
        reminder.setReminderDate(reminderDTO.getReminderDate());
        return reminderMapper.toReminderDTO(reminderRepo.save(reminder));

    }

    public ReminderDTO getReminderById(Long id) throws NotFoundException {
        return reminderMapper.toReminderDTO(reminderRepo
                .findById(id).orElseThrow(NotFoundException::new));
    }

    public ReminderDTO updateReminder(ReminderDTO updateRequest, long id){
        Reminder reminder = reminderRepo.findById(id).orElseThrow(NotFoundException::new);
        reminderMapper.updateReminderFromDTO(updateRequest, reminder);
        reminder.setId(id);
        reminder.setReminderDate(updateRequest.getReminderDate());
        return reminderMapper.toReminderDTO(reminderRepo.save(reminder));
    }

    public void deleteReminder(Long id){
        Reminder reminder = reminderRepo.findById(id).orElseThrow(NotFoundException::new);
        reminderRepo.delete(reminder);
    }

    public List<ReminderDTO> getAllReminders(){
        return reminderRepo.findAll().stream()
                .map(reminderMapper::toReminderDTO).toList();
    }


}
