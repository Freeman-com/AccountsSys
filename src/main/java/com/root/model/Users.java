package com.root.model;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "user_accounts")
public class Users {



    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "email")
    private String email;

    private String name;

    @Column(name = "hex")
    private String hex;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
                name = "users_roles",
                joinColumns = @JoinColumn(name = "user_id",
                referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn(name = "role_id",
                referencedColumnName = "id")
                )

    private Collection<Roles> roles;

    public Users(Collection<Roles> roles) {
        this.roles = roles;
    }


    public Long getId() {
        return id;
    }

    public void setId(final Long id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(final String username) {
        this.email = username;
    }

    public String getName() {
        return name;
    }

    public void setName(final String name) {
        this.name = name;
    }

    public String getHex() {
        return hex;
    }

    public void setHex(final String hex) {
        this.hex = hex;
    }

    public Collection<Roles> getRoles() {
        return roles;
    }

    public void setRoles(final Collection<Roles> roles) {
        this.roles = roles;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = (prime * result) + ((getEmail() == null) ? 0 : getEmail().hashCode());
        return result;
    }

    @Override
    public boolean equals(final Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Users user = (Users) obj;
        if (!getEmail().equals(user.getEmail())) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        final StringBuilder builder = new StringBuilder();
        builder.append("User [id=")
                .append(id)
                .append(", name=").append(name)
                .append(", email=").append(email)
                .append(", roles=").append(roles)
                .append("]");
        return builder.toString();
    }

}
