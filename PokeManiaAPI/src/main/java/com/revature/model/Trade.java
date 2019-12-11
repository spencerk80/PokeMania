package com.revature.model;

import com.revature.TradeStatus;

/**
 * A model that represents a trade happening between two trainers holding their ids and
 * the ids of the pokemon offered as well as the current status of the trade and its id
 * 
 * @author Kristoffer Spencer
 */
public class Trade {

	///id, t_id1, p_id1, t_id2, p_id2, status
	private	int			id,
						t_id1,
						t_id2,
						p_id1,
						p_id2;
	private TradeStatus	status;
	public Trade(int id, int trainer_id1, int trainer_id2, int pokemon_id1, int pokemon_id2, TradeStatus status) {
		super();
		this.id = id;
		this.t_id1 = trainer_id1;
		this.t_id2 = trainer_id2;
		this.p_id1 = pokemon_id1;
		this.p_id2 = pokemon_id2;
		this.status = status;
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public int getTrainer_id1() {
		return t_id1;
	}
	
	public void setTrainer_id1(int t_id1) {
		this.t_id1 = t_id1;
	}
	
	public int getTrainer_id2() {
		return t_id2;
	}
	
	public void setTrainer_id2(int t_id2) {
		this.t_id2 = t_id2;
	}
	
	public int getPokemon_id1() {
		return p_id1;
	}
	
	public void setPokemon_id1(int p_id1) {
		this.p_id1 = p_id1;
	}
	
	public int getPokemon_id2() {
		return p_id2;
	}
	
	public void setPokemon_id2(int p_id2) {
		this.p_id2 = p_id2;
	}
	
	public TradeStatus getStatus() {
		return status;
	}
	
	public void setStatus(TradeStatus status) {
		this.status = status;
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
		Trade other = (Trade) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	@Override
	public String toString() {
		return "Trade [id=" + id + ", trainer_id1=" + t_id1 + ", trainer_id2=" + t_id2 + ", pokemon_id1=" + p_id1 + ", pokemon_id2=" + p_id2
				+ ", status=" + status + "]";
	}
	
}
