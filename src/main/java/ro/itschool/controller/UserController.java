package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.exception.UserNotFound;
import ro.itschool.repository.RoleRepository;
import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

@Controller

public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


//    --------- GET all users for ADMINs only ------------------------------
    @GetMapping("/users")
    public String getAllUsers(Model model, String keyword) throws Exception {
        model.addAttribute("users", userService.searchUser(keyword));// model.addAttribute pune la dispozitia Thymleafului rezultatul acestei cautari
        //   model.addAttribute("roles", roleRepository.findAll().stream().map(Role::getName).toList());
//        model.addAttribute("adminRole", roleRepository.findAll()
//                .stream()
//                .map((Role::getName))
//                .filter(role -> role.equals(Constants.ROLE_ADMIN))
//                .findAny()
//                .orElseThrow(() -> new Exception("User with admin roles not found")));

        return "all-users";
    }

    //---------DELETE a user by id for ADMINs only ------------------------------
    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id) throws UserNotFound {
        if(userService.findById(id).isPresent()){
            userService.deleteById(id);
        }else throw new UserNotFound("User not found to be deleted!");

        return Constants.REDIRECT_TO_USERS;
    }








}
