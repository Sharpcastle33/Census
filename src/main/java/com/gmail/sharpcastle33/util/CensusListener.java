package com.gmail.sharpcastle33.managers;

import java.util.ArrayList;

import org.bukkit.Material;
import org.bukkit.block.Block;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.BlockBreakEvent;
import org.bukkit.event.entity.EntityDeathEvent;

import com.gmail.sharpcastle33.Census;

import org.bukkit.block.Biome;
import org.bukkit.World;
import org.bukkit.entity.Animals;
import org.bukkit.entity.Creature;

public class CensusListener implements  Listener {

	@EventHandler
	public void onBlockBreak(BlockBreakEvent event) {
		
		Block broke = event.getBlock();
		
		if(broke.getType().equals(Material.STONE)) {
			
			if(Census.cManager.containsRegion(getRegion(broke))) { // If something has been recorded for this region already...
				
				ArrayList<Region> regions = Census.cManager.activeRegions;
				
				int index = regions.indexOf(getRegion(broke));
				
				regions.get(index).countBreaks();
				
			} else {
				
				// Add new region (worldname, biome)
				Census.cManager.activeRegions.add(new Region(broke.getWorld().getName(),
						broke.getWorld().getBiome((int)broke.getLocation().getX(), (int)broke.getLocation().getY())));
				
				ArrayList<Region> regions = Census.cManager.activeRegions;
				
				int index = regions.indexOf(getRegion(broke));
				
				regions.get(index).countBreaks();
				
			}
		}	
	}
	
	@EventHandler
	public void onLivingEntityDeath(EntityDeathEvent event){
		
		
		// record deaths of hostile mobs (avoids counting deaths of cows, sheep, & other farm critters)
		if(event.getEntity() instanceof Creature && !(event.getEntity() instanceof Animals)) {
			
			
			if(Census.cManager.containsRegion(getRegion((Creature)event.getEntity()))) {
				
				ArrayList<Region> regions = Census.cManager.activeRegions;
				
				int index = regions.indexOf(getRegion((Creature)event.getEntity()));
				
				regions.get(index).countKills();
				
				
			} else {
				
				// Add new region (worldname, biome)
				Census.cManager.activeRegions.add(new Region(event.getEntity().getWorld().getName(),
						event.getEntity().getWorld().getBiome((int)event.getEntity().getLocation().getX(), (int)event.getEntity().getLocation().getY())));
				
				ArrayList<Region> regions = Census.cManager.activeRegions;
				
				int index = regions.indexOf(getRegion((Creature)event.getEntity()));
				
				regions.get(index).countKills();
				
				
			}
		} 	
	}

	
	
	
	
	
	public String getRegion(Block block) {
		
		World blockWorld = block.getWorld();
		
		String worldName = block.getWorld().getName();
		
		Biome blockBiome = blockWorld.getBiome((int)block.getLocation().getX(), (int)block.getLocation().getY());
		
		return worldName + blockBiome;
		
	}
	
	public String getRegion(Creature creature) {
		
		World world = creature.getWorld();
		
		String worldName = world.getName();
		
		Biome biome = world.getBiome((int)creature.getLocation().getX(), (int)creature.getLocation().getX());
		
		return worldName + biome;
		
	}
	
	
	
}
