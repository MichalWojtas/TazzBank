package com.gmail.wojtass.michal.controllers;

import com.gmail.wojtass.michal.model.User;
import com.gmail.wojtass.michal.otherMethods.WelcomeTextGenerator;
import com.gmail.wojtass.michal.services.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {

    @Autowired
    UserRepository repo;

    @RequestMapping("/bank/passwordChangeForm")
    public String passwordChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "passwordChangeForm";
    }

    @PostMapping("/bank/passwordChangeForm")
    public String passwordChange(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String username2 = auth2.getName();
        User user2 = repo.findByUsername(username2);
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("b4encryptPassword")) {
            return "passwordChangeForm";
        }else {
            user2.setPassword(user.bcryptPassword(user.getB4encryptPassword()));
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @GetMapping(value = "/bank/passwordChangeForm")
    public String getPasswordChangeForm(Model model, @ModelAttribute("user") User user, HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession();
        WelcomeTextGenerator welcomeTextGenerator = new WelcomeTextGenerator();
        session.setAttribute("welcomeText",welcomeTextGenerator.generate());
        model.addAttribute("user", user);
        return "passwordChangeForm";
    }
}
