package com.gmail.sharpcastle33;

import org.bukkit.plugin.java.JavaPlugin;

import com.gmail.sharpcastle33.managers.CensusInterval;
import com.gmail.sharpcastle33.managers.CensusListener;
import com.gmail.sharpcastle33.managers.CensusManager;

public class Census extends JavaPlugin{
	
	public static Census plugin;
	public static CensusManager cManager;
	
	@Override
	public void onEnable() {
		
		plugin = this;
		cManager = new CensusManager();
		
		CensusInterval timer = new CensusInterval();
		
		timer.runTask(plugin);
		
		
		CensusListener cListener = new CensusListener();
		this.getServer().getPluginManager().registerEvents(cListener, plugin);
		
		
	}
	
	
	

}
