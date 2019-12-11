package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.revature.model.User;
import com.revature.util.ConnectionUtil;
import com.revature.util.Password;

/**
 * Handles the saving and fetching of user data with the remote SQL database on
 * AWS. Fetch a single user or save a new one. Set a friend or fetch all
 * friends. Also fetch the password of a user for comparison for login.
 * 
 * @author Kristoffer Spencer
 */
public class UserDaoSql implements UserDao {

	private static final UserDaoSql instance = new UserDaoSql();
	private static final Logger logger = LogManager.getLogger(UserDaoSql.class);
	private static final String GET_USER_SQL = "SELECT * FROM trainers WHERE trainer_name = ?",
			INSERT_USER_SQL = "INSERT INTO trainers (trainer_name, trainer_password, first_name, last_name, badges, wins, losses, counter, ctime)"
					+ " VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)",
			INSERT_TEST_SQL = "INSERT INTO trainers VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)",
			LOGIN_SQL = "SELECT trainer_password FROM trainers WHERE trainer_name = ?",
			FETCH_FRND_IDS = "SELECT * FROM friends WHERE trainer_id1 = ? OR trainer_id2 = ?",
			FETCH_FRND 	= "SELECT * FROM trainers WHERE trainer_id = ",
			ADD_FRND = "INSERT INTO friends VALUES ((SELECT trainer_id FROM trainers WHERE trainer_name = ?), "
					+ "(SELECT trainer_id FROM trainers WHERE trainer_name = ?))",
			UPDATE_STATS = "UPDATE trainers SET badges = ?, wins = ?, losses = ? WHERE trainer_id = ?",
			UPDATE_COUNTER = "UPDATE trainers SET counter = ?, cTime = ? WHERE trainer_id = ?";

	private UserDaoSql() {
	};

	/**
	 * Package private getter for the instance. The instance is meant to be
	 * retrieved from the interface so that if a different implementation is used to
	 * manage data, no code beyond the interface is needed to be modified.
	 * 
	 * @return The singleton instance of this dao
	 */
	static public UserDaoSql getInstance() {
		return instance;
	}

	/**
	 * Fetch a user from the database by the username.
	 * 
	 * @exception SQLException Thrown when there's an issue talking to the db
	 * @param username The user name of the user whose info is needed
	 * @return The user obj of all the user's data based on username
	 */
	public User fetchUser(String username) throws SQLException {

		User user = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(GET_USER_SQL);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next())

