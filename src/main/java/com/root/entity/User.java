package com.root.entity;

import javax.persistence.*;

@Entity /* This tells Hibernate to make a table out of this class */
@Table(name = "user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private Integer Id;

    @Column(name = "email")
    private String email;

    @Column(name = "name")
    private String name;

    @Column(name = "hex")
    private String hex;

    public User() {
    }

    public User(Integer id, String email, String name, String hex) {
        Id = id;
        this.email = email;
        this.name = name;
        this.hex = hex;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(String hex) {
        this.hex = hex;
    }

    @Override
    public String toString() {
        return "User{" +
                "Id=" + Id +
                ", email='" + email + '\'' +
                ", name='" + name + '\'' +
                ", hex='" + hex + '\'' +
                '}';
    }
}
