package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Reminder;
import com.errahouti.BabyCareApi.repository.ReminderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepo reminderRepo;
    private final ReminderMapper reminderMapper;

    public ReminderDTO createReminder(ReminderDTO reminderDTO) throws NotFoundException {
        Reminder reminder = reminderRepo.findById(reminderDTO.getId())
                .orElseThrow(NotFoundException::new);
        return reminderMapper.toReminderDTO(reminderRepo.save(reminder));

    }


}
