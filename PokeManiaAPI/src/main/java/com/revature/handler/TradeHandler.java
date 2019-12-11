package com.revature.handler;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.TradeDao;
import com.revature.dao.TradeDaoSql;
import com.revature.model.Trade;
import com.revature.util.Json;

public class TradeHandler {

	private static final TradeDao dao = TradeDaoSql.currentImplementation;
	private static final Logger logger = LogManager.getLogger(TradeHandler.class);

	public static void handleCreateTradeRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Create Trade Request");
		try {
			Trade trade = (Trade) Json.read(request.getInputStream(), Trade.class);
			boolean wasSuccessful = dao.openTradeOffer(trade.getTrainer_id1(), trade.getTrainer_id2(),
					trade.getPokemon_id1());
			if (wasSuccessful) {
				response.setStatus(HttpServletResponse.SC_CREATED);
				logger.info("Successfully Added New Trade request");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				logger.info("New Trade request not created. Bad Request");
				return;
			}
		} catch (IOException | SQLException e) {
			logger.warn("Error inside Handler Trade Request {}", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}

	public static void handleUpdateTradeRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Patch Trade Request to Approve/Decline Offer");
		String status = request.getParameter("status").toLowerCase();
		try {
			Trade trade = (Trade) Json.read(request.getInputStream(), Trade.class);
			switch (status) {
			case "approved":
				boolean wasSuccessfulAccept = dao.accept(trade.getId());
				if (wasSuccessfulAccept) {
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
					logger.info("Successfully Approved Trade Request");
					return;
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					logger.info("Trade Request Approval was not done. Bad Request");
					return;
				}
			case "declined":
				boolean wasSuccessfulDeny = dao.decline(trade.getId());
				if (wasSuccessfulDeny) {
					response.setStatus(HttpServletResponse.SC_ACCEPTED);
					logger.info("Successfully Declined Trade Request");
					return;
				} else {
					response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
					logger.info("Trade Request Decline was not done. Bad Request");
					return;
				}
			default:
				logger.warn("The status code of {} was not read and could not be completed", status);
				break;
			}
		} catch (IOException | SQLException e) {
			logger.warn("Exception {} encountered in handleUpdateTradeRequest", e );
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	public static void handleUpdateTradeOffer(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Patch Trade Offer");
		try {
			Trade trade = (Trade) Json.read(request.getInputStream(), Trade.class);
			boolean wasSuccessful = dao.updateOffer(trade.getId(), trade.getPokemon_id2());
			if(wasSuccessful) {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				logger.info("Successfully Updated Offer");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				logger.info("Trade Offer did not go through. Bad Request");
				return;
			}
		} catch (IOException | SQLException e) {
			logger.warn("Exception {} encountered in handleUpdateTradeOffer", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
}
