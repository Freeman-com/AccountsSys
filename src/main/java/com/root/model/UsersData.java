package com.root.model;

import javax.persistence.*;

@Entity
@Table(name = "users_data")
public class UsersData {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "total_balance")
    private Long total_balance;

    @OneToOne(mappedBy = "usersData")
    private User user;

    public UsersData() {}

    public UsersData(int id, Long total_balance) {
        this.id = id;
        this.total_balance = total_balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Long getTotal_balance() {
        return total_balance;
    }

    public void setTotal_balance(Long total_balance) {
        this.total_balance = total_balance;
    }

    @Override
    public String toString() {
        return "UsersData{" +
                "id=" + id +
                ", total_balance=" + total_balance +
                '}';
    }
}
