package com.iotek.entity;

public class Records {
	private int id;
	private int uid;
	private int biid;
	private String lendTime;
	private String returnTime;
	
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
	public int getBiid() {
		return biid;
	}
	public void setBiid(int biid) {
		this.biid = biid;
	}
	public String getLendTime() {
		return lendTime;
	}
	public void setLendTime(String lendTime) {
		this.lendTime = lendTime;
	}
	public String getReturnTime() {
		return returnTime;
	}
	public void setReturnTime(String returnTime) {
		this.returnTime = returnTime;
	}
	@Override
	public String toString() {
		return "records [id=" + id + ", uid=" + uid + ", biid=" + biid + ", lendTime=" + lendTime + ", returnTime="
				+ returnTime + "]";
	}
	public Records(int uid, int biid, String lendTime) {
		super();
		this.uid = uid;
		this.biid = biid;
		this.lendTime = lendTime;
	}
	public Records(int uid, int biid, String lendTime, String returnTime) {
		super();
		this.uid = uid;
		this.biid = biid;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	public Records(int id, int uid, int biid, String lendTime, String returnTime) {
		super();
		this.id = id;
		this.uid = uid;
		this.biid = biid;
		this.lendTime = lendTime;
		this.returnTime = returnTime;
	}
	

}
