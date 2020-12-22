package com.root.model;

import com.root.role.Role;
import com.root.service.OwnerService;

public class Owner implements OwnerService {

    private User user;
    private Role role;

    private Long ID;
    private String name;
    private String email;

    public Owner(Role role, Long ID, String name, String email) {
        this.role = role;
        this.ID = ID;
        this.name = name;
        this.email = email;
    }

    @Override
    public void createSuperAdmin() {
        SuperAdmin superAdmin = new SuperAdmin();
        superAdmin.setUserRole(Role.SUPER_ADMIN);

    }

    @Override
    public void deleteSuperAdmin() {

    }

    @Override
    public void createAdmin() {

    }

    @Override
    public void deleteAdmin() {

    }
}
