package com.root.model;

import com.root.role.Role;

import javax.persistence.*;

@Entity
@Table(name = "user_account")
public class UserAccount {

    @Id
    @Column(name = "id", unique = true, nullable = false)
    @GeneratedValue(strategy = GenerationType.AUTO)

    private Long ID;
    private String name;
    private String email;
    private String hex;

    private Role role;

    public UserAccount() {

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

    public String getHex() {
        return hex;
    }

    public void setHex(final String hex) {
        this.hex = hex;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    public UserAccount(Long ID, String name, String email, String hex) {
        this.ID = ID;
        this.name = name;
        this.email = email;
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "ID=" + ID +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", hex='" + hex + '\'' +
                '}';
    }
}
