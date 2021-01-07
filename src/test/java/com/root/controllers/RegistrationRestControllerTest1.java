package com.root.controllers;

import com.root.model.User;
import com.root.repository.UserRepository;
import org.junit.Assert;
import org.junit.Before;
import org.junit.jupiter.api.Test;

class RegistrationRestControllerTest1 {

    private User user;
    private UserRepository userRepository;

    @Before
    public void init() {
        this.user = new User(12, "Ivan Mamichau", "ivan.mamichau@gmail.com", "CUSTOMER",
                "0:0:0:0:0:0:0:1", "6976616e2e6d616d696368617540676d61696c2e636f6d31323331323331323331");
    }


    @Test
    void loginMethod() {
        User user2 = (User) userRepository.findAll();

        Assert.assertEquals(this.user, user2);

    }
}