package com.root.dao;

import com.root.model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends JpaRepository<Users, Long> {
    Users findByEmail(String email);

    @Override
    void delete(Users user);

}
