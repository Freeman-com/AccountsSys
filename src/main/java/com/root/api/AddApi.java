package com.root.api;

import com.root.model.User;
import com.root.repository.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.net.http.HttpRequest;

@Controller
public class AddApi {

    private final UserRepository userRepository;

    public AddApi(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @PostMapping("/addAPI")
    public String addApi(@ModelAttribute("user")User user, HttpSession session, HttpServletRequest request){
        String publicKey = request.getParameter("p-a-k");
        String privateKey = request.getParameter("private-a-k");


        return null;
    }
}
