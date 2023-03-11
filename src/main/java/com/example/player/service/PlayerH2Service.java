package com.example.player.service;

import com.example.player.model.PlayerRowMapper;
  import org.springframework.beans.factory.annotation.Autowired;
  import org.springframework.http.HttpStatus;
  import org.springframework.jdbc.core.JdbcTemplate;
  import org.springframework.stereotype.Service;
  import org.springframework.web.server.ResponseStatusException;
  import com.example.player.model.Player;
  import com.example.player.repository.PlayerRepository;  
  import java.util.*;


  @Service
  public class PlayerH2Service implements PlayerRepository{
    
    @Autowired
    private JdbcTemplate db;

    @Override
    public Player addPlayer(Player player){
      db.update("insert into team(playerName,jerseyNumber,role) values(?,?,?)",
                      player.getplayerName(), player.getjerseyNumber(), player.getrole());
      return db.queryForObject("select * from team where playerName = ? and jerseyNumber = ?", new PlayerRowMapper(),
                               player.getplayerName(), player.getjerseyNumber() );
    }
    

    @Override
    public ArrayList<Player> getPlayers(){
      return (ArrayList<Player>) db.query("select * from TEAM" , new PlayerRowMapper());
      
    }
    @Override
      
      public Player findPlayer(int playerId){
      try{
        Player player = db.queryForObject("select * from team where playerId = ?", new PlayerRowMapper(), playerId);
        return player;
      }
      catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
      
    } 
    @Override 
    public Player updatePlayer(int playerId, Player player){
      try{
        if(player.getplayerName() != null){
          db.update("update team set playerName = ? where playerId = ?", player.getplayerName(), playerId);    
        }
      
        if(player.getjerseyNumber() != 0){
          db.update("update team set jerseyNumber = ? where playerId = ?", player.getjerseyNumber(), playerId);
        }
        if(player.getrole() != null){
          db.update("update team set role=? where playerId=?", player.getrole(), playerId);
        }
        return findPlayer(playerId);
      }
      catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
  
    }
    
    @Override
    public void deletePlayer(int playerId){
      try{
        db.update("delete from team where playerId = ?", playerId);
      }
      catch(Exception e){
        throw new ResponseStatusException(HttpStatus.NOT_FOUND);
      }
    }


    




  }