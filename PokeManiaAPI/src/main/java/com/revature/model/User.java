package com.revature.model;

/**
 * A model to represent a user of the system. (A trainer, as known to the game)
 * 
 * @author Kristoffer Spencer
 */
public class User {
	
	private	String	username,
					firstname,
					lastname;
	private	int		id,
					badges,
					wins,
					losses,
					counter;
	private long	cTime;
	
	public User() {
		super();
		// TODO Auto-generated constructor stub
	}
	public User(String username, String firstname, String lastname, int id, int badges, int wins, int losses,
			int counter, long cTime) {
		super();
		this.username = username;
		this.firstname = firstname;
		this.lastname = lastname;
		this.id = id;
		this.badges = badges;
		this.wins = wins;
		this.losses = losses;
		this.counter = counter;
		this.cTime = cTime;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBadges() {
		return badges;
	}
	public void setBadges(int badges) {
		this.badges = badges;
	}
	public int getWins() {
		return wins;
	}
	public void setWins(int wins) {
		this.wins = wins;
	}
	public int getLosses() {
		return losses;
	}
	public void setLosses(int losses) {
		this.losses = losses;
	}
	public int getCounter() {
		return counter;
	}
	public void setCounter(int counter) {
		this.counter = counter;
	}
	public long getcTime() {
		return cTime;
	}
	public void setcTime(long cTime) {
		this.cTime = cTime;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + badges;
		result = prime * result + (int) (cTime ^ (cTime >>> 32));
		result = prime * result + counter;
		result = prime * result + ((firstname == null) ? 0 : firstname.hashCode());
		result = prime * result + id;
		result = prime * result + ((lastname == null) ? 0 : lastname.hashCode());
		result = prime * result + losses;
		result = prime * result + ((username == null) ? 0 : username.hashCode());
		result = prime * result + wins;
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (badges != other.badges)
			return false;
		if (cTime != other.cTime)
			return false;
		if (counter != other.counter)
			return false;
		if (firstname == null) {
			if (other.firstname != null)
				return false;
		} else if (!firstname.equals(other.firstname))
			return false;
		if (id != other.id)
			return false;
		if (lastname == null) {
			if (other.lastname != null)
				return false;
		} else if (!lastname.equals(other.lastname))
			return false;
		if (losses != other.losses)
			return false;
		if (username == null) {
			if (other.username != null)
				return false;
		} else if (!username.equals(other.username))
			return false;
		if (wins != other.wins)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "User [username=" + username + ", firstname=" + firstname + ", lastname=" + lastname + ", id=" + id
				+ ", badges=" + badges + ", wins=" + wins + ", losses=" + losses + ", counter=" + counter + ", cTime="
				+ cTime + "]";
	}
	
	
	
	
}
