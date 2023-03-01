package ro.itschool.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ro.itschool.entity.Contact;
import ro.itschool.entity.User;
import ro.itschool.exception.UserNotFound;
import ro.itschool.repository.ContactRepository;
import ro.itschool.util.Constants;

@Controller
public class ContactController {

    @Autowired
    ContactRepository contactRepository;

    @GetMapping("/contact-message")
    public String getContact(Model model, String keyword) {
        model.addAttribute("contacts", contactRepository.searchContact(keyword));
        return Constants.CONTACT_MESSAGE;
    }

    @GetMapping("/contact")
    public String saveContact(Model model) {
        model.addAttribute("contactObject", new Contact());
        return Constants.CONTACT;
    }

    //TODO
    @PostMapping("/contact")
    public String saveContact2(@ModelAttribute Contact contact, Model model, User user) throws UserNotFound {
        model.addAttribute("contactObject", contact);
        if (contact.getUsername().equals((user.getUsername()))){
            contactRepository.save(contact);
            return Constants.REDIRECT_CONTACT;
        } else
            throw new UserNotFound("Wrong username");
    }

    @RequestMapping(path = "/delete-contact/{id}")
    public String deleteContact(@PathVariable("id") Integer id) {
        contactRepository.deleteById(id);
        return Constants.REDIRECT_CONTACT_MESSAGE;
    }
}