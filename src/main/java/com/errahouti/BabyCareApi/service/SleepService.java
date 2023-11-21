package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.repository.SleepRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class SleepService {

    private final SleepRepo sleepRepo;
}
