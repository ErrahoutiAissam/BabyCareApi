package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.controller.auth.RegisterRequest;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.user.UserDTO;
import com.errahouti.BabyCareApi.dto.user.UserMapper;
import com.errahouti.BabyCareApi.exception.NotFoundException;
import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.User;
import com.errahouti.BabyCareApi.repository.ChildRepo;
import com.errahouti.BabyCareApi.repository.UserRepo;
import com.errahouti.BabyCareApi.security.PasswordEncoder;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final ChildRepo childRepo;

    private final UserMapper userMapper;
    private final ChildMapper childMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByUsername(username)
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }

    public UserDTO addUser(RegisterRequest request){
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getEmail())
                .password(passwordEncoder.encoder().encode(request.getPassword()))
                .build();

        return userMapper.toUserDTO(userRepo.save(user)) ;

    }


    public UserDTO getUserById(Long id) throws NotFoundException {
        return userMapper.toUserDTO(userRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }


    public List<UserDTO> getUsers(){
        return userRepo.findAll().stream().
                map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    @Transactional
    public void addChildren(List<Long> childListIds, Long parentId) throws NotFoundException {
        User user = userRepo.findById(parentId).orElseThrow(NotFoundException::new);

        for(Long id : childListIds){
            Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
            user.getChildren().add(child);
        }
        userRepo.save(user);
    }


}
