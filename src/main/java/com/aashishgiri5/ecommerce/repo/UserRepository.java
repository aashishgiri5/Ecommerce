package com.aashishgiri5.ecommerce.repo;

import com.aashishgiri5.ecommerce.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<User,Integer> {
    Optional<User> findByUserName(String user);

    User findByPassword(String password);
}
