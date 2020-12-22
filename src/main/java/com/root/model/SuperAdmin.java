package com.root.model;

import com.root.role.Role;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "user_account")
public class SuperAdmin extends User {


    public SuperAdmin() {

    }
}
