package ru.maxima.springboottest.controllers;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.maxima.springboottest.models.Person;
import ru.maxima.springboottest.service.PeopleService;
import ru.maxima.springboottest.validation.PersonValidator;

@Controller
@RequestMapping("/auth")
public class AuthController {

    private final PersonValidator validator;
    private final PeopleService service;

    @Autowired
    public AuthController(PersonValidator validator, PeopleService service) {
        this.validator = validator;
        this.service = service;
    }

    @GetMapping("/login")
    public String giveToUserLoginPage() {
        return "auth/login-page";
    }

    @GetMapping("/registration")
    public String giveToUserRegistrationPage(@ModelAttribute("person") Person person) {
        return "auth/registration-page";
    }

    @PostMapping("/registration")
    public String performPage(@ModelAttribute("person") @Valid Person person,
                              BindingResult result) {

        validator.validate(person, result);

        if (result.hasErrors()) {
            return "auth/registration-page";
        }

        service.save(person);

        return "redirect:/auth/login-page";
    }

}
