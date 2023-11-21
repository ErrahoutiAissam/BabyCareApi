package com.errahouti.BabyCareApi.dto.user;

import com.errahouti.BabyCareApi.model.Child;
import com.errahouti.BabyCareApi.model.Tips;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Set;

@Data
@NoArgsConstructor
public class UserDTO {
    private String firstName;
    private String lastName;
    private String username;
    private String phone;
    private String password;
    private List<Child> children;
    private Set<Tips> tipsList;


}
