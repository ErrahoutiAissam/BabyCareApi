package com.errahouti.BabyCareApi.service;


import com.errahouti.BabyCareApi.controller.auth.RegisterRequest;
import com.errahouti.BabyCareApi.dto.child.ChildDTO;
import com.errahouti.BabyCareApi.dto.child.ChildMapper;
import com.errahouti.BabyCareApi.dto.user.UserDTO;
import com.errahouti.BabyCareApi.dto.user.UserMapper;
import com.errahouti.BabyCareApi.exception.AlreadyExistsException;
import com.errahouti.BabyCareApi.exception.EmailAlreadyExistsException;
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

import java.io.NotActiveException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepo userRepo;
    private final ChildRepo childRepo;
    private final ChildService childService;

    private final UserMapper userMapper;
    private final ChildMapper childMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepo.findByEmail(username)
                .orElseThrow(()-> new UsernameNotFoundException("username not found"));
    }


    public List<ChildDTO> getChildren(Long id){
        return userRepo.findById(id).orElseThrow(NotFoundException::new)
                .getChildren().stream().map(childMapper::toChildDTO).toList();
    }
    public UserDTO addUser(RegisterRequest request){
        User user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encoder().encode(request.getPassword()))
                .build();

        return userMapper.toUserDTO(userRepo.save(user)) ;

    }


    public ChildDTO getChild(Long id, Long parentId){
        Child child = childRepo.findByIdAndParentId(id, parentId).orElseThrow(NotFoundException::new);
        return childMapper.toChildDTO(child);


    }

    public void deleteUser(Long id){
        User user = userRepo.findById(id).orElseThrow(NotFoundException::new);
        userRepo.delete(user);
    }

    public UserDTO updateUser(UserDTO updateRequest, Long id){

        User user = userRepo.findById(id).orElseThrow(NotFoundException::new);
        if(!Objects.equals(user.getUsername(), updateRequest.getEmail())){
            throw new NotFoundException("Email not found");
        }


        userMapper.update(updateRequest, user);
        user.setPassword(passwordEncoder.encoder().encode(updateRequest.getPassword()));
       return userMapper.toUserDTO(userRepo.save(user));
    }


    public UserDTO getUserById(Long id){
        return userMapper.toUserDTO(userRepo.findById(id)
                .orElseThrow(NotFoundException::new));
    }


    public List<UserDTO> getUsers(){
        return userRepo.findAll().stream().
                map(userMapper::toUserDTO).collect(Collectors.toList());
    }

    @Transactional
    public void addChildren(List<Long> childListIds, Long parentId){
        User user = userMapper.toUser(getUserById(parentId));

        for(Long id : childListIds){
            Child child = childRepo.findById(id).orElseThrow(NotFoundException::new);
            user.getChildren().add(child);
        }
        userRepo.save(user);
    }

     @Transactional
    public void addChild(Long childId, Long parentId) throws NotFoundException, AlreadyExistsException {
        User parent = userRepo.findById(parentId).orElseThrow(NotFoundException::new);
        Child child = childRepo.findById(childId).orElseThrow(NotFoundException::new);

        if(!parent.getChildren().contains(child)){
            parent.getChildren().add(child);
        }
        else throw new AlreadyExistsException("this child already exists");

        userRepo.save(parent);


    }

    @Transactional
    public void addChild(ChildDTO child, Long parentId) throws NotFoundException, AlreadyExistsException {
        User parent =  userRepo.findById(parentId).orElseThrow(NotFoundException::new);
        Long childId = childService.createChild(child).getId();
        Child child1 = childRepo.findById(childId).orElseThrow(NotFoundException::new);
        parent.getChildren().add(child1);
        child1.setParent(parent);
        userRepo.save(parent);
    }

    // TODO: add child

    public void removeChild(Long id, Long parentId){
        User user = userMapper.toUser(getUserById(parentId));
        if(!user.getChildren().isEmpty()){
            user.getChildren().remove(childMapper.toChild(childService.getChildById(id)));
        }
        userRepo.save(user);

    }


}
