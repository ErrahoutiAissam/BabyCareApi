package com.errahouti.BabyCareApi.dto.user;


import com.errahouti.BabyCareApi.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "spring")
public interface UserMapper {
//    @Mappings({
//            @Mapping(target = "tipsList", ignore = true), // Ignore tipsList for now
//            @Mapping(target = "password", ignore = true) // Ignore tipsList for now
//    })
    @Mapping(target = "tipsList", ignore = true) // Ignore tipsList for now
    User toUser(UserDTO userDTO);
    @Mapping(target = "tipsList", ignore = true) // Ignore tipsList for now
    @Mapping(target = "password", ignore = true)
    UserDTO toUserDTO(User user);

    void update(UserDTO userDTO, @MappingTarget User user);

}
