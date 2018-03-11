package com.gmail.sharpcastle33.managers;

import java.util.ArrayList;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

public class CensusManager {
  
	ArrayList<Region> activeRegions;
	private Connection connection = null;
	private PreparedStatement statement;
	
	public CensusManager() {
		
		activeRegions = new ArrayList<Region>();
		
		try {
			
			// Make DB file on server system
			Class.forName("org.sqlite.JDBC");
			connection = DriverManager.getConnection("jdbc:sqlite:CensusDatabase.db");
			
			// Set up tables
			
			DatabaseMetaData dbm = connection.getMetaData();
			ResultSet tables = dbm.getTables(null, null, "region", null);
					
					
					
			// if no tables have been built, build them
			// So to avoid wiping database upon server restart
			// So this should only run when Census first starts, ever
					
			if(!tables.next()) { 
				
				String qString = "";
				
				
				
				qString = "CREATE TABLE region(regionName VARCHAR(50), blockBreaks int(20), mobKills int(20), players int(3), dateOfReport VARCHAR(12);";
				statement = connection.prepareStatement(qString);
				statement.executeUpdate();
				
				
				
			}
			
			
			
			
			
		} catch (Exception e) {
			// Error shouldn't happen
			// We're only making a file on the server here
			System.out.println("CENSUS FAILED TO START DATABASE");
			
		}
		
	}
	
	
	
	public void storeCensusInformation() {
		
		
		DateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy HH:mm");
		
		String dateOfReport = dateFormat.format(new Date());
		
		
		String qString = null;
		ResultSet result = null;
		
		// FOR EACH REGION IN LIST
		// CALL REPORT METHODS
		
		for(Region region : activeRegions) {
			try {
			
				
					qString = "INSERT INTO region"
							+ "VALUES(" + region.getRegion() + ","
							+ region.reportStoneBreaks() + ","
							+ region.reportMobKills() + ","
							+ region.getPlayerCount() + ","
							+ dateOfReport + ");";
				
					statement = connection.prepareStatement(qString);
					statement.executeUpdate();
				
				
			
			} catch(Exception e) {
				
				System.out.println("DATABASE GOOFED");
				
			}
			
			
			activeRegions.remove(region);
			
			
			
			
		}
		
		
		
		
		
		
		
	}
	
	public boolean containsRegion(String regionCode) {
		
		for(Region r :activeRegions)
		{
			
			if(r.getRegion().equals(regionCode)) {
				
				return true;
				
			}
			
		}
		
		return false;
		
	}
	
  

}
