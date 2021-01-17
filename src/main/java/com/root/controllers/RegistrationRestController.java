package com.root.controllers;

import com.root.model.User;
import com.root.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@Controller
public class RegistrationRestController {

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    private final UserRepository userRepository;

    public RegistrationRestController(UserRepository userRepository) {

        this.userRepository = userRepository;
    }

    @GetMapping("/index2")
    public String toIndex2(HttpServletRequest request, @ModelAttribute("user") User user) {
        return "index2";
    }

    @GetMapping("/")
    public String toIndexPage(@ModelAttribute("user") User user, HttpServletRequest request) {
        HttpSession session = request.getSession();
        session.getAttribute("userData");
        if (session.getAttribute("userData") == null) {
            return "redirect:index";
        } else return "redirect:index2";
    }

    @GetMapping("/index")
    public String indexPageToo() {
        return "index";
    }

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


        if (userRepository.findByHex(q) != null) {
            User userFromDb = userRepository.findByEmail(user.getEmail());
            user.setName(userFromDb.getName());

            HttpSession httpSession = request.getSession();
            httpSession.setAttribute("userData", userFromDb);
            return "redirect:myasset";
        } else {
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
        return "login1";
    }


    @GetMapping("/myasset")
    public String toMyAsset(HttpServletRequest request, @ModelAttribute("user") User user) {

//        User ivan = (User) request.getSession().getAttribute("userData");
//        LOGGER.info("'{}'", ivan);

        return "myasset";
    }

    @GetMapping("/myaccounts")
    public String toMyAcc(@ModelAttribute("user") User user, HttpServletRequest request) {
        return "myacc";
    }

    @GetMapping("/logout")
    public String logOut(HttpServletRequest request, HttpServletResponse response) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
            if (authentication != null) {
                new SecurityContextLogoutHandler().logout(request, response, authentication);
            }
        return "redirect:index";
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
