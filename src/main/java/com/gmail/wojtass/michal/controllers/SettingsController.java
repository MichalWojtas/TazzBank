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
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class SettingsController {

    @Autowired
    UserRepository repo;

    private User getUser2(){
        Authentication auth2 = SecurityContextHolder.getContext().getAuthentication();
        String username2 = auth2.getName();
        User user2 = repo.findByUsername(username2);
        return user2;
    }

    @RequestMapping("/bank/passwordChangeForm")
    public String passwordChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "passwordChangeForm";
    }

    @PostMapping("/bank/passwordChangeForm")
    public String passwordChange(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
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

    @RequestMapping("/bank/transactionLimitForDayChangeForm")
    public String transactionLimitForDayChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "transactionLimitForDayChangeForm";
    }

    @GetMapping("/bank/transactionLimitForDayChangeForm")
    public String getTransactionLimitForDayChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "transactionLimitForDayChangeForm";
    }

    @PostMapping("/bank/transactionLimitForDayChangeForm")
    public String postTransactionLimitForDayChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword")) {
            return "transactionLimitForDayChangeForm";
        }else {
            user2.setLimitTransactionForDay(user.getLimitTransactionForDay());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @RequestMapping("/bank/transactionLimitForMonthChangeForm")
    public String transactionLimitForMonthChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "transactionLimitForMonthChangeForm";
    }

    @GetMapping("/bank/transactionLimitForMonthChangeForm")
    public String getTransactionLimitForMonthChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "transactionLimitForMonthChangeForm";
    }

    @PostMapping("/bank/transactionLimitForMonthChangeForm")
    public String postTransactionLimitForMonthChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        boolean isMonthGreaterThanDay = user.getLimitTransactionForMonth() >= user2.getLimitTransactionForDay();
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(!isMonthGreaterThanDay){
            bindingResult.rejectValue("limitTransactionForMonth","error_code","Month limit can't be lower than day limit");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("limitTransactionForMonth")) {
            return "transactionLimitForMonthChangeForm";
        }else {
            user2.setLimitTransactionForMonth(user.getLimitTransactionForMonth());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @RequestMapping("/bank/addressChangeForm")
    public String addressChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "addressChangeForm";
    }

    @GetMapping("/bank/addressChangeForm")
    public String getAddressChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "addressChangeForm";
    }

    @PostMapping("/bank/addressChangeForm")
    public String postAddressChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("address")) {
            return "addressChangeForm";
        }else {
            user2.setAddress(user.getAddress());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @RequestMapping("/bank/addressCorrespondenceChangeForm")
    public String addressCorrespondenceChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "addressCorrespondenceChangeForm";
    }

    @GetMapping("/bank/addressCorrespondenceChangeForm")
    public String getAddressCorrespondenceChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "addressCorrespondenceChangeForm";
    }

    @PostMapping("/bank/addressCorrespondenceChangeForm")
    public String postAddressCorrespondenceChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("addressForCorrespondence")) {
            return "addressCorrespondenceChangeForm";
        }else {
            user2.setAddressForCorrespondence(user.getAddressForCorrespondence());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @RequestMapping("/bank/emailChangeForm")
    public String emailChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "emailChangeForm";
    }

    @GetMapping("/bank/emailChangeForm")
    public String getEmailChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "emailChangeForm";
    }

    @PostMapping("/bank/emailChangeForm")
    public String postEmailChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(repo.existsByEmail(user.getEmail())) {
            bindingResult.rejectValue("email", "err_code", "That email already exists.");
        }
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("email")) {
            return "emailChangeForm";
        }else {
            user2.setEmail(user.getEmail());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

    @RequestMapping("/bank/phoneNumberChangeForm")
    public String phoneNumberChangeForm(Model model, @SessionAttribute("loggedUser") User user){
        model.addAttribute("loggedUser",user);
        return "phoneNumberChangeForm";
    }

    @GetMapping("/bank/phoneNumberChangeForm")
    public String getPhoneNumberChangeForm(Model model, @ModelAttribute("user") User user){
        model.addAttribute("user", user);
        return "phoneNumberChangeForm";
    }

    @PostMapping("/bank/phoneNumberChangeForm")
    public String postPhoneNumberChangeForm(@ModelAttribute("user") @Validated User user, BindingResult bindingResult){
        User user2 = getUser2();
        boolean checkPassword = BCrypt.checkpw(user.getConfirmPassword(),user2.getPassword());
        if(!checkPassword){
            bindingResult.rejectValue("confirmPassword", "error_code", "It's not your actual password");
        }
        if(bindingResult.hasFieldErrors("confirmPassword") || bindingResult.hasFieldErrors("phoneNumber")) {
            return "phoneNumberChangeForm";
        }else {
            user2.setPhoneNumber(user.getPhoneNumber());
            repo.save(user2);
            return "redirect:/bank";
        }
    }

}
