package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.sleep.CreateSleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.ChildProgress;
import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.repository.ChildProgressRepo;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.UserRepo;
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
    private final UserRepo userRepo;


    public ChildDTO getChildById(Long id){
        return childMapper.toChildDTO(childRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<ChildDTO> getAllChildren(){
        return childRepo.findAll().stream()
                .map(childMapper::toChildDTO).toList();
    }
    public ChildDTO createChild(ChildDTO childDTO){
        Child child = childMapper.toChild(childDTO);
        User parent = userRepo.findById(childDTO.getParentId()).orElseThrow(NotFoundException::new);
        parent.getChildren().add(child);

        child.setParent(parent);
        userRepo.save(parent);
        Child savedChild = childRepo.save(child);

        ChildProgress childProgress = getChildProgressOrCreate(savedChild.getId(), LocalDate.now().getYear());
        childProgressRepo.save(childProgress);

        return childMapper.toChildDTO(savedChild);
    }

    public ChildDTO updateChild(ChildDTO updateRequest, Long id){
        Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
        childMapper.update(updateRequest, child);
        child.setId(id);

        Child savedChild = childRepo.save(child);

        double newWeight = updateRequest.getWeight();
        int currentYear = LocalDate.now().getYear();
        ChildProgress childProgress = getChildProgressOrCreate(id, currentYear);

        Map<Integer, Double> growthData = childProgress.getGrowthData();
        growthData.put(currentYear, newWeight);
        childProgress.setGrowthData(growthData);
        childProgressRepo.save(childProgress);

        return childMapper.toChildDTO(savedChild);
    }

    private ChildProgress getChildProgressOrCreate(Long id, int year) {
        List<ChildProgress> childProgressList = childProgressRepo.findByChild_Id(id);
        Optional<ChildProgress> optionalChildProgress = childProgressList.stream()
                .filter(progress -> progress.getGrowthData().containsKey(year))
                .findFirst();

        return optionalChildProgress.orElseGet(() -> {
            ChildProgress newChildProgress = new ChildProgress();
            newChildProgress.setChild(childMapper.toChild(getChildById(id)));
            newChildProgress.setGrowthData(Map.of(year, getChildById(id).getWeight()));
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
