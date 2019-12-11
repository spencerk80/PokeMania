package com.revature.util;

/**
 * Utility class for interacting with passwords
 * 
 * @author Kristoffer Spencer
 */
public final class Password {

	/**
	 * Used to transform the human readable password into unreadable. The unreadable password
	 * can be put back through this method to return the original password.
	 * 
	 * @param pswd The password to be transformed
	 * @param key Usually the username. Some unique string each user has
	 * @return Transformed password
	 */
	public static String transformPasswd(String pswd, String key) {
		
		StringBuilder	sb		= new StringBuilder("");
		char[]			letters	= pswd.toCharArray(),
						keyVals	= key.toCharArray();
		char			mask	= 13;
		
		//Make mask
		for(char keyVal : keyVals)
			
			mask += keyVal;
		
		//For each letter
		for(char c : letters) {
			
			//Mask and append the character to rebuild the string later
			c ^= mask;
			sb.append(c);
			
			//Update the mask because more secure to use dynamically 
			//changing masks than just the same one.
			mask += 3;
			mask %= 16;
			
		}
		
		//Return the transformed string
		return sb.toString();
		
	}
	
	/**
	 * Test to see if a provided password is any good. Must not be an empty
	 * string. Must start with a letter. Must contain letters and numbers.
	 * Must be at least 8 chars long
	 * 
	 * @param pswd The password to be tested
	 * @return Whether the password is good or not
	 */
	public static boolean isSecure(String pswd) {
		
		String	letter				= "[A-Z]",
				number				= "[0-9]";
		char[]	letters				= pswd.toCharArray();
		
		//If the first char isn't a letter, it's a bad password
		if(!(letters[0] + "").matches(letter))
			
			return false;
		
		//If the password isn't at least 8 char long. It's a bad password
		if(pswd.length() < 8)
			
			return false;
		
		//If there's a number in there, password is good
		for(char c : letters) 
			
			if((c + "").matches(number))
				
				return true;
		
		//No letter was found
		return false;
		
	}
	
}
