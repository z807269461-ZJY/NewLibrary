package com.iotek.entity;

public class Frozentime {
	private int id;
	private int uid;
	private String frozentime;
	private String unfrozentime;
	public Frozentime(int id, int uid, String frozentime, String unfrozentime) {
		super();
		this.id = id;
		this.uid = uid;
		this.frozentime = frozentime;
		this.unfrozentime = unfrozentime;
	}
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
	public String getFrozentime() {
		return frozentime;
	}
	public void setFrozentime(String frozentime) {
		this.frozentime = frozentime;
	}
	public String getUnfrozentime() {
		return unfrozentime;
	}
	public void setUnfrozentime(String unfrozentime) {
		this.unfrozentime = unfrozentime;
	}
	@Override
	public String toString() {
		return "Frozentime [id=" + id + ", uid=" + uid + ", frozentime=" + frozentime + ", unfrozentime=" + unfrozentime
				+ "]";
	}
	

}
