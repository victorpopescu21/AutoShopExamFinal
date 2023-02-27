package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import ro.itschool.entity.User;
import ro.itschool.repository.RoleRepository;
import ro.itschool.service.RoleService;
import ro.itschool.service.UserService;
import ro.itschool.util.Constants;

import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    UserService userService;
    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private RoleService roleService;

    @GetMapping(value = "/register")// should work with DTO, not with Entity
    public String registerForm(Model model) {
        User user = new User();
        user.setAccountNonExpired(true);
        user.setAccountNonLocked(true);
        user.setCredentialsNonExpired(true);
        user.setEnabled(false);
        model.addAttribute("user", user);
        return "register";
    }

    @PostMapping(value = "/register")
    public String registerUser(@ModelAttribute("user") @RequestBody User user) {
        if (user.getPassword().equals(user.getPasswordConfirm())) {
            user.setRoles(Set.of(roleService.findRoles(Constants.ROLE_USER)));
            userService.saveUser(user);
            return "register-success";
        } else {
            return "register";
        }
    }

}
