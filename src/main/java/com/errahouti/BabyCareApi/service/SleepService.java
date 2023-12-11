package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.dto.sleep.UpdateSleepDTO;
import com.errahouti.BabyCareApi.exception.SleepNotFoundException;
import com.errahouti.BabyCareApi.model.ReminderState;
import com.errahouti.BabyCareApi.model.Sleep;
import com.errahouti.BabyCareApi.repository.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
    private final SleepMapper sleepMapper;

    public List<SleepDTO> getAll(){
        return sleepRepo.findAll().stream()
                .map(sleepMapper::toSleepDTO).toList();
    }
    public SleepDTO create(CreateSleepDTO sleepDTO){
        Sleep sleep = sleepMapper.createSleep(sleepDTO);
        sleep.setReminderDate(sleepDTO.getStartDate());
        sleep.setReminderState(ReminderState.UPCOMING);
        return  sleepMapper.toSleepDTO(sleepRepo.save(sleep));
    }

    public SleepDTO getById(Long id) throws SleepNotFoundException {
        return sleepMapper.toSleepDTO(findSleepById(id));
    }

    public void update(UpdateSleepDTO updateSleepDTO, Long id) throws SleepNotFoundException {
        Sleep sleepToModify = findSleepById(id);
        sleepMapper.updateSleep(updateSleepDTO, sleepToModify);
        sleepToModify.setId(id);
        sleepRepo.save(sleepToModify);
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
