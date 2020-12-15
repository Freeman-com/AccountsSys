package com.root.entity;

import javax.persistence.*;

@Entity /* This tells Hibernate to make a table out of this class */
@Table(name = "bitmax_api")
public class Bitmax {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)

    @Column(name = "id")
    private Integer Id;

    @Column(name = "public_key")
    private String public_key;

    @Column(name = "private_key")
    private String private_key;

    public Bitmax() {}

    public Bitmax(Integer id, String public_key, String private_key) {
        Id = id;
        this.public_key = public_key;
        this.private_key = private_key;
    }

    public Integer getId() {
        return Id;
    }

    public void setId(Integer id) {
        Id = id;
    }

    public String getPublic_key() {
        return public_key;
    }

    public void setPublic_key(String public_key) {
        this.public_key = public_key;
    }

    public String getPrivate_key() {
        return private_key;
    }

    public void setPrivate_key(String private_key) {
        this.private_key = private_key;
    }

    @Override
    public String toString() {
        return "Bitmax{" +
                "Id=" + Id +
                ", public_key='" + public_key + '\'' +
                ", private_key='" + private_key + '\'' +
                '}';
    }
}
