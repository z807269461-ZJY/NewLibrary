package com.iotek.entity;

public class Bookinfo {
	private int id;
	private int bid;
	private int inorout;
	private int state;//ÊÇ·ñËð»µ
	private int lost;//ÊÇ·ñ¶ªÊ§
	public int getId() {
		return id;
	}
	public Bookinfo(int id, int bid, int inorout, int state, int lost) {
		super();
		this.id = id;
		this.bid = bid;
		this.inorout = inorout;
		this.state = state;
		this.lost = lost;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	public int getInorout() {
		return inorout;
	}
	public void setInorout(int inorout) {
		this.inorout = inorout;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public int getLost() {
		return lost;
	}
	public void setLost(int lost) {
		this.lost = lost;
	}
	@Override
	public String toString() {
		return "Bookinfo [id=" + id + ", bid=" + bid + ", inorout=" + inorout + ", state=" + state + ", lost=" + lost
				+ "]";
	}
	public Bookinfo(int bid) {
		super();
		this.bid = bid;
	}
	
}
