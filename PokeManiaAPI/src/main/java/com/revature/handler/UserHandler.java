package com.revature.handler;

import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.dao.UserDao;
import com.revature.dao.UserDaoSql;
import com.revature.model.User;
import com.revature.util.Json;

public class UserHandler {

	private static final UserDao dao = UserDaoSql.currentImplementation;
	private static final Logger logger = LogManager.getLogger(UserHandler.class);
	
	public static void handleCreateUserRequest(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Create User Request");
		try {
			User user = (User) Json.read(request.getInputStream(), User.class);
			String password = request.getParameter("password");
			int successCode = dao.addNewUser(user, password);
			if(successCode > 0) {
				response.getWriter().write(Integer.toString(successCode));
				response.setStatus(HttpServletResponse.SC_CREATED);
				logger.info("Successfully Added New User");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				logger.info("New User NOT Created. Bad Request");
				return;
			}		
		} catch (IOException | SQLException e) {
			logger.warn("Exception occured: {}", e);
			return;
		}
	}

	public static void handleAddFriend(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Add Friend Request Handler");
		logger.info("Friend username: {}", request.getParameter("friendusername"));
		try {
			User user = (User) Json.read(request.getInputStream(), User.class);
			String friendUsername = request.getParameter("friendusername");
			boolean wasSuccessful = dao.addFriend(user.getUsername(), friendUsername);
			if(wasSuccessful) {
				response.setStatus(HttpServletResponse.SC_CREATED);
				logger.info("Added new friend. Congratulations. You're not a loser.");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
				logger.info("Unable to add friend. Bad Request");
				return;
			}
			
		} catch (IOException | SQLException e) {
			logger.warn("Exception encountered in Add Friend Handler: {}", e);
			return;
		}
	}
	
	public static void handleGetFriends(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Get Friends UserHandler");
		int userId = Integer.parseInt(request.getParameter("userid"));
		logger.info("Your user ID is: {}", userId);
		try {
			User[] friends = dao.getFriends(userId);		
			if(friends == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.setContentType(Json.CONTENT_TYPE);
				response.getOutputStream().write(Json.write(friends));
				return;
			}
		} catch (SQLException | IOException e) {
			logger.warn("Exception was thrown: {}", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
	public static void handleUpdateUser(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside User Update Handler");
		try {
			User user = (User) Json.read(request.getInputStream(), User.class);
			boolean wasSuccessful = dao.updateStats(user);
			if(wasSuccessful) {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				logger.info("Updated User Stats");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				logger.info("Unable to Update Stats. Bad Request");
				return;
			}
		} catch (IOException | SQLException e) {
			logger.warn("Exception encounterd in Handle Update User: {}", e);
		}
	}
	
	
	public static void handleUpdateCounter(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside User Update Counter");
		try {
			User user = (User) Json.read(request.getInputStream(), User.class);
			boolean wasSuccessful = dao.updateCounter(user);
			if(wasSuccessful) {
				response.setStatus(HttpServletResponse.SC_ACCEPTED);
				logger.info("Updated User Counter");
				return;
			} else {
				response.setStatus(HttpServletResponse.SC_NOT_MODIFIED);
				logger.info("Unable to Update Counter.");
				return;
			}
		} catch (IOException | SQLException e) {
			logger.warn("Exception encounterd in Handle Update Counter: {}", e);
		}
	}
	
	public static void handleFindFriend(HttpServletRequest request, HttpServletResponse response) {
		logger.info("Inside Get Friend Finder UserHandler");
		String username = request.getParameter("username");
		logger.info("Your username is: {}", username);
		try {
			User friend = dao.fetchUser(username);		
			if(friend == null) {
				response.setStatus(HttpServletResponse.SC_NO_CONTENT);
			} else {
				response.setContentType(Json.CONTENT_TYPE);
				response.getOutputStream().write(Json.write(friend));
				return;
			}
		} catch (SQLException | IOException e) {
			logger.warn("Exception was thrown: {}", e);
			response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
			return;
		}
	}
	
}
