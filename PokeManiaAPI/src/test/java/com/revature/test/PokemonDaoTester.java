//package com.revature.test;
//
//import static org.junit.Assert.assertTrue;
//
//import java.sql.Connection;
//import java.sql.PreparedStatement;
//import java.sql.SQLException;
//
//import org.junit.AfterClass;
//import org.junit.BeforeClass;
//import org.junit.Test;
//
//import com.revature.dao.PokemonDaoSql;
//import com.revature.dao.UserDaoSql;
//import com.revature.model.Pokemon;
//import com.revature.model.User;
//import com.revature.util.ConnectionUtil;
//
//public class PokemonDaoTester {
//	
//	private static final 	PokemonDaoSql	dao		= PokemonDaoSql.getInstance();
//	private static final	UserDaoSql		uDao	= UserDaoSql.getInstance();
//	private static final	String			RM_POKE	= "DELETE FROM pokemon WHERE pokemon_id = ?",
//											RM_USR	= "DELETE FROM trainers WHERE trainer_id = ?",
//											RM_TM	= "DELETE FROM pokemon_team WHERE trainer_id = ?";
//	private static 			Pokemon			p1,
//											p2,
//											p3,
//											p4,
//											p5;
//	private static final	User			t1 		= new User("Tester11", "Bob", "Man", -1, 0, 0, 0, 0, 0),
//											t2		= new User("Tester22", "Bob", "Man", -2, 0, 0, 0, 0, 0),
//											t3		= new User("Tester33", "Bob", "Man", -3, 0, 0, 0, 0, 0);
//	
//	@BeforeClass
//	public static void setup() throws SQLException {
//		
//		//Only the first two args are important. id and t_id
//		p1 = new Pokemon(-1, t1.getId(), 0, 0, 0, 0, 0, 0, "Fire", null, "img", "img");
//		p2 = new Pokemon(-2, t1.getId(), 0, 0, 0, 0, 0, 0, "Fire", null, "img", "img");
//		p3 = new Pokemon(-3, t1.getId(), 0, 0, 0, 0, 0, 0, "Fire", null, "img", "img");
//		p4 = new Pokemon(-4, t2.getId(), 0, 0, 0, 0, 0, 0, "Fire", null, "img", "img");
//
//		uDao.add_TEST_newUser(t1, "pass");
//		uDao.add_TEST_newUser(t2, "pass");
//		uDao.add_TEST_newUser(t3, "pass");
//		
//		dao.save_TEST_pokemon(p1);
//		dao.save_TEST_pokemon(p2);
//		dao.save_TEST_pokemon(p3);
//		dao.save_TEST_pokemon(p4);
//		
//	}
//	
//	@Test
//	public void testFetch() {
//		
//		try {
//			
//			assertTrue(p1.equals(dao.fetchPokemon(p1.getId())));
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
////	@Test
////	public void testTeamFetch() {
////		
////		Pokemon[]	pokemon		= {p1, p2};
////		boolean		result		= false;
////		
////		try {
////
////			if(!dao.saveTeam(pokemon))
////				
////				//Fail the test
////				assertTrue(false);
////			
////			pokemon = dao.fetchTeam(t1.getId());
////			
////			
////		} catch(SQLException e) {
////			
////			//Fail the test
////			assertTrue(false);
////			
////		}
////		
////		if(pokemon.length != 2)
////			
////			//Fail the test
////			assertTrue(false);
////		
////		//check that the two pokemon came back out. Order might be different
////		if(p1.equals(pokemon[0]))
////			
////			if(p2.equals(pokemon[1]))
////				
////				result = true;
////		
////			else ;
////		
////		else if(p2.equals(pokemon[0]))
////			
////			if(p1.equals(pokemon[1]))
////				
////				result = true;
////		
////		assertTrue(result);
////		
////	}
//
//	@Test
//	public void testFetchBox() {
//		
//		Pokemon[] pokemon	= null;
//		
//		try {
//
//			pokemon = dao.fetchBox(t1.getId());
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//		assertTrue(allThreeExist(pokemon));
//		
//	}
//	
//	@Test
//	public void testSavePokemon() {
//		
//		p5	= new Pokemon(-5, t3.getId(), 0, 0, 0, 0, 0, 0, "Fire", null, "img", "Img");
//		
//		try {
//			
//			dao.save_TEST_pokemon(p5);
//			assertTrue(p5.equals(dao.fetchPokemon(-5)));
//			
//		} catch(SQLException e) {
//			
//			//Fail the test
//			assertTrue(false);
//			
//		}
//		
//	}
////
////	@Test
////	public void testSaveTeam() {
////		
////		Pokemon[]	pokemon		= {p4};
////		
////		try {
////			
////			dao.saveTeam(pokemon);
////			pokemon = dao.fetchTeam(t2.getId());
////			
////		} catch(SQLException e) {
////			
////			//Fail the test
////			System.err.println(e.getMessage());
////			assertTrue(false);
////			
////		}
////		
////		if(pokemon.length != 1)
////			
////			//Fail the test
////			assertTrue(false);
////		
////		assertTrue(p4.equals(pokemon[0]));
////		
////	}
////	
////	private boolean allThreeExist(Pokemon[] pokemon) {
////		
////		boolean result = false;
////		
////		//Check for all three pokemon. Order might be different
////		if(p1.equals(pokemon[0]))
////			
////			if(p2.equals(pokemon[1]))
////				
////				if(p3.equals(pokemon[2]))
////					
////					result = true;
////		
////				else ;
////		
////			else if(p3.equals(pokemon[1]))
////				
////				if(p2.equals(pokemon[2]))
////					
////					result = true;
////		
////				else;
////		
////			else;
////		
////		else if(p2.equals(pokemon[0]))
////			
////			if(p1.equals(pokemon[1]))
////				
////				if(p3.equals(pokemon[2]))
////					
////					result = true;
////		
////				else ;
////		
////			else if(p3.equals(pokemon[1]))
////				
////				if(p1.equals(pokemon[2]))
////					
////					result = true;
////		
////				else;
////		
////			else;
////		
////		else if(p3.equals(pokemon[0]))
////			
////			if(p2.equals(pokemon[1]))
////				
////				if(p1.equals(pokemon[2]))
////					
////					result = true;
////		
////				else ;
////		
////			else if(p1.equals(pokemon[1]))
////				
////				if(p2.equals(pokemon[2]))
////					
////					result = true;
////		
////				else;
////		
////			else;
////		
////		return result;
////		
////	}
//	
//	@Test
//	public void deletePoke() {
//		
//		try {
//			
//			assertTrue(dao.releasePoke(p3.getId()));
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
//	@AfterClass
//	public static void cleanUp() throws SQLException {
//		
//		PreparedStatement	ps;
//		
//		try(Connection c = ConnectionUtil.getConnection()) {
//			
//			ps = c.prepareStatement(RM_TM);
//			ps.setInt(1, t1.getId());
//			ps.addBatch();
//			ps.setInt(1, t2.getId());
//			ps.addBatch();
//			ps.setInt(1, t3.getId());
//			ps.addBatch();
//			
//			ps.executeBatch();
//			
//			ps = c.prepareStatement(RM_POKE);
//			ps.setInt(1, p1.getId());
//			ps.addBatch();
//			ps.clearParameters();
//			ps.setInt(1, p2.getId());
//			ps.addBatch();
//			ps.clearParameters();
//			ps.setInt(1, p4.getId());
//			ps.addBatch();
//			ps.clearParameters();
//			ps.setInt(1, p5.getId());
//			ps.addBatch();
//			
//			ps.executeBatch();
//			
//			ps = c.prepareStatement(RM_USR);
//			ps.setInt(1, t1.getId());
//			ps.addBatch();
//			ps.clearParameters();
//			ps.setInt(1, t2.getId());
//			ps.addBatch();
//			ps.clearParameters();
//			ps.setInt(1, t3.getId());
//			ps.addBatch();
//			
//			ps.executeBatch();
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
