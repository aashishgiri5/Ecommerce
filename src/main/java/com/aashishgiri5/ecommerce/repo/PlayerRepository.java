package com.aashishgiri5.ecommerce.repo;

import com.aashishgiri5.ecommerce.model.Player;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlayerRepository extends JpaRepository<Player,Integer> {
}
