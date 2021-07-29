package com.aashishgiri5.ecommerce.service;

import com.aashishgiri5.ecommerce.dto.PlayerRequest;
import com.aashishgiri5.ecommerce.model.Player;
import com.aashishgiri5.ecommerce.repo.PlayerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PlayerService {
    @Autowired
    PlayerRepository playerRepository;
    public void createPlayer(PlayerRequest playerRequest) {
        Player player=new Player();
        player.setFirst_name(playerRequest.getFirstName());
        player.setLast_name(playerRequest.getLastName());
        player.setAge(player.getAge());
        player.setClub(player.getClub());
        playerRepository.save(player);
    }

    public void delete(int id) {
        playerRepository.deleteById(id);
    }

    public Player findById(int id) {
        return playerRepository.findById(id).get();
    }

    public void update(Player player, PlayerRequest playerRequest) {
        player.setFirst_name(playerRequest.getFirstName());
        player.setLast_name(playerRequest.getLastName());
        player.setAge(playerRequest.getAge());
        player.setClub(playerRequest.getClub());
        playerRepository.save(player);
    }



    public List<Player> findAllPlayers() {
        return playerRepository.findAll();
    }
}
