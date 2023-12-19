package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.dto.diaper.DiaperMapper;
import com.errahouti.BabyCareApi.exception.DiaperNotFoundException;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.Diaper;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.DiaperRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaperService {

    private final DiaperRepo diaperRepo;
    private final ChildRepo childRepo;
    private final ReminderService reminderService;


    private final DiaperMapper diaperMapper;


    public DiaperDTO createDiaper(DiaperDTO diaperDTO){
        Child child = childRepo.findById(diaperDTO.getChildId()).orElseThrow(NotFoundException::new);
        Diaper diaper = diaperMapper.createDiaper(diaperDTO);
        diaper.setChild(child);
        Date currentDate = new Date();
        Date startDate = diaperDTO.getReminderDate();
        diaper.setReminderState(reminderService.determineReminderState(currentDate, startDate));
        diaper.setReminderDate(startDate);
        Diaper createdDiaper = diaperRepo.save(diaper);
        child.getDiaperReminders().add(createdDiaper);
        childRepo.save(child);

        return diaperMapper.toDiaperDTO(createdDiaper);
    }

    public DiaperDTO getDiaperById(Long id) {
        return diaperMapper.toDiaperDTO(findDiaperById(id));
    }

    public DiaperDTO update(DiaperDTO updateRequest, Long id) {
        Diaper diaper = findDiaperById(id);
        diaperMapper.updateDiaperFromDTO(updateRequest, diaper);
        diaper.setReminderState(reminderService.determineReminderState(new Date(),updateRequest.getReminderDate()));
        diaper.setReminderDate(updateRequest.getReminderDate());
        diaper.setId(id);

        return diaperMapper.toDiaperDTO(diaperRepo.save(diaper));
    }

    public List<DiaperDTO> getAllDiaper() {
        return diaperRepo.findAll().stream()
                .map(diaperMapper::toDiaperDTO).toList();
    }


    public void deleteDiaper(Long id) {
        Diaper diaper = findDiaperById(id);
        diaperRepo.delete(diaper);
    }


    private Diaper findDiaperById(Long id) {
        return diaperRepo.findById(id)
                .orElseThrow(() -> new DiaperNotFoundException(id));
    }


    public List<DiaperDTO> getChildDiapers(Long id){
        return diaperRepo.findByChildId(id).stream()
                .map(diaperMapper::toDiaperDTO).toList();
    }
}
