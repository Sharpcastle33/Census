package com.gmail.sharpcastle33.managers;

import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldType;
import org.bukkit.block.Biome;
import org.bukkit.entity.Player;

public class Region {

	
	/*
	 * Regions are defined as World and Biome, so a region could be (Normal, Desert) 
	 * and have census data for that region, currently we track; Stone block breaks, Mob Kills, and player count (at every interval)
	 */
	
	
	String worldType;
	Biome biomeType;
	
	int numStoneBreaks = 0;
	int numMobKills = 0;	
	
	public Region(String worldType, Biome biomeType) {
		
		this.worldType = worldType;
		this.biomeType = biomeType;
				
	}
	
	public String getRegion() {
		
		
		return worldType + biomeType;
		
	}
	
	@Override
	public String toString() {
		
		return worldType + biomeType;
		
	}
	
	public int getPlayerCount() {
		
		int playerCount = 0;
		
		for(World world : Bukkit.getWorlds()) { // Sift through worlds in server
			
			if(world.getName().equals(worldType)) { // if world matches this region's world
				
				for(Player p : world.getPlayers()) { // Determine what players are in this region's biome
					
					int x = (int)p.getLocation().getX();
					int y = (int)p.getLocation().getY();
					
					if(world.getBiome(x, y) == biomeType) { // If players are in this region (WORLD AND BIOME)
						
						playerCount++;
						
					}
					
				}
				
				
				
			}
			
		}
		
		
		return playerCount;
	}
	
	public int reportStoneBreaks() {
		
		int numBreaks = numStoneBreaks;
		numStoneBreaks = 0;
		
		return numBreaks;
		
	}
	
	public int reportMobKills() {
		
		int kills = numMobKills;
		numMobKills = 0;
		
		return kills;
	}
	
	public void countBreaks() {
		numStoneBreaks++;
	}
	public void countKills() {
		numMobKills++;
	}
}
