package com.root.model;

import com.root.role.Role;

import javax.persistence.*;

//@MappedSuperclass
@Entity
public class User {

    @Id
    private Long id;
    private String name;
    private String email;
    private Role userRole;
    private String hex;

    public User(Long id, String name, String email, Role userRole) {

    }

    public User() {

    }

    public String getHex(String q) {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
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
