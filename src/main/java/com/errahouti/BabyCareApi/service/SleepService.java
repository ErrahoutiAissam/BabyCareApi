package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.dto.sleep.UpdateSleepDTO;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.exception.SleepNotFoundException;
import com.errahouti.BabyCareApi.model.*;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
    private final ChildRepo childRepo;
    private final ChildService childService;
    private final SleepMapper sleepMapper;
    private final ChildMapper childMapper;
    private final ReminderService reminderService;


    public List<SleepDTO> getAll(){
        return sleepRepo.findAll().stream()
                .map(sleepMapper::toSleepDTO).toList();
    }

    public SleepDTO createSleep(CreateSleepDTO sleepDTO) {
        Child child = childRepo.findById(sleepDTO.getChildId()).orElseThrow(NotFoundException::new);

        Sleep sleep = sleepMapper.createSleep(sleepDTO);

        sleep.setChild(child);
        Date currentDate = new Date();
        Date startDate = sleepDTO.getStartDate();
        sleep.setReminderState(reminderService.determineReminderState(currentDate, startDate));
        sleep.setReminderDate(sleepDTO.getStartDate());
        sleep.setSleepType(SleepType.determineSleepType(sleepDTO.getAwakenings()));
        Sleep createdSleep = sleepRepo.save(sleep);

        child.getSleepReminders().add(createdSleep);
        childRepo.save(child);

        System.out.println(createdSleep);
        return sleepMapper.toSleepDTO(createdSleep);
    }
    public SleepDTO update(UpdateSleepDTO updateSleepDTO, Long id) throws SleepNotFoundException {
        Sleep sleepToModify = findSleepById(id);
        sleepMapper.updateSleep(updateSleepDTO, sleepToModify);
        sleepToModify.setReminderState(reminderService.determineReminderState(new Date(), updateSleepDTO.getStartDate()));
        sleepToModify.setReminderDate(updateSleepDTO.getStartDate());
        sleepToModify.setSleepType(SleepType.determineSleepType(updateSleepDTO.getAwakenings()));
        sleepToModify.setId(id);

        return sleepMapper.toSleepDTO(sleepRepo.save(sleepToModify));
    }
    public SleepDTO getById(Long id) throws SleepNotFoundException {
        return sleepMapper.toSleepDTO(findSleepById(id));
    }

    public void delete(Long sleepId) throws SleepNotFoundException{
        Sleep sleep = findSleepById(sleepId);
        sleepRepo.delete(sleep);
    }

    private Sleep findSleepById(Long sleepId) throws SleepNotFoundException {
        return sleepRepo.findById(sleepId)
                .orElseThrow(() -> new SleepNotFoundException(sleepId));
    }

    public List<SleepDTO> getChildSleepReminder(Long id){
        return sleepRepo.findByChildId(id).stream()
                .map(sleepMapper::toSleepDTO).toList();
    }

}
