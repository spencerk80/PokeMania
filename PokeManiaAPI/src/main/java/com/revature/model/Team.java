package com.revature.model;

public class Team {
	
	private int userId;
	private int poke1, poke2, poke3, poke4, poke5, poke6;
	public Team(int userId, int poke1, int poke2, int poke3, int poke4, int poke5, int poke6) {
		super();
		this.userId = userId;
		this.poke1 = poke1;
		this.poke2 = poke2;
		this.poke3 = poke3;
		this.poke4 = poke4;
		this.poke5 = poke5;
		this.poke6 = poke6;
	}
	public Team() {
		super();
		// TODO Auto-generated constructor stub
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public int getPoke1() {
		return poke1;
	}
	public void setPoke1(int poke1) {
		this.poke1 = poke1;
	}
	public int getPoke2() {
		return poke2;
	}
	public void setPoke2(int poke2) {
		this.poke2 = poke2;
	}
	public int getPoke3() {
		return poke3;
	}
	public void setPoke3(int poke3) {
		this.poke3 = poke3;
	}
	public int getPoke4() {
		return poke4;
	}
	public void setPoke4(int poke4) {
		this.poke4 = poke4;
	}
	public int getPoke5() {
		return poke5;
	}
	public void setPoke5(int poke5) {
		this.poke5 = poke5;
	}
	public int getPoke6() {
		return poke6;
	}
	public void setPoke6(int poke6) {
		this.poke6 = poke6;
	}
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + poke1;
		result = prime * result + poke2;
		result = prime * result + poke3;
		result = prime * result + poke4;
		result = prime * result + poke5;
		result = prime * result + poke6;
		result = prime * result + userId;
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
		Team other = (Team) obj;
		if (poke1 != other.poke1)
			return false;
		if (poke2 != other.poke2)
			return false;
		if (poke3 != other.poke3)
			return false;
		if (poke4 != other.poke4)
			return false;
		if (poke5 != other.poke5)
			return false;
		if (poke6 != other.poke6)
			return false;
		if (userId != other.userId)
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Team [userId=" + userId + ", poke1=" + poke1 + ", poke2=" + poke2 + ", poke3=" + poke3 + ", poke4="
				+ poke4 + ", poke5=" + poke5 + ", poke6=" + poke6 + "]";
	}
	
	
	
	
	

}
