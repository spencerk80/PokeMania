package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.TradeStatus;
import com.revature.model.Trade;
import com.revature.util.ConnectionUtil;
import com.revature.util.Parser;

/**
 * This dao handles all the transactions of trade requests for PokemonMania
 * 
 * @author Kristoffer Spencer
 */
public class TradeDaoSql implements TradeDao {
	
	private static final TradeDaoSql	instance		= new TradeDaoSql();
	private static final Logger			log				= LogManager.getLogger(TradeDaoSql.class);
	private static final String			OPEN_OFFER		= "INSERT INTO trade_requests (trainer_id1, pokemon_id1, trainer_id2, status) VALUES "
														+ "(?, ?, ?, ?)",
										FETCH_TRADES	= "SELECT * FROM trade_requests WHERE (trainer_id1 = ? OR trainer_id2 = ?) AND "
														+ "(status = ? OR status = ?)",
										UPDATE_OFFER	= "UPDATE trade_requests SET pokemon_id2 = ?, status = ? WHERE trade_id = ?",
										DECLINE_TRADE	= "UPDATE trade_requests SET status = ? WHERE trade_id = ?",
										ACCEPT_TRADE	= "UPDATE trade_requests SET status = ? WHERE trade_id = ?";
	
	private TradeDaoSql() {}
	
	/**
	 * Return the singleton instance of this dao.
	 * 
	 * @return Singleton instance of this dao
	 */
	public static TradeDaoSql getInstance() {
		
		return instance;
		
	}
	
	/**
	 * Opens a trade offer by writing that info down to the db
	 * 
	 * @param openerID The id of the initiating trainer
	 * @param partnerID The id of the trainer with whom to trade
	 * @param offerPokemonID The offered up pokemonID
	 * @return Whether creation of trade was successful
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public boolean openTradeOffer(int openerID, int partnerID, int offerPokemonID) throws SQLException {
		
		PreparedStatement	ps;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement(OPEN_OFFER);
			ps.setInt(1, openerID);
			ps.setInt(2, offerPokemonID);
			ps.setInt(3, partnerID);
			ps.setString(4, TradeStatus.SUBMITTED.toString());
			
			return ps.executeUpdate() == 1;
			
		} catch(SQLException e) {
			
			log.warn("Error: Could not open the trade offer\n" + e.getMessage());
			throw e;
			
		}
				
	}

	/**
	 * Get all open trades involving this trainer
	 * 
	 * @param trainerID The id of the initiating trainer
	 * @return All open trades which include this trainer
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	@Override
	public Trade[] fetchTrades(int trainerID) throws SQLException {
	
		PreparedStatement	ps;
		ResultSet			rs;
		List<Trade>			trades	= new ArrayList<>();
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement(FETCH_TRADES);
			ps.setInt(1, trainerID);
			ps.setInt(2, trainerID);
			ps.setString(3, TradeStatus.SUBMITTED.toString());
			ps.setString(4, TradeStatus.PENDING.toString());
			rs = ps.executeQuery();
			
			while(rs.next())
				
				//Yes, 1, 2, 4, 3. Why? Because they're in that order in the model vs the db table
				trades.add(new Trade(rs.getInt(1), rs.getInt(2), rs.getInt(4), rs.getInt(3), rs.getInt(5), Parser.parseTradeStatus(rs.getString(6))));
			
			return trades.toArray(new Trade[0]);
			
		} catch(SQLException e) {
			
			log.warn("Error: Failed to fetch trades\n" + e.getMessage());
			throw e;
			
		}
		
	}
	
	/**
	 * Update an opening offer with the proposed offer and set the status accordingly
	 * 
	 *@param tradeID The id of the trade to be updated
	 *@param offeredPokemonID The offered pokemonID
	 *@return Whether the update was successful
	 *@exception SQLException Thrown when there's an issue talking to the db
	 */
	@Override
	public boolean updateOffer(int tradeID, int offeredPokemonID) throws SQLException {
		
		PreparedStatement	ps;
		int					result;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement(UPDATE_OFFER);
			ps.setInt(1, offeredPokemonID);
			ps.setString(2, TradeStatus.PENDING.toString());
			ps.setInt(3, tradeID);
			
			result = ps.executeUpdate();
			
			if(result == 0)
				
				return false;
			
			else if(result == 1)
				
				return true;
			
			else
				
				throw new SQLException("Update affected more than one row");
			
		} catch(SQLException e) {
			
			log.warn("Error: Failed to update offer\n" + e.getMessage());
			throw e;
			
		}
		
	}

	/**
	 * Updates the offer to declined
	 * 
	 * @param tradeID The id of the trade to close
	 * @return Whether the update was successful
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	@Override
	public boolean decline(int tradeID) throws SQLException {
		
		PreparedStatement	ps;
		int					result;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement(DECLINE_TRADE);
			ps.setString(1, TradeStatus.DECLINED.toString());
			ps.setInt(2, tradeID);
			result = ps.executeUpdate();
			
			if(result == 0)
				
				return false;
			
			else if(result == 1)
				
				return true;
			
			else
				
				throw new SQLException("Update affected more than one row");
			
		} catch(SQLException e) {
			
			log.warn("Failed to update status to declined\n" + e.getMessage());
			throw e;
			
		}
		
	}

	/**
	 * Updates the offer to accepted
	 * 
	 * @param tradeID The id of the trade to close
	 * @return Whether the update was successful
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	@Override
	public boolean accept(int tradeID) throws SQLException {

		PreparedStatement	ps;
		int					result;
		
		try(Connection c = ConnectionUtil.getConnection()) {
			
			ps = c.prepareStatement(ACCEPT_TRADE);
			ps.setString(1, TradeStatus.ACCEPTED.toString());
			ps.setInt(2, tradeID);
			result = ps.executeUpdate();
			
			if(result == 0)
				
				return false;
			
			else if(result == 1)
				
				return true;
			
			else
				
				throw new SQLException("Update affected more than one row");
			
		} catch(SQLException e) {
			
			log.warn("Failed to update status to accepted\n" + e.getMessage());
			throw e;
			
		}

	}

}
