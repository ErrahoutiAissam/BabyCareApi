package com.errahouti.BabyCareApi.repository;


import com.errahouti.BabyCareApi.model.Reminder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ReminderRepo extends JpaRepository<Reminder, Long> {

    List<Reminder> findByChild_Id(Long id);
}
