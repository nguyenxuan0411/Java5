package com.example.demo.controller;

import com.example.demo.dto.ChangePass;
import com.example.demo.dto.ForgotPass;
import com.example.demo.dto.UserDTO;
import com.example.demo.model.Account;
import com.example.demo.service.account.IAccountService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.net.HttpCookie;
import java.util.Optional;

@Controller
@RequestMapping("/user")
public class AccountController {
    @Autowired
    public JavaMailSender emailSender;
    @Autowired
    HttpServletRequest request;
    @Autowired
    IAccountService service;
    @Autowired
    HttpSession session;
    HttpCookie cookie;

    @PostMapping("/login")
    public String login(HttpServletResponse response, Model model, @Validated @ModelAttribute(name = "user") UserDTO userDTO) {
        try {
            Optional<Account> userOptional = service.findByUserName(userDTO.getUsername());
            if (userOptional.isPresent()) {
                Account user = userOptional.get();
                if (user.getPassword().equals(userDTO.getPassword())) {
                    session.setAttribute("user", user);
                    Cookie usernameCookie;
                    if (userDTO.isRemember()) {
                        usernameCookie = new Cookie("username", String.valueOf(user.getUsername()));
                        usernameCookie.setMaxAge(24 * 60 * 60); // 24 hours in seconds
                    } else {
                        usernameCookie = new Cookie("username", "");
                        usernameCookie.setMaxAge(0); // Remove the cookie
                    }
                    usernameCookie.setPath("/");
                    response.addCookie(usernameCookie);

                    if (user.getAdmin()) {
                        return "redirect:/sac/hien-thi";
                    } else {
                        return "redirect:/product/show";
                    }
                }
            }
            model.addAttribute("error", "Invalid username or password!!");
            return "login";
        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }




    @GetMapping("/register")
    public String showRegistrationForm(Model model){
        Account user = new Account();
        model.addAttribute("user", user);
        return "register";
    }
    @GetMapping("/login")
    public String showLoginForm(Model model){

        Account user = new Account();
        model.addAttribute("user", user);
        return "login";
    }

    // handler method to handle user registration form submit request
    @PostMapping("/register/save")
    public String registration(@Validated @ModelAttribute("user") Account user,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "register";
        }
        user.setUsername(service.nameId(user.getFullname()));
        service.save(user);
        return "redirect:/user/login";
    }
    @PostMapping("/capnhat")
    public String capnhat(@Validated @ModelAttribute("user") Account user,
                               BindingResult result,
                               Model model){

        if(result.hasErrors()){
            model.addAttribute("user", user);
            return "capnhat";
        }

        service.capnhat(user);
        return "redirect:/user/login";
    }
    @GetMapping("/capnhat")
    public String capnhatform(@Validated @ModelAttribute("user") Account user,
                          BindingResult result,
                          Model model){

        return "capnhat";
    }

    // handler method to handle list of users
//    @GetMapping("/users")
//    public String users(Model model){
//        List<Account> users = accountService.getAllUser();
//        model.addAttribute("users", users);
//        return "list_account";
//    }
    @GetMapping("/logout")
    public String logout() {
        session.invalidate(); // Xóa session và hủy bỏ nó
        return "redirect:/user/login";
    }
    @GetMapping("/changePass")
    public String getForm(@ModelAttribute("changePass")ChangePass changePass,Model model){
        return "changPassword";
    }
    @PostMapping("/changePass")
    public String change(@ModelAttribute("changePass")ChangePass changePass,Model model){

        if (!changePass.getConfirmPassword().equals(changePass.getPassword())) {
            model.addAttribute("error", "New password and confirm password didn't matched!!");
        } else if (changePass.getPassword().equals("") || changePass.getConfirmPassword().equals("")) {
            model.addAttribute("error", "New password or confirm can't be empty!!");
        } else {
            System.out.println( changePass.getCurrentPassword());
            System.out.println( changePass.getPassword());
            service.changePassword(changePass.getUsername(), changePass.getCurrentPassword(), changePass.getPassword());
            model.addAttribute("message", "User password updated!!");
        }
		return "changPassword";
}
    @PostMapping("/forgot")
    public String forgot(Model model,@ModelAttribute("quen") ForgotPass forgotPass){
        Account account=service.findByUserName(forgotPass.getUsername()).get();
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(forgotPass.getEmail());
        message.setSubject("Test Simple Email");
        message.setText("Pass của bạn là:"+account.getPassword());
        this.emailSender.send(message);
        model.addAttribute("quen", forgotPass);
        return "redirect:/user/login";
    }
    @GetMapping("/forgot")
    public String forgotForm(Model model,@ModelAttribute("quen") ForgotPass forgotPass){
        return "quenPass";
    }


}