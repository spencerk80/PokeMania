package com.revature.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.handler.PokemonHandler;

public class PokemonDispatcher implements Dispatcher {

	private final Logger logger = LogManager.getLogger(getClass());
	
	// Constructor with the package-private access
	PokemonDispatcher() {
	}

	@Override
	public boolean supports(HttpServletRequest request) {
		return isViewUsersPokemon(request) || isViewUsersTeam(request) || isUpdateUsersTeam(request) || isCatchPokemon(request) || isReleasePokemon(request);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (isViewUsersPokemon(request)) {
			PokemonHandler.handleViewAllUsersPokemon(request, response);
		} else if (isViewUsersTeam(request)) {
			PokemonHandler.handleViewAllUsersTeam(request, response);
		} else if (isUpdateUsersTeam(request)){
			PokemonHandler.handleUpdateUsersTeam(request, response);
		} else if (isCatchPokemon(request)) {
			PokemonHandler.handleCatchPokemon(request, response);
		} else if (isReleasePokemon(request)) {
			PokemonHandler.handleReleasePokemon(request, response);
		}
	}

	public boolean isViewUsersPokemon(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/PokeManiaAPI/api/pokemon")
				&& request.getParameter("userId") != null;
	}

	public boolean isViewUsersTeam(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/PokeManiaAPI/api/pokemonteam")
				&& request.getParameter("userId") != null;
	}
	
	public boolean isUpdateUsersTeam(HttpServletRequest request) {
		return request.getMethod().equals("POST") && request.getRequestURI().equals("/PokeManiaAPI/api/pokemonteam")
				&& request.getParameter("userId") != null;
	}
	
	public boolean isCatchPokemon(HttpServletRequest request) {
		return request.getMethod().contentEquals("POST") && request.getRequestURI().equals("/PokeManiaAPI/api/pokemon");
	}

	public boolean isReleasePokemon(HttpServletRequest request) {
		return request.getMethod().contentEquals("DELETE")
				&& request.getRequestURI().equals("/PokeManiaAPI/api/pokemon") && request.getParameter("pokemonId") != null;
	}

}
