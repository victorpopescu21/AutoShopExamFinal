package ro.itschool.controller.mapper;


import ro.itschool.controller.model.MyUserDTO;

import ro.itschool.entity.Role;
import ro.itschool.entity.User;

public class MyUserMapper {
    public static MyUserDTO convertToDTO(User myUser) {
        MyUserDTO myUserDTO = new MyUserDTO();
        myUserDTO.setId(myUser.getId());
        myUserDTO.setUsername(myUser.getUsername());
        myUserDTO.setEmail(myUser.getEmail());
        myUserDTO.setFullName(myUser.getFullName());


        myUserDTO.setRoles(myUser.getRoles().stream()
                .map(Role::getName)
                .toList());

        return myUserDTO;
    }
}