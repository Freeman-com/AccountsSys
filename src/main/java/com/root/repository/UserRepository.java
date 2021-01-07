package com.root.repository;

import com.root.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

// This will be AUTO IMPLEMENTED by Spring into a Bean called userRepository
// CRUD refers Create, Read, Update, Delete

public interface UserRepository extends CrudRepository<User, Long> {
    User findByEmail(String email);
    User findByHex (String hex);
    User findByName (String name);

    @Override
    void delete(User user);

}
