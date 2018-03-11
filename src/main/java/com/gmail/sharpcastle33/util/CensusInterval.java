package com.gmail.sharpcastle33.managers;

import org.bukkit.scheduler.BukkitRunnable;

import com.gmail.sharpcastle33.Census;

public class CensusInterval extends BukkitRunnable {
	
	int time = 20 * 60 * 5;
			// ticks * seconds * minutes
			
	public CensusInterval() {
		
		
		
	}
			
	
	
	
	@Override
	public void run() {
		
		if(time <= 0) {
			
			Census.cManager.storeCensusInformation();
			
			time = 20 * 60 * 5; // reset clock
			
		}
		
		
	}

}
