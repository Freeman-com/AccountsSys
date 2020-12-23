package com.root.web.controllers;

import com.root.dao.UserRepository;
import com.root.model.User;
import com.root.service.UserService;
import com.root.web.dto.UserDto;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

@Controller
@RequestMapping("/")
public class RegistrationRestController {

    private final UserService userService;

    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    final
    UserRepository userRepository;

    public RegistrationRestController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    @GetMapping(path = "/")
    public String indexPage(){
        return "index";
    }

    @GetMapping("/registrationPage")
    public String registrationPage(@ModelAttribute("user") User user) {

        return "register";
    }

    @RequestMapping(method = RequestMethod.POST, path = "/create")
    public String registerUserAccount(@Valid final UserDto accountDto, @ModelAttribute("user") User user,
                                      final HttpServletRequest request, BindingResult bindingResult) {
            if (bindingResult.hasErrors()){
                return "/register";
            }
        LOGGER.debug("Registering user account with information: {}", accountDto);

            String username1 = user.getName();
            String passwd1 = request.getParameter("passwd");

        String x = username1 + passwd1;
        byte[] c = x.getBytes();
        String q = bytesToHex(c);
        user.getHex(q);

        final User registered = userService.registerNewUserAccount(accountDto);
        userService.addUserLocation(registered, getClientIP(request));

            return "redirect:admin";
    }

    private String getClientIP(HttpServletRequest request) {
        final String xfHeader = request.getHeader("X-Forwarded-For");
        if (xfHeader == null) {
            return request.getRemoteAddr();
        }
        return xfHeader.split(",")[0];
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
