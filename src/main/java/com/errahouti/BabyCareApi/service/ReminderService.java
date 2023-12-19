package com.errahouti.BabyCareApi.service;



import com.errahouti.BabyCareApi.dto.activity.ActivityMapper;
import com.errahouti.BabyCareApi.dto.diaper.DiaperMapper;
import com.errahouti.BabyCareApi.dto.healthCare.HealthCareMapper;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionMapper;
import com.errahouti.BabyCareApi.dto.reminder.ReminderDTO;
import com.errahouti.BabyCareApi.dto.reminder.ReminderMapper;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.dto.tips.TipsDTO;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.*;
import com.errahouti.BabyCareApi.repository.ReminderRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.ZoneId;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ReminderService {

    private final ReminderRepo reminderRepo;
    private final ReminderMapper reminderMapper;
    private final SleepMapper sleepMapper;
    private final NutritionMapper nutritionMapper;
    private final DiaperMapper diaperMapper;
    private final HealthCareMapper healthCareMapper;
    private final ActivityMapper activityMapper;
    private final TipsService tipsService;


    public ReminderDTO createReminder(ReminderDTO reminderDTO) {
        Reminder reminder = reminderMapper.createReminder(reminderDTO);
        reminder.setReminderDate(reminderDTO.getReminderDate());
        return reminderMapper.toReminderDTO(reminderRepo.save(reminder));

    }

    public ReminderDTO getReminderById(Long id) throws NotFoundException {
        return reminderMapper.toReminderDTO(reminderRepo
                .findById(id).orElseThrow(NotFoundException::new));
    }

    public ReminderDTO updateReminder(ReminderDTO updateRequest, long id) {
        Reminder reminder = reminderRepo.findById(id).orElseThrow(NotFoundException::new);
        reminderMapper.updateReminderFromDTO(updateRequest, reminder);
        reminder.setId(id);
        reminder.setReminderDate(updateRequest.getReminderDate());
        return reminderMapper.toReminderDTO(reminderRepo.save(reminder));
    }

    public void deleteReminder(Long id) {
        Reminder reminder = reminderRepo.findById(id).orElseThrow(NotFoundException::new);
        reminderRepo.delete(reminder);
    }

    public List<ReminderDTO> getAllReminders() {
        return reminderRepo.findAll().stream()
                .map(reminderMapper::toReminderDTO).toList();
    }

    public List<Reminder> getTodayRemindersList() {
        LocalDate today = LocalDate.now();


        return reminderRepo.findAll().stream()
                .filter(reminder -> {
                    LocalDate reminderDate = reminder.getReminderDate()
                            .toInstant().atZone(ZoneId.systemDefault()).toLocalDate();

                    return reminderDate.isEqual(today);
                })
                .toList();
    }

    public List<ReminderDTO> getTodayReminders() {
        List<Reminder> reminderListToday = getTodayRemindersList();
        System.out.println(reminderListToday);

        if(reminderListToday.isEmpty()){
            return null;
        }
        return reminderTypeSwitcher(reminderListToday);
    }

    public List<ReminderDTO> reminderTypeSwitcher(List<Reminder> list){
        return list.stream()
                .map(reminder -> switch (reminder.getClass().getSimpleName()) {
                    case "Sleep" -> sleepMapper.toSleepDTO((Sleep) reminder);
                    case "Diaper" -> diaperMapper.toDiaperDTO((Diaper) reminder);
                    case "HealthCare" -> healthCareMapper.toHealthCareDTO((HealthCare) reminder);
                    case "Nutrition" -> nutritionMapper.toNutritionDTO((Nutrition) reminder);
                    case "Activity" -> activityMapper.toActivityDTO((Activity) reminder);
                    default -> throw new RuntimeException("list is empty");
                })
                .collect(Collectors.toList());
    }

    public List<ReminderDTO> getTodayRemindersCompleted(){
        return getTodayReminders().stream().filter(reminderDTO ->
                reminderDTO.getReminderState().equals(ReminderState.COMPLETED)).toList();
    }

    public List<ReminderDTO> getTodayUpcomingReminders(){
        return getTodayReminders().stream().filter(reminderDTO ->
                reminderDTO.getReminderState().equals(ReminderState.UPCOMING)).toList();
    }

    public List<ReminderDTO> getChildReminders(Long childId){
        return reminderTypeSwitcher(reminderRepo.findByChild_Id(childId));
    }

    public TodayInfo getTodayInfo(Long childId) {
        List<ReminderDTO> todayReminders = getTodayChildReminders(childId);
        int remindersCompleted = (int) todayReminders.stream()
                .filter(reminderDTO -> reminderDTO.getReminderState().equals(ReminderState.COMPLETED))
                .count();
        int remindersNotCompleted = (int) todayReminders.stream()
                .filter(reminderDTO -> !reminderDTO.getReminderState().equals(ReminderState.COMPLETED))
                .count();

        TipsDTO tips = tipsService.getRandomTip();

        return new TodayInfo(todayReminders, remindersCompleted, remindersNotCompleted, tips);
    }
    public List<ReminderDTO> getTodayChildReminders(Long childId) {
        List<Reminder> todayReminders = reminderRepo.findByChild_Id(childId).stream()
                .filter(reminder -> {
                    LocalDate reminderDate = reminder.getReminderDate().toInstant()
                            .atZone(ZoneId.systemDefault()).toLocalDate();
                    LocalDate today = LocalDate.now();
                    return reminderDate.isEqual(today);
                })
                .collect(Collectors.toList());

        return reminderTypeSwitcher(todayReminders);
    }

    ReminderState determineReminderState(Date currentDate, Date startDate) {
        int comparisonResult = currentDate.compareTo(startDate);

        if (comparisonResult < 0) {
            return ReminderState.UPCOMING;
        } else if (comparisonResult == 0) {
            return ReminderState.ONGOING;
        } else {
            return ReminderState.COMPLETED;
        }
    }



}


