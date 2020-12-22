package com.root.model;

import com.root.role.Role;

import javax.persistence.*;

//@MappedSuperclass
@Entity
public class User {

    @Id
    @Column(name = "id")
    private Long ID;

    private String name;

    private String email;

    private Role userRole;

    public User(Long id, String name, String email, Role userRole) {

    }

    public User() {

    }


    public Long getID() {
        return ID;
    }

    public void setID(final Long ID) {
        this.ID = ID;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String email) {
        this.email = email;
    }

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }

}
