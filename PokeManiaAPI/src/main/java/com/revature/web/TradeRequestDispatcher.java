package com.revature.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.handler.TradeHandler;

public class TradeRequestDispatcher implements Dispatcher {

	private final Logger logger = LogManager.getLogger(getClass());
	
	// Constructor with package-private access
	TradeRequestDispatcher() {
	}

	@Override
	public boolean supports(HttpServletRequest request) {
		return isCreateTradeRequest(request) || isPatchTradeRequest(request) || isPatchTradeOffer(request);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (isCreateTradeRequest(request)) {
			TradeHandler.handleCreateTradeRequest(request, response);
		} else if (isPatchTradeRequest(request)) {
			TradeHandler.handleUpdateTradeRequest(request, response);
		} else if (isPatchTradeOffer(request)) {
			TradeHandler.handleUpdateTradeOffer(request, response);
		}

	}

	public boolean isCreateTradeRequest(HttpServletRequest request) {
		return request.getMethod().equals("POST") && request.getRequestURI().equals("/PokeManiaAPI/api/traderequest");
	}

	public boolean isPatchTradeRequest(HttpServletRequest request) {
		return request.getMethod().equals("PATCH") && request.getRequestURI().equals("/PokeManiaAPI/api/traderequest")
				&& request.getParameter("status") != null;
	}

	public boolean isPatchTradeOffer(HttpServletRequest request) {
		return request.getMethod().equals("PATCH") && request.getRequestURI().equals("/PokeManiaAPI/api/tradeoffer");
	}
}
