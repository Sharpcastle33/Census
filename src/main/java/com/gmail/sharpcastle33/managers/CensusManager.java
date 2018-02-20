package com.gmail.sharpcastle33.managers;

import java.util.Map;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import com.gmail.sharpcastle33.util.CensusBlock;

public class CensusManager {
  
  Map<CensusBlock, String> activeCensusBlocks;
  int playerCount;
  String time;
  
  public void collectPopData(){
    for(Player p : Bukkit.getServer().getOnlinePlayers()){
      
    }
    
    playerCount = Bukkit.getServer().getOnlinePlayers().size();
    
  }
  
  public void saveCensusBlocks(){
    
  }
  
  public void resetCensusBlocks(){
    
  }
  
  

}
