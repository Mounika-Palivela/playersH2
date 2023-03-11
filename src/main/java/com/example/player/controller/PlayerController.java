package com.example.player.controller;
import com.example.player.model.Player;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.*;

import com.example.player.service.PlayerH2Service;
@RestController
public class PlayerController{
    @Autowired
    public PlayerH2Service playerh2Service;

    @GetMapping("/players")
    public ArrayList<Player> getPlayers(){
        return playerh2Service.getPlayers();
    }
    @GetMapping("/players/{playerId}")
    public Player findPlayer(@PathVariable("playerId") int playerId){
        return playerh2Service.findPlayer(playerId);
    }
    @PostMapping("/players")
    public Player addPlayer(@RequestBody Player player){
        return playerh2Service.addPlayer(player);
    }

    @PutMapping("/players/{playerId}")
    public Player updatePlayer(@PathVariable("playerId") int playerId, @RequestBody Player player){
        return playerh2Service.updatePlayer(playerId, player);
    }
    @DeleteMapping("/players/{playerId}")
    public void deletePlayer(@PathVariable("playerId") int playerId){
        playerh2Service.deletePlayer(playerId);
    }

    






}