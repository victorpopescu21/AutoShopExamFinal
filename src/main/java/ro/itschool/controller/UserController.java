package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import ro.itschool.entity.Role;
import ro.itschool.entity.User;
import ro.itschool.repository.RoleRepository;

import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

import java.util.Optional;

@RestController
@RequestMapping(value = "/user")
@PreAuthorize("hasRole('ROLE_ADMIN')")
public class UserController {



    @Autowired
    private UserService userService;

    @Autowired
    private RoleRepository roleRepository;


    //--------- GET all users for ADMINs only ------------------------------
//    @GetMapping("/users")
//    public String getAllUsers(Model model, String keyword) throws Exception {
//        model.addAttribute("users", userService.searchUser(keyword));
//        //   model.addAttribute("roles", roleRepository.findAll().stream().map(Role::getName).toList());
//        model.addAttribute("adminRole", roleRepository.findAll()
//                .stream()
//                .map((Role::getName))
//                .filter(role -> role.equals(Constants.ROLE_ADMIN))
//                .findAny()
//                .orElseThrow(() -> new Exception("User with admin roles not found")));
//
//        return "all-users";
//    }

    //---------DELETE a user by id for ADMINs only ------------------------------
    @RequestMapping(path = "/delete/{id}")
    public String deleteUserById(Model model, @PathVariable("id") Long id) {
        userService.deleteById(id);
        return Constants.REDIRECT_TO_PRODUCTS;
    }

    //----------ADD ADMIN ROLE TO USER----------------------------------------------
    @RequestMapping("/add-admin-role/{id}")
    public String addAdminRoleToUser(@PathVariable("id") Long id) {
        final Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            final Role role = roleRepository.findByName(Constants.ROLE_ADMIN);
            user.get().getRoles().add(role);
            userService.updateUser(user.get());
            return "redirect:/users";
        }
        return ("USER not found for this id : " + id);
    }
    //------------------------------------------------------------------------------

    //----------REMOVE ADMIN ROLE FROM USER-----------------------------------------
    @RequestMapping("/remove-admin-role/{id}")
    public String removeAdminRoleFromUser(@PathVariable("id") Long id) {
        String username = getCurrentUserDetails();
        final Optional<User> user = userService.findById(id);
        if (user.isPresent()) {
            final Role role = roleRepository.findByName(Constants.ROLE_ADMIN);
            user.get().getRoles().remove(role);
            userService.updateUser(user.get());
            //Check if logged user is the same as selected user
            if (username.equals(user.get().getUsername()))
                return "redirect:/logout";
            else
                return "redirect:/users";
        }
        return ("USER not found for this id : " + id);
    }

    //------------------------------------------------------------------------------

    //----------------------PRIVATE METHODS-----------------------------------------
    private String getCurrentUserDetails() {
        Authentication loggedInUser = SecurityContextHolder.getContext().getAuthentication();
        return loggedInUser.getName();
    }


}
