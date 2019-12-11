package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Trade;

/**
 * Interface for the tradeDao that repersents the tasks to be accomplished and establishes
 * the way to talk to this dao
 * 
 * @author Kristoffer Spencer
 */
public interface TradeDao {
	
	TradeDao currentImplementation 	= TradeDaoSql.getInstance();
	
	boolean openTradeOffer(int openerID, int partnerID, int offerPokemonID) throws SQLException;
	Trade[] fetchTrades(int trainerID) throws SQLException;
	boolean updateOffer(int tradeID, int offeredPokemonID) throws SQLException;
	boolean decline(int tradeID) throws SQLException;
	boolean accept(int tradeID) throws SQLException;

}
