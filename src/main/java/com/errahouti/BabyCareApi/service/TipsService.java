package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.tips.TipsDTO;
import com.errahouti.BabyCareApi.dto.tips.TipsMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Tips;
import com.errahouti.BabyCareApi.repository.TipsRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TipsService {

    private final TipsRepo tipsRepo;
    private final TipsMapper tipsMapper;

    public TipsDTO getTipById(Long id){
        return tipsMapper.toTipsDTO(tipsRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public TipsDTO createTip(TipsDTO tipsDTO){
        return tipsMapper.toTipsDTO(tipsRepo
                .save(tipsMapper.createTips(tipsDTO)));
    }

    public TipsDTO updateTip(TipsDTO updateRequest, Long id){
        Tips tip = tipsRepo.findById(id).orElseThrow(NotFoundException::new);
        tipsMapper.updateTipsFromDTO(updateRequest, tip);
        tip.setId(id);
        return tipsMapper.toTipsDTO(tipsRepo.save(tip));
    }

    public void deleteTip(Long id){
        Tips tip = tipsRepo.findById(id).orElseThrow(NotFoundException::new);
        tipsRepo.delete(tip);
    }

    public List<TipsDTO> getAllTips(){
        return tipsRepo.findAll().stream()
                .map(tipsMapper::toTipsDTO).toList();
    }
}
