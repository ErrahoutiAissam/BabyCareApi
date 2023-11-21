package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.repository.TipsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TipsService {

    private final TipsRepo tipsRepo;
}
