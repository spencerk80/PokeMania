package com.revature.util;

import com.revature.TradeStatus;

/**
 * A parser class used to simplify parsing of various values. Returns a default on failure to parse
 * 
 * @author Kristoffer Spencer
 */
public class Parser {

	/**
	 * Tries to parse from a string a TradeStatus. Upon an invalid value passed in,
	 * returns a TradeStatus.INVALID value
	 * 
	 * @param status the string form that matches the value of a TradeStatus enum
	 * @return The appropriate TradeStatus enum type mathcing the string
	 */
	public static TradeStatus parseTradeStatus(String status) {
		
		try {
			
			return TradeStatus.valueOf(status.toUpperCase());
			
		} catch(Exception e) {
			
			return TradeStatus.INVALID;
			
		}
		
	}
	
}
