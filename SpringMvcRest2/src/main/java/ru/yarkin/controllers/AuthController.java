package ru.yarkin.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import ru.yarkin.models.database.User;
import ru.yarkin.service.UserService;
import ru.yarkin.validator.UserValidator;


@Controller
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private UserValidator userValidator;

    @GetMapping("/sign_up")
    public String getSignUp(Model model) {
        model.addAttribute("user", new User());
        return "/auth/sign_up";
    }

    @PostMapping("/sign_up")
    public String singUp(@ModelAttribute User user, BindingResult bindingResult, Model model) {
        userValidator.validate(user, bindingResult);

        if (bindingResult.hasErrors()) {
            model.addAttribute("errors", bindingResult.getAllErrors());
            return "/auth/sign_up";
        }

        userService.add(user);

        return "redirect:/start/menu";
    }

    @GetMapping("/login")
    public String login(String error, Model model) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect.");
        }

        return "auth/sign_in";
    }
}
