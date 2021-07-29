package com.aashishgiri5.ecommerce.repo;

import com.aashishgiri5.ecommerce.model.Token;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface TokenRepository extends JpaRepository<Token,Integer> {
    Optional<Token> findByToken(String id);
}
