package com.iotek.entity;

public class Book {
	private int id;
	private int uid;
	private int bid;
	private int state;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getUid() {
		return uid;
	}
	public void setUid(int uid) {
		this.uid = uid;
	}
	public int getBid() {
		return bid;
	}
	public void setBid(int bid) {
		this.bid = bid;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	@Override
	public String toString() {
		return "Book [id=" + id + ", uid=" + uid + ", bid=" + bid + ", state=" + state + "]";
	}
	public Book(int id, int uid, int bid, int state) {
		super();
		this.id = id;
		this.uid = uid;
		this.bid = bid;
		this.state = state;
	}
	

}
