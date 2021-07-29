package com.aashishgiri5.ecommerce.controller;


import com.aashishgiri5.ecommerce.dto.PlayerRequest;
import com.aashishgiri5.ecommerce.model.Player;
import com.aashishgiri5.ecommerce.service.PlayerService;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("api/player")
public class PlayerController {
    @Autowired
    private PlayerService playerService;
    @PostMapping(value="/createPlayer",produces = "application/json")
    public ResponseEntity<?> createPlayer(@RequestBody PlayerRequest playerRequest)
    {
      playerService.createPlayer(playerRequest);
      return ResponseEntity.ok("Player Created Successfully");
    }

    @DeleteMapping(value = "/deletePlayer/{id}",produces="application/json")
    public ResponseEntity<?> deletePlayer(@PathVariable int id)
    {
        playerService.delete(id);
        return ResponseEntity.ok("Player Deleted Successfully");
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<?> updatePlayer(@PathVariable int id,@RequestBody PlayerRequest playerRequest)
    {
        Player player=playerService.findById(id);
        playerService.update(player,playerRequest);
        return ResponseEntity.ok("Player Updated Successfully");
    }

    @GetMapping("/get/{id}")
    public ResponseEntity<?> getPlayer(@PathVariable int id)
    {
        Player player=playerService.findById(id);
        return ResponseEntity.ok("Player Found Successfully");
    }

    @GetMapping("/getAll")
    public List<Player> getPlayer()
    {
//        JSONObject jsonObject=new JSONObject();
//        jsonObject.put("status",200);
//        jsonObject
        return  playerService.findAllPlayers();
    }
}
