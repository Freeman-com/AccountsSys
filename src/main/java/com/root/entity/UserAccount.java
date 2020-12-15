package com.root.entity;

import javax.management.relation.Role;
import javax.persistence.*;

@Entity /* This tells Hibernate to make a table out of this class */
@Table(name = "user_account")
public class UserAccount
{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "userIdaccount")
    private Integer userIdaccount;

    @Column(name = "hexaccount")
    private String hexaccount;

    @Column(name = "emailaccount")
    private String emailaccount;

    @Column(name = "nameaccount")
    private String nameaccount;

    @Column(name = "role")
    private String role;

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public String getNameaccount() {
        return nameaccount;
    }

    public void setNameaccount(String nameaccount) {
        this.nameaccount = nameaccount;
    }

    public Integer getUserIdaccount() {
        return userIdaccount;
    }

    public void setUserIdaccount(Integer userIdaccount) {
        this.userIdaccount = userIdaccount;
    }


    public String getHexaccount() {
        return hexaccount;
    }

    public void setHexaccount(String hexaccount) {
        this.hexaccount = hexaccount;
    }

    public String getEmailaccount() {
        return emailaccount;
    }

    public void setEmailaccount(String emailaccount) {
        this.emailaccount = emailaccount;
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "userIdaccount=" + userIdaccount +
                ", hexaccount='" + hexaccount + '\'' +
                ", emailaccount='" + emailaccount + '\'' +
                ", nameaccount='" + nameaccount + '\'' +
                ", role='" + role + '\'' +
                '}';
    }
}
