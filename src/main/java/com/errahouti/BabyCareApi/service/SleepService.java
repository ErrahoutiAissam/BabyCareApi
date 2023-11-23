package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.sleep.SleepDTO;
import com.errahouti.BabyCareApi.dto.sleep.SleepMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.repository.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
    private final SleepMapper sleepMapper;


    public SleepDTO getSleepById(Long id)  {
        return sleepMapper.toSleepDTO(sleepRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public SleepDTO createSleep(SleepDTO sleepDTO){
        return sleepMapper.toSleepDTO(sleepRepo
                .save(sleepMapper.createSleep(sleepDTO)));
    }

    public List<SleepDTO> getAllSleep(){
        return sleepRepo.findAll().stream()
                .map(sleepMapper::toSleepDTO).toList();
    }

}
