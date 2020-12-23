package com.root.controllers;

import com.root.dao.UserRepository;
import com.root.model.User;
import com.root.role.Role;
import com.root.security.ActiveUserStore;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/")
public class RegistrationRestController {

    final
    ActiveUserStore activeUserStore;

    Role role;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    final
    UserRepository userRepository;


    public RegistrationRestController(UserRepository userRepository, ActiveUserStore activeUserStore) {
        this.userRepository = userRepository;

        this.activeUserStore = activeUserStore;
    }

    @GetMapping()
    public String indexPage() {
        return "index";
    }

    @GetMapping("/index")
    public String indexPageToo() {
        return "index";
    }

    @GetMapping("/login")
    public String loginPage(@ModelAttribute("user") User user) {
        return "login";
    }

    @PostMapping(path = "/click_login")
    public String loginMethod(@ModelAttribute("user") User user, Model model, HttpServletRequest request) {

        model.addAllAttributes("user", activeUserStore.getUsers());

        String email = user.getEmail();
        String password2 = request.getParameter("passwd");
        String x = email + password2;
        byte[] c = x.getBytes();
        String q = bytesToHex(c);


        if (userRepository.findByHex(q) == null) {
            return "redirect:login";
        } else
            user.getEmail();
            return "admin";
    }

    @GetMapping("/registrationPage")
    public String registrationPage(@ModelAttribute("user") User user) {

        return "register";
    }

    @GetMapping("/admin")
    public String adminPage(User user) {

        // if ()
        return "forward:register";
    }

    @PostMapping("/create")
    public String registerUserAccount(@ModelAttribute("user") User user, HttpServletRequest request,
                                      BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/register";
        }

        String passwd1 = request.getParameter("passwd");
        String email = user.getEmail();

        if (userRepository.findByEmail(email) != null) {
            return "redirect:login";
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
}
