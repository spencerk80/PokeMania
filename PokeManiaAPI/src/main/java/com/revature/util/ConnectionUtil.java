package com.revature.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * A utility class used to get a connection to the db for PokeMania
 * 
 * @author Kristoffer Spencer
 */
public class ConnectionUtil {
	
	//Make this work for servlets
	static {
		
		try {
			
			Class.forName("oracle.jdbc.OracleDriver");
			
		} catch (ClassNotFoundException e) {
			
			System.err.println(e.getMessage());
			
		}
		
	}
	
	/**
	 * Grab a connection to the db for PokeMania
	 * 
	 * @return The connection established
	 * @throws SQLException Thrown when a connection cannot be established
	 */
	public static Connection getConnection() throws SQLException {
		
		String		url			= System.getenv("POKEMANIA_DB_URL"),
					username	= System.getenv("POKEMANIA_DB_USERNAME"),
					password	= System.getenv("POKEMANIA_DB_PASSWD");
		
		return DriverManager.getConnection(url, username, password);
		
	}

}
