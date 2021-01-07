package com.root.exchange;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "bitmax")
public class BitMax {

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;

    @Column(name = "email")
    private String email;

    @Column(name = "balance")
    private BigDecimal balance;

    public BitMax() {}

    public BitMax(int id, String email, BigDecimal balance) {
        this.id = id;
        this.email = email;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    @Override
    public String toString() {
        return "BitMax{" +
                "id=" + id +
                ", email='" + email + '\'' +
                ", balance=" + balance +
                '}';
    }
}
