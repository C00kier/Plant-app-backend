package com.plantapp.plantapp.user.repository;

import com.plantapp.plantapp.user.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    Optional<User> findByEmail(String email);
<<<<<<< HEAD
    Optional<User> findByLogin(String login);

=======
    boolean existsByEmail(String email);
>>>>>>> 934dacd1572a60e7ddd848d0a5a61e1ca8377c8d
}
