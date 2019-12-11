package com.revature.model;

/**
 * A model that holds all the relevant data of a pokemon for PokeMania
 * 
 * @author Kristoffer Spencer
 */
public class Pokemon {
	
	private int		id,
					trainerID,
					dexNum,
					level,
					hp,
					att,
					def,
					spd;
	private String	type1,
					type2,
					frontImg,
					backImg;
	public Pokemon(int id, int trainerID, int dexNum, int level, int hp, int att, int def, int spd, String type1,
			String type2, String frontImg, String backImg) {
		super();
		this.id = id;
		this.trainerID = trainerID;
		this.dexNum = dexNum;
		this.level = level;
		this.hp = hp;
		this.att = att;
		this.def = def;
		this.spd = spd;
		this.type1 = type1;
		this.type2 = type2;
		this.frontImg = frontImg;
		this.backImg = backImg;
	}

	public Pokemon() {
		super();
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getTrainerId() {
		return trainerID;
	}

	public void setTrainerId(int trainerID) {
		this.trainerID = trainerID;
	}

	public int getDexNum() {
		return dexNum;
	}

	public void setDexNum(int dexNum) {
		this.dexNum = dexNum;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getHp() {
		return hp;
	}

	public void setHp(int hp) {
		this.hp = hp;
	}

	public int getAtt() {
		return att;
	}

	public void setAtt(int att) {
		this.att = att;
	}

	public int getDef() {
		return def;
	}

	public void setDef(int def) {
		this.def = def;
	}

	public int getSpd() {
		return spd;
	}

	public void setSpd(int spd) {
		this.spd = spd;
	}

	public String getType1() {
		return type1;
	}

	public void setType1(String type1) {
		this.type1 = type1;
	}

	public String getType2() {
		return type2;
	}

	public void setType2(String type2) {
		this.type2 = type2;
	}

	public String getFrontImg() {
		return frontImg;
	}

	public void setFrontImg(String frontImg) {
		this.frontImg = frontImg;
	}

	public String getBackImg() {
		return backImg;
	}

	public void setBackImg(String backImg) {
		this.backImg = backImg;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
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
		Pokemon other = (Pokemon) obj;
		if (id != other.id)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Pokemon [id=" + id + ", trainerID=" + trainerID + ", dexNum=" + dexNum + ", level=" + level + ", hp="
				+ hp + ", att=" + att + ", def=" + def + ", spd=" + spd + ", type1=" + type1 + ", type2=" + type2
				+ ", frontImg=" + frontImg + ", backImg=" + backImg + "]";
	}

}
