package com.iotek.entity;

public class Books {
	private int id;
	private String name;
	private int count;
	private String type;
	private String author;
	private int discount;// 借出的次数
	//private int hasLended;// 已借出的数量

	/*
	 * public Books(int id, String name, int count, String type, String author, int
	 * discount, int hasLended) { super(); this.id = id; this.name = name;
	 * this.count = count; this.type = type; this.author = author; this.discount =
	 * discount; this.hasLended = hasLended; }
	 */

	public Books(int id, String name, int count, String type, String author, int discount) {
		super();
		this.id = id;
		this.name = name;
		this.count = count;
		this.type = type;
		this.author = author;
		this.discount = discount;
	}

	public Books(String name, int count, String type, String author) {
		super();
		this.name = name;
		this.count = count;
		this.type = type;
		this.author = author;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
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

	public int getDiscount() {
		return discount;
	}

	public void setDiscount(int discount) {
		this.discount = discount;
	}

	/*
	 * public int getHasLended() { return hasLended; }
	 * 
	 * public void setHasLended(int hasLended) { this.hasLended = hasLended; }
	 */

	@Override
	public String toString() {
		return "Books [id=" + id + ", name=" + name + ", count=" + count + ", type=" + type + ", author=" + author
				+ ", discount=" + discount +  "]";
	}

}
