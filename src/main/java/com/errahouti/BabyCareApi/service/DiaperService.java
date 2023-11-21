package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.repository.DiaperRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class DiaperService {

    private final DiaperRepo diaperRepo;
}
