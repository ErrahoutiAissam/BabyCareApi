package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.model.Activity;
import com.errahouti.BabyCareApi.repository.ActivityRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ActivityService {

    private final ActivityRepo activityRepo;
}
