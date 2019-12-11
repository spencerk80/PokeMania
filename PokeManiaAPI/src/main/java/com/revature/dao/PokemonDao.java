package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.Pokemon;

/**
 * An interface that defines how the pokemon dao will work and what its jobs are
 * and how to use this dao
 * 
 * @author Kristoffer Spencer
 */
public interface PokemonDao {

	PokemonDao	currentImplimentation	= PokemonDaoSql.getInstance();
	
	Pokemon fetchPokemon(int id) throws SQLException;
	Pokemon[] fetchTeam(int userID) throws SQLException;
	Pokemon[] fetchBox(int userID) throws SQLException;
	int savePokemon(Pokemon pokemon) throws SQLException;
	boolean saveTeam(int userID, int[] pokeTeam) throws SQLException;
	boolean releasePoke(int pokemonID) throws SQLException;
	
}
