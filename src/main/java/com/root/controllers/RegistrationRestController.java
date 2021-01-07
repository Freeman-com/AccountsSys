package com.root.controllers;

import com.root.model.User;
import com.root.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class RegistrationRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public RegistrationRestController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping()
    public String indexPage() {
        return "index";
    }

    @GetMapping("/index")
    public String indexPageToo() {
        return "index";
    }

//    @GetMapping("/login")
//    public String loginPage(@ModelAttribute("user") User user, HttpServletRequest request) {
//        String passwd1 = request.getParameter("passwd");
//        String email = user.getEmail();
//
//        String x = email + passwd1;
//        byte[] c = x.getBytes();
//        String q = bytesToHex(c);
//
//        if(userRepository.findByEmail(email) != null & userRepository.findByHex(q) != null) {
//
//        }
//        return "login";
//    }



    @GetMapping("/login1")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login1";
    }

    @PostMapping("/login1")
    public String loginMethod(HttpServletRequest request, @ModelAttribute("user") User user) {

        String passwd1 = request.getParameter("passwd");
        String email = user.getEmail();

        String x = email + passwd1;
        byte[] c = x.getBytes();
        String q = bytesToHex(c);


        if(userRepository.findByHex(q) != null) {
            user.setName(userRepository.findByEmail(user.getEmail()).getName());
            return "admin";
        }
        else {
            return "login1";
        }
    }


    @GetMapping("/registrationPage")
    public String registrationPage(@ModelAttribute("user") User user) {

        return "registration-page";
    }

    @PostMapping("/create")
    public String registerUserAccount(@ModelAttribute("user") User user, HttpServletRequest request,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "registration-page";
        }
        String passwd1 = request.getParameter("passwd");
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return "login1";
        }

        String x = email + passwd1;
        byte[] c = x.getBytes();
        String q = bytesToHex(c);
        user.setHex(q);
        user.setRole("CUSTOMER");
        user.setIp(user.getClientIP(request));
        userRepository.save(user);
        return "admin";
    }


    private static String bytesToHex(byte[] hash) {
        StringBuilder hexString = new StringBuilder();
        for (byte b : hash) {
            String hex = Integer.toHexString(0xff & b);
            if (hex.length() == 1) {
                hexString.append('0');
            }
            hexString.append(hex);
        }
        return hexString.toString();
    }

    public  String registredUserAccount(User user) {
        int id = user.getId();
        String name = user.getName();
        return null;
    }
}
