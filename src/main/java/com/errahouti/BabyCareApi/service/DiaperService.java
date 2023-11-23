package com.errahouti.BabyCareApi.service;

import com.errahouti.BabyCareApi.dto.diaper.DiaperDTO;
import com.errahouti.BabyCareApi.dto.diaper.DiaperMapper;
import com.errahouti.BabyCareApi.dto.nutrition.NutritionDTO;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.repository.DiaperRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DiaperService {

    private final DiaperRepo diaperRepo;

    private final DiaperMapper diaperMapper;


    public DiaperDTO createDiaper(DiaperDTO diaperDTO){
        return diaperMapper.toDiaperDTO(diaperRepo
                .save(diaperMapper.createDiaper(diaperDTO)));
    }

    public DiaperDTO getDiaperById(Long id) {
        return diaperMapper.toDiaperDTO(diaperRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<DiaperDTO> getAllDiaper(){
        return diaperRepo.findAll().stream()
                .map(diaperMapper::toDiaperDTO).toList();
    }
}
