package com.revature.test;

import static org.junit.Assert.assertTrue;

import java.sql.SQLException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.junit.Test;

import com.revature.util.ConnectionUtil;

public class ConnectionUtilTester {
	
	Logger log = LogManager.getLogger(ConnectionUtilTester.class);
	
	@Test
	public void testGetConnection() {
		
		try {
			
			ConnectionUtil.getConnection();
			
		} catch(SQLException e) {
			
			log.error("Error: Failed to obtain a connection to the DB\n" + e.getMessage());
			//Fail the test
			assertTrue(false);
			
		}
		
	}

}
