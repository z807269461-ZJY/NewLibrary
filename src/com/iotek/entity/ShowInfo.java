package com.iotek.entity;

public class ShowInfo {
	private int id;
	private String bookName;
	private String type;
	private String author;
	private int inorout;
	private int state;
	String lendtime;
	String returntime;
	String comment;
	
	public ShowInfo(int id, String bookName, String comment) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.comment = comment;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public ShowInfo(int id, String bookName, int state) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.state = state;
	}
	public ShowInfo(int id, String bookName, String lendtime, String returntime) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.lendtime = lendtime;
		this.returntime = returntime;
	}
	public String getLendtime() {
		return lendtime;
	}
	public void setLendtime(String lendtime) {
		this.lendtime = lendtime;
	}
	public String getReturntime() {
		return returntime;
	}
	public void setReturntime(String returntime) {
		this.returntime = returntime;
	}
	public ShowInfo(int id, String bookName, String type, String author, int inorout, int state) {
		super();
		this.id = id;
		this.bookName = bookName;
		this.type = type;
		this.author = author;
		this.inorout = inorout;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getBookName() {
		return bookName;
	}
	public void setBookName(String bookName) {
		this.bookName = bookName;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public String getAuthor() {
		return author;
	}
	public void setAuthor(String author) {
		this.author = author;
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
	@Override
	public String toString() {
		return "ShowInfo [id=" + id + ", bookName=" + bookName + ", type=" + type + ", author=" + author + ", inorout="
				+ inorout + ", state=" + state + "]";
	}
	
	public String toShowBorrow() {
		return "records [id=" + id + ", bookName=" + bookName + ", lendTime=" + lendtime + ", returnTime=" + returntime  + "]";
	}
	
	public String toShowOrder() {
		return "Order [id=" + id + ", bookName=" + bookName + ", state=" + state   + "]";
	}
	
	public String toShowCom() {
		return "Order [id=" + id + ", bookName=" + bookName + ", comment=" + comment   + "]";
	}

}
