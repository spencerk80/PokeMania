package com.revature.dao;

import java.sql.SQLException;

import com.revature.model.User;

/**
 * Interface to use the underlining SQL dao to manage user data
 * 
 * @author Kristoffer Spencer
 */
public interface UserDao {
	
	//Please access dao from here with UserDao.currentImplementation
	UserDao	currentImplementation = UserDaoSql.getInstance();
	
	User fetchUser(String username) throws SQLException;
	int	addNewUser(User user, String password) throws SQLException;
	boolean	addFriend(String username, String friendName) throws SQLException;
	User login(String username, String password) throws SQLException;
	User[] getFriends(int userID) throws SQLException;
	boolean	updateStats(User user) throws SQLException;
	boolean updateCounter(User user) throws SQLException;
	
}
