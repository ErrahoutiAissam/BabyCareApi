package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.dto.sleep.UpdateSleepDTO;
import com.errahouti.BabyCareApi.exception.SleepNotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.ReminderState;
import com.errahouti.BabyCareApi.model.Sleep;
import com.errahouti.BabyCareApi.model.SleepType;
import com.errahouti.BabyCareApi.repository.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
    private final ChildService childService;
    private final SleepMapper sleepMapper;
    private final ChildMapper childMapper;

    public List<SleepDTO> getAll(){
        return sleepRepo.findAll().stream()
                .map(sleepMapper::toSleepDTO).toList();
    }
    public SleepDTO create(CreateSleepDTO sleepDTO) {
        Child child = childMapper.toChild(childService.getChildById(sleepDTO.getChildId()));
        Sleep sleep = sleepMapper.createSleep(sleepDTO);

        setReminderDetails(sleep, sleepDTO, child);

        Sleep sleepSaved = sleepRepo.save(sleep);
        childService.addSleepReminder(sleepDTO, childMapper.toChildDTO(child));

        return sleepMapper.toSleepDTO(sleepSaved);
    }
    private void setReminderDetails(Sleep sleep, CreateSleepDTO sleepDTO, Child child) {
        sleep.setReminderDate(sleepDTO.getStartDate());

        Date currentDate = new Date();
        Date startDate = sleepDTO.getStartDate();
        sleep.setReminderState(determineReminderState(currentDate, startDate));
        sleep.setSleepType(SleepType.determineSleepType(sleepDTO.getAwakenings()));
        sleep.setChild(child);
    }

    private ReminderState determineReminderState(Date currentDate, Date startDate) {
        int comparisonResult = currentDate.compareTo(startDate);

        if (comparisonResult < 0) {
            return ReminderState.UPCOMING;
        } else if (comparisonResult == 0) {
            return ReminderState.ONGOING;
        } else {
            return ReminderState.COMPLETED;
        }
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
