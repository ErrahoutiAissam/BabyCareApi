package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.ChildProgress;
import com.errahouti.BabyCareApi.repository.ChildProgressRepo;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepo childRepo;
    private final ChildProgressRepo childProgressRepo;
    private final ChildMapper childMapper;
    private final SleepMapper sleepMapper;


    public ChildDTO getChildById(Long id){
        return childMapper.toChildDTO(childRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public ChildDTO createChild(ChildDTO childDTO){
        return childMapper.toChildDTO(childRepo
                .save(childMapper.toChild(childDTO)));
    }

    public List<ChildDTO> getAllChildren(){
        return childRepo.findAll().stream()
                .map(childMapper::toChildDTO).toList();
    }


    public ChildDTO updateChild(ChildDTO updateRequest, Long id){
        Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
        childMapper.update(updateRequest, child);
        child.setId(id);

        double newWeight = updateRequest.getWeight();
        child.setWeight(newWeight);
        childRepo.save(child);
        int currentYear = LocalDate.now().getYear();
        ChildProgress childProgress = getChildProgressOrCreate(id, currentYear);
        Map<Integer, Double> growthData = childProgress.getGrowthData();
        growthData.put(currentYear, newWeight);
        childProgress.setGrowthData(growthData);
        childProgressRepo.save(childProgress);
        return childMapper.toChildDTO(child);
    }

    private ChildProgress getChildProgressOrCreate(Long id, int year) {
        Child child = childMapper.toChild(getChildById(id));
        List<ChildProgress> childProgressList = childProgressRepo.findByChild_Id(id);
        // Filter the list to find an entry for the specified year
        Optional<ChildProgress> optionalChildProgress = childProgressList.stream()
                .filter(progress -> progress.getGrowthData().containsKey(year))
                .findFirst();

        return optionalChildProgress.orElseGet(() -> {
            // If no entry found for the year, create a new ChildProgress entry
            ChildProgress newChildProgress = new ChildProgress();
            newChildProgress.setChild(child);
            newChildProgress.setGrowthData(Map.of(year, child.getWeight()));
            return newChildProgress;
        });
    }

    public void deleteChild(Long id){
        Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
        childRepo.delete(child);
    }
    @Transactional
    public void addSleepReminder(CreateSleepDTO sleepDTO, ChildDTO childDTO) {

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getSleepReminders().add(sleepMapper.createSleep(sleepDTO));
        childRepo.save(child);
    }


    /*

    @Transactional
    public void addNutritionReminder(NutritionDTO nutritionDTO, Long id){

        Child child = childRepo.findById(id)
                .orElseThrow(NotFoundException::new);
        child.getNutritionReminders().add(nutritionMapper.createNutrition(nutritionDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addSleepReminder(CreateSleepDTO sleepDTO, ChildDTO childDTO) {

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getSleepReminders().add(sleepMapper.createSleep(sleepDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addActivityReminder(ActivityDTO activityDTO, ChildDTO childDTO){

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getActivityReminders().add(activityMapper.createActivity(activityDTO));
        childRepo.save(child);
    }
    @Transactional
    public void addDiaperReminder(DiaperDTO diaperDTO, ChildDTO childDTO){

        Child child = childRepo.findById(childDTO.getId())
                .orElseThrow(NotFoundException::new);
        child.getDiaperReminders().add(diaperMapper.createDiaper(diaperDTO));
        childRepo.save(child);
    }
     */



}
