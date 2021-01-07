package com.root.controllers;


import com.root.model.User;
import com.root.repository.UserRepository;
import org.junit.Assert;

import static org.junit.Assert.*;

public class RegistrationRestControllerTest {

    public UserRepository userRepository;


    RegistrationRestControllerTest(UserRepository userRepository) {
        this.userRepository = userRepository;

    }

    @org.junit.Test
    public void loginMethod() {
        String actual = "6976616e2e6d616d696368617540676d61696c2e636f6d31323331323331323331";
        User z = userRepository.findByHex(actual);

        String expected = z.toString();
//        assertEquals(actual, expected);

        Assert.assertEquals(actual, expected);
    }

    @org.junit.Test
    public void registrationPage() {
    }
}