				user = new User(rs.getString(2), rs.getString(4), rs.getString(5), rs.getInt(1), rs.getInt(6),
						rs.getInt(7), rs.getInt(8), rs.getInt(9), rs.getLong(10));

		} catch (SQLException e) {

			logger.warn("Error: Could not fetch user\n" + e.getMessage());

			throw e; // Exception is propagated for higher level exception handling

		}

		return user;

	}

	/**
	 * Register a new user to the system by passing in all the user info needed.
	 * Writes to the db, then return the user id
	 * 
	 * @param user     User object with the user's data. Password for the user for
	 *                 loggin in
	 * @param password The password they user wants to set
	 * @return Newly generate user id
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public int addNewUser(User user, String password) throws SQLException {

		PreparedStatement ps 	= null;
		ResultSet		  rs;
		int				  id 	= 0;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(INSERT_USER_SQL);
			ps.setString(1, user.getUsername());

			// My own custom password hashing thingie
			ps.setString(2, Password.transformPasswd(password, user.getUsername()));

			ps.setString(3, user.getFirstname());
			ps.setString(4, user.getLastname());
			ps.setInt(5, 0);
			ps.setInt(6, 0);
			ps.setInt(7, 0);
			ps.setInt(8, 0);
			ps.setInt(9, 0);
			
			ps.executeUpdate();
			
			ps = c.prepareStatement(GET_USER_SQL);
			ps.setString(1, user.getUsername());
			rs = ps.executeQuery();
			
			if(rs.next()) id = rs.getInt(1);
			
			return id;

		} catch (SQLException e) {

			logger.warn("Error: Failed to add new user to DB\n" + e.getMessage());
			throw e; // Exception is propagated for higher level exception handling

		}

	}

	/**
	 * TEST VERSION allows to set the id in db Register a new user to the system by
	 * passing in all the user info needed. Writes to the db
	 * 
	 * @param user     User object with the user's data. Password for the user for
	 *                 loggin in
	 * @param password The password the user wishes to use for login
	 * @return Whether the user was saved successfully
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public boolean add_TEST_newUser(User user, String password) throws SQLException {
		// (trainer_name, trainer_password, first_name, last_name, badges, wins, losses)

		PreparedStatement ps = null;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(INSERT_TEST_SQL);
			ps.setInt(1, user.getId());
			ps.setString(2, user.getUsername());
			ps.setString(3, Password.transformPasswd(password, user.getUsername()));
			ps.setString(4, user.getFirstname());
			ps.setString(5, user.getLastname());
			ps.setInt(6, user.getBadges());
			ps.setInt(7, user.getWins());
			ps.setInt(8, user.getLosses());
			ps.setInt(9, user.getCounter());
			ps.setLong(10, user.getcTime());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {

			logger.warn("Error: Failed to add new user to DB\n" + e.getMessage());
			throw e; // Exception is propagated for higher level exception handling

		}

	}

	/**
	 * Method to add an entry into the friends table to forever mark a trainer and
	 * friend as friends
	 * 
	 * @param username   The name of the logged in user
	 * @param friendName The name of the friend to add
	 * @return Whether it was successful
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public boolean addFriend(String username, String friendName) throws SQLException {

		PreparedStatement ps = null;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(ADD_FRND);
			ps.setString(1, username);
			ps.setString(2, friendName);

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {

			logger.warn("Error: Could not add friend!\n" + e.getMessage());
			throw e;

		}

	}

	/**
	 * Authenticates that the user provided creds match that which are written in
	 * the database. I.E this checks whether a login is successful
	 * 
	 * @param username username of the user logging in
	 * @param password password of the user logging in
	 * @return user job of the logged in user or null on auth failure
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public User login(String username, String password) throws SQLException {

		User user = null;
		PreparedStatement ps;
		ResultSet rs;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(LOGIN_SQL);
			ps.setString(1, username);
			rs = ps.executeQuery();

			if (rs.next())

				if (password.equals(Password.transformPasswd(rs.getString(1), username)))

					user = this.fetchUser(username);

		} catch (SQLException e) {

			logger.warn("Error fetching user creds from db\n" + e.getMessage());

		}

		return user;

	}

	/**
	 * Get a list of the logged in user's friends' usernames
	 * 
	 * @param userID Takes the ID of the logged in user to find the friends of said
	 *               user
	 * @return Returns a list of the friend objs
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	@Override
	public User[] getFriends(int userID) throws SQLException {

		LinkedList<Integer> friendIDs = getFriendIDs(userID);
		List<User> friends = null;

		if (friendIDs.size() == 0) // No friends

			return null;

		friends = getFriends(createIDsString(friendIDs));

		return friends.toArray(new User[0]);

	}

	/**
	 * Helper method to break up code. Takes the user's ID and gets the list of
	 * their friends' ids
	 * 
	 * @param userID The id of the logged in user
	 * @return A LinkedList of the ids of the friends. Works as a queue
	 * @throws SQLException Thrown when there's an issue talking to the db
	 */
	private LinkedList<Integer> getFriendIDs(int userID) throws SQLException {

		PreparedStatement ps = null;
		ResultSet rs = null;
		LinkedList<Integer> friendIDs = new LinkedList<>();

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(FETCH_FRND_IDS);
			ps.setInt(1, userID);
			ps.setInt(2, userID);
			rs = ps.executeQuery();

			// Gather up the ids of the friends. Value pairs can have the friend
			// in either column
			while (rs.next()) {

				int id1 = rs.getInt(1), id2 = rs.getInt(2);

				if (id1 == userID)

					friendIDs.add(id2);

				else

					friendIDs.add(id1);

			}

			return friendIDs;

		} catch (SQLException e) {

			logger.warn("Error: Failed to fetch friends\n" + e.getMessage());
			throw e;

		}

	}

	/**
	 * Helper method to cut down the size of methods. Uses the list of friend IDs to
	 * make a string to finish the query WHERE clause
	 * 
	 * @param friendIDs A linkedList of friendIDs
	 * @return A string that of the ids. Ex: "1 OR 3 OR 2"
	 */
	private String createIDsString(LinkedList<Integer> friendIDs) {

		StringBuilder sb = new StringBuilder("");

		// Create the OR list for the query
		sb.append(friendIDs.removeFirst());

		while (friendIDs.size() > 0) {

			sb.append(" OR trainer_id = ");
			sb.append(friendIDs.removeFirst());

		}
		logger.info(sb.toString());
		return sb.toString();

	}

	/**
	 * Helper method to break up code. Takes in the formated id string and retreives
	 * the list of the friends objs
	 * 
	 * @param idList String of ids ex: "1 OR 3 OR 2"
	 * @return An ArrayList of the friends objs
	 * @throws SQLException Thrown when there's an issue talking to the db
	 */
	private List<User> getFriends(String idList) throws SQLException {

		PreparedStatement ps;
		ResultSet rs;
		List<User> users = new ArrayList<>();

		try (Connection c = ConnectionUtil.getConnection()) {
			logger.info(FETCH_FRND + idList);
			ps = c.prepareStatement(FETCH_FRND + idList);
			rs = ps.executeQuery();

			while (rs.next())

				users.add(new User(rs.getString(2), rs.getString(4), rs.getString(5), rs.getInt(1), rs.getInt(6), rs.getInt(7), rs.getInt(8), rs.getInt(9),rs.getLong(10)));

			return users;

		} catch (SQLException e) {

			logger.warn("Error: Failed to fetch friends\n" + e.getMessage());
			throw e;

		}

	}

	/**
	 * This method takes a user object and updates the badges, wins and losses in
	 * the db to the current numbers given by the passed in user obj
	 * 
	 * @param user The user object which contains the states
	 * @return Returns whether only one record was updated
	 * @exception SQLException Thrown when there's an issue talking to the db
	 */
	public boolean updateStats(User user) throws SQLException {

		PreparedStatement ps;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(UPDATE_STATS);
			ps.setInt(1, user.getBadges());
			ps.setInt(2, user.getWins());
			ps.setInt(3, user.getLosses());
			ps.setInt(4, user.getId());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {

			logger.warn("Failed to update stats\n" + e.getMessage());
			throw e;

		}

	}

	@Override
	public boolean updateCounter(User user) throws SQLException {
		PreparedStatement ps;

		try (Connection c = ConnectionUtil.getConnection()) {

			ps = c.prepareStatement(UPDATE_COUNTER);
			ps.setInt(1, user.getCounter());
			ps.setLong(2, user.getcTime());
			ps.setInt(3, user.getId());

			return ps.executeUpdate() == 1;

		} catch (SQLException e) {

			logger.warn("Failed to update counter\n" + e.getMessage());
			throw e;

		}
	}

}
