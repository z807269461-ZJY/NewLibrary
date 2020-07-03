package com.iotek.entity;

public class Comments {
	private int id;
	private int bid;
	private String comment;
	public int getId() {
		return id;
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
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	@Override
	public String toString() {
		return "Comments [id=" + id + ", bid=" + bid + ", comment=" + comment + "]";
	}
	public Comments(int id, int bid, String comment) {
		super();
		this.id = id;
		this.bid = bid;
		this.comment = comment;
	}
	

}
