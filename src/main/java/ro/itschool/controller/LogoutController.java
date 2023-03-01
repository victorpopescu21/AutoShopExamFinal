package ro.itschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.util.Constants;

@Controller
public class LogoutController {

    @RequestMapping(value = {"/logout"})
    public String logout(){
        return Constants.LOGIN;
    }
}
