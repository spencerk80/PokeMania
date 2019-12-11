//package com.revature.test;
//
//import static org.junit.Assert.assertTrue;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.ResultSet;
//import java.sql.SQLException;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.revature.dao.UserDaoSql;
//import com.revature.model.User;
//import com.revature.util.ConnectionUtil;
//
//public class UserDaoTester {
//	
//	private static final String	 TEST_USRNM	= "tester11",
//								 TEST_USRNM2	= "tester22",
//								 TEST_USRNM3 = "tester33",
//								 TEST_USRNM4	= "tester44",
//								 TEST_PSWD	= "pass",
//								 RM_TST_USR	= "DELETE FROM trainers WHERE trainer_name = ?",
//								 RM_TST_FRN	= "DELETE FROM friends WHERE trainer_id1 = (SELECT trainer_id FROM trainers WHERE trainer_name = ?)"
//											+ " AND trainer_id2 = (SELECT trainer_id FROM trainers WHERE trainer_name = ?)",
//								 GET_ID		= "SELECT trainer_id FROM trainers WHERE trainer_name = ?";
//	private static		 User	 u2;
//	private static 	 	 UserDaoSql dao 		= UserDaoSql.getInstance();
//	
//	@BeforeClass
//	public static void addTestUser() throws SQLException {
//		
//			u2 = new User(TEST_USRNM2, "Bob", "Man", -2, 0, 0, 0, 0, 0);
//		
//			dao.add_TEST_newUser(u2, TEST_PSWD);
//			dao.addNewUser(new User(TEST_USRNM3, "Bob", "Man", 0, 0, 0, 0, 0, 0), TEST_PSWD);
//			dao.addNewUser(new User(TEST_USRNM4, "Bob", "Man", 0, 0, 0, 0, 0, 0), TEST_PSWD);
//		
//	}
//	
////	@Test
////	public void registerUser() {
////		
////		try {
////			
////			assertTrue(dao.addNewUser(new User(TEST_USRNM, "Bob", "Man", 0, 0, 0, 0, 0, 0), TEST_PSWD));
////			
////		} catch(SQLException e) {
////			
////			//Fail the test
////			assertTrue(false);
////			
////		}
////		
////	}
//	
//	@Test
//	public void fetchUser() {
//		
//		try {
//			
//			assertTrue(dao.fetchUser(TEST_USRNM2) != null);
//			
//		} catch (SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void getLoginTest() {
//		
//		try {
//			
//			assertTrue(dao.login(TEST_USRNM2, TEST_PSWD) != null);
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void addFriend() {
//		
//		try {
//			
//			assertTrue(dao.addFriend(TEST_USRNM2, TEST_USRNM3));
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void getFriend() {
//		
//		PreparedStatement	ps	= null;
//		ResultSet			rs	= null;
//		int					id	= 0;
//		
//		try(Connection c = ConnectionUtil.getConnection()) {
//			
//			ps = c.prepareStatement(GET_ID);
//			ps.setString(1, TEST_USRNM2);
//			rs = ps.executeQuery();
//			
//			if(rs.next())
//				
//				id = rs.getInt(1);
//			
//			else
//				
//				//Test failure
//				assertTrue(false);
//
//			assertTrue(TEST_USRNM3.equals(dao.getFriends(id)[0]));
//			
//		} catch(SQLException e) {
//			
//			e.printStackTrace(System.err);
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void getFriend2() { //Swaps the two users around to make sure we can still get the friend pair
//		
//		PreparedStatement	ps	= null;
//		ResultSet			rs	= null;
//		int					id	= 0;
//		
//		try(Connection c = ConnectionUtil.getConnection()) {
//			
//			ps = c.prepareStatement(GET_ID);
//			ps.setString(1, TEST_USRNM3);
//			rs = ps.executeQuery();
//			
//			if(rs.next())
//				
//				id = rs.getInt(1);
//			
//			else
//				
//				//Test failure
//				assertTrue(false);
//			
//			assertTrue(TEST_USRNM2.equals(dao.getFriends(id)[0]));
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void getFriend_NoFriends() {
//		
//		PreparedStatement	ps	= null;
//		ResultSet			rs	= null;
//		int					id	= 0;
//		
//		try(Connection c = ConnectionUtil.getConnection()) {
//			
//			ps = c.prepareStatement(GET_ID);
//			ps.setString(1, TEST_USRNM4);
//			rs = ps.executeQuery();
//			
//			if(rs.next())
//				
//				id = rs.getInt(1);
//			
//			else
//				
//				//Test failure
//				assertTrue(false);
//			
//			assertTrue(dao.getFriends(id) == null);
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
//	
//	@Test
//	public void updateStats() {
//		
//		u2.setWins(7);
//		
//		try {
//			
//			assertTrue(dao.updateStats(u2));
//			
//		} catch (SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//
//		}
//		
//	}
//	
//	@AfterClass
//	public static void removeTestUser() throws SQLException {
//		
//		PreparedStatement ps;
//		
//		try(Connection c = ConnectionUtil.getConnection()) {
//			
//			ps = c.prepareStatement(RM_TST_FRN);
//			ps.setString(1, TEST_USRNM2);
//			ps.setString(2, TEST_USRNM3);
//			ps.executeUpdate();
//			
//			ps = c.prepareStatement(RM_TST_USR);
//			ps.setString(1, TEST_USRNM);
//			ps.executeUpdate();
//			
//			ps = c.prepareStatement(RM_TST_USR);
//			ps.setString(1, TEST_USRNM2);
//			ps.executeUpdate();
//			
//			ps = c.prepareStatement(RM_TST_USR);
//			ps.setString(1, TEST_USRNM3);
//			ps.executeUpdate();
//			
//			ps = c.prepareStatement(RM_TST_USR);
//			ps.setString(1, TEST_USRNM4);
//			ps.executeUpdate();
//			
//		} catch(SQLException e) {
//			
//			throw e;
//			
//		}
//		
//	}
//
//}
