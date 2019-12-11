package com.revature.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.revature.handler.UserHandler;

public class UserDispatcher implements Dispatcher {

	// Constructor with the package-private access
	UserDispatcher() {
	}

	@Override
	public boolean supports(HttpServletRequest request) {
		return isCreateUser(request) || isAddFriend(request) || isFindUser(request) || isGetFriends(request) || isUpdateUser(request) || isUpdateCounter(request);
	}

	@Override
	public void execute(HttpServletRequest request, HttpServletResponse response) {
		if (isCreateUser(request)) {
			UserHandler.handleCreateUserRequest(request, response);
		} else if (isAddFriend(request)) {
			UserHandler.handleAddFriend(request, response);
		} else if (isGetFriends(request)) {
			UserHandler.handleGetFriends(request, response);
		} else if (isUpdateUser(request)) {
			UserHandler.handleUpdateUser(request, response);
		} else if (isUpdateCounter(request)) {
			UserHandler.handleUpdateCounter(request, response);
		} else if (isFindUser(request)) {
			UserHandler.handleFindFriend(request, response);
		}

	}

	public boolean isCreateUser(HttpServletRequest request) {
		return request.getMethod().equals("POST") && request.getRequestURI().equals("/PokeManiaAPI/api/createuser")
				&& request.getParameter("password") != null;
	}

	public boolean isAddFriend(HttpServletRequest request) {
		return request.getMethod().equals("POST") && request.getRequestURI().equals("/PokeManiaAPI/api/addfriend")
				&& request.getParameter("friendusername") != null;
	}

	public boolean isFindUser(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/PokeManiaAPI/api/finduser")
				&& request.getParameter("username") != null;
	}
	
	public boolean isGetFriends(HttpServletRequest request) {
		return request.getMethod().equals("GET") && request.getRequestURI().equals("/PokeManiaAPI/api/getfriends")
				&& request.getParameter("userid") != null;
	}
	
	public boolean isUpdateUser(HttpServletRequest request) {
		return request.getMethod().equals("PUT") && request.getRequestURI().equals("/PokeManiaAPI/api/updateuser");
	}
	
	public boolean isUpdateCounter(HttpServletRequest request) {
		return request.getMethod().equals("PUT") && request.getRequestURI().equals("/PokeManiaAPI/api/updatecounter");
	}

}
