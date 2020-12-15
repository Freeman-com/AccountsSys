package com.root.controllers;

import com.root.accessingdatamysql.UserRepository;
import com.root.entity.User;
import com.root.entity.UserAccount;
import com.root.role.Role;
import org.springframework.stereotype.Controller;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Controller
@RequestMapping("/")
public class MainController {

    // This means to get the bean called userRepository
    // Which is auto-generated by Spring, we will use it to handle the data
    private final UserRepository userRepository;

    public MainController(UserRepository userRepository) {
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

    @GetMapping("/login")
    public String loginPage() {
        return "login";
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

    /*@ModelAttribute("user" User user - это аннотация, которая связывает параметр метода или возвращаемое
     значение метода с именованным атрибутом модели, а затем предоставляет его веб-представлению.)*/
    @PostMapping(path = "/create")
    public String addNewUser(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return "/register";
        }
        String username1 = user.getName();
        String passwd1 = request.getParameter("passwd");

        String x = username1 + passwd1;
        byte[] c = x.getBytes();
        /* q - endpoint DB variable */
        String q = bytesToHex(c);
        user.setHex(q);

        /*checked method*/

        /*      anything code ...   */
        UserAccount userAccount = new UserAccount();
        userAccount.setEmailaccount(user.getEmail());
        userAccount.setHexaccount(q);

        userAccount.setRole("user");


        userRepository.save(user);

        request.getSession().setAttribute("user", user);
        return "redirect:login";
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse resp = (HttpServletResponse) response;
        HttpSession session = req.getSession();

        Role type = (Role) session.getAttribute("userType");
        if (type == null) {
            type = Role.USER;
            session.setAttribute("userType", type);

            return;
        }
    }

    @GetMapping("/autorisationFailedPage")
    public String autorisationFailedPage() {
        return "autorisationFailedPage";
    }


    @GetMapping("/add-api")
    public String addApi() {
        return "add-api";
    }

    @GetMapping("/buy-ipo")
    public String buyIpo() {
        return "buyipo";
    }

    @GetMapping("/buy-ico")
    public String buyIco() {
        return "buyico";
    }

    @GetMapping("/trade")
    public String tradePage() {
        return "trade";
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
