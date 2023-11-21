package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ChildService {

    private final ChildRepo childRepo;
    private final ChildMapper childMapper;


    public ChildDTO getChildById(Long id) throws NotFoundException {
        return childMapper.toChildDTO(childRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }

    public List<ChildDTO> getAllChildren(){
        return childRepo.findAll().stream()
                .map(childMapper::toChildDTO).toList();
    }


}
