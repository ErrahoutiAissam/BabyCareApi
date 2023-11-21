package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.tips.TipsDTO;
import com.errahouti.BabyCareApi.dto.tips.TipsMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.repository.TipsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipsService {

    private final TipsRepo tipsRepo;
    private final TipsMapper tipsMapper;

    public TipsDTO getTipsById(Long id) throws NotFoundException {
        return tipsMapper.toTipsDTO(tipsRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public TipsDTO createTips(TipsDTO tipsDTO){
        return tipsMapper.toTipsDTO(tipsRepo
                .save(tipsMapper.createTips(tipsDTO)));
    }

    public List<TipsDTO> getAllTips(){
        return tipsRepo.findAll().stream()
                .map(tipsMapper::toTipsDTO).toList();
    }
}
