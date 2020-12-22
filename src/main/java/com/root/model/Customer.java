package com.root.model;

import com.root.dao.UserRepository;
import com.root.role.Role;

public class Customer extends User  {

    UserRepository repository;

    public Customer(Long ID, String name, String email, Role role) {
        super(ID, name, email, role);
    }



}
