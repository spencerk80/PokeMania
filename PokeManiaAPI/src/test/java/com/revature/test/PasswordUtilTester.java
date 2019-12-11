package com.revature.test;

import static org.junit.Assert.assertTrue;

import org.junit.Before;
import org.junit.Test;

import com.revature.util.Password;

public class PasswordUtilTester {
	
	private String 			hash		= null;
	private final String	USERNAME	= "Fakeuser",
							PASSWORD	= "Testing123";
	
	@Before
	public void setup() {
		
		hash = Password.transformPasswd(PASSWORD, USERNAME);
		
	}
	
	@Test
	public void testHash() {
		
		assertTrue(!PASSWORD.equals(Password.transformPasswd(PASSWORD, USERNAME)));
		
	}
	
	@Test
	public void unhash() {
		
		assertTrue(Password.transformPasswd(hash, USERNAME).equals(PASSWORD));
		
	}

}
