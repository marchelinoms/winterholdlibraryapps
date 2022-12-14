package com.indocyber.Controllers.MVCControllers;

import com.indocyber.DTO.Account.RegisterDto;
import com.indocyber.Services.Interfaces.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;


@Controller
@RequestMapping("/account")
public class AccountController {

    @Autowired
    private AccountService accountService;

    @GetMapping("/loginForm")
    public String loginForm(Model model) {
        return "login-form";
    }

    @GetMapping("/accessDenied")
    public String accessDenied(Model model) {
        return "access-denied";
    }

    @GetMapping("/registerForm")
    public String registerForm(Model model) {
        RegisterDto dto = new RegisterDto();
        model.addAttribute("account", dto);
        return "register-form";
    }

    @PostMapping("/register")
    public String register(@Valid @ModelAttribute("account") RegisterDto dto, Model model){

        accountService.registerAccount(dto);
        return "redirect:/account/loginForm";
    }

}
