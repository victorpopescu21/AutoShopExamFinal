package ro.itschool.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import ro.itschool.util.Constants;

@Controller
public class AboutController {

    @RequestMapping(value = "/about")
    public String returnSomething(){
        return Constants.ABOUT;
    }
}
