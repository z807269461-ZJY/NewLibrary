package com.iotek.entity;

public class Users {
	private int id;
	private String name;
	private String password;
	private int point;//积分
	private String level;//等级
	
	public Users(int id, String name, String password) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
	}
	
	public Users(String name, String password) {
		super();
		this.name = name;
		this.password = password;
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
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public int getPoint() {
		return point;
	}
	public void setPoint(int point) {
		this.point = point;
	}
	public String getLevel() {
		return level;
	}
	public void setLevel(String level) {
		this.level = level;
	}
	@Override
	public String toString() {
		return "Users [id=" + id + ", name=" + name + ", password=" + password + ", point=" + point + ", level=" + level
				+ "]";
	}
	

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		result = prime * result + ((level == null) ? 0 : level.hashCode());
		result = prime * result + ((name == null) ? 0 : name.hashCode());
		result = prime * result + ((password == null) ? 0 : password.hashCode());
		result = prime * result + point;
		return result;
	}
	
	public Users(int id, String name, String password, int point, String level) {
		super();
		this.id = id;
		this.name = name;
		this.password = password;
		this.point = point;
		this.level = level;
	}
	
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Users other = (Users) obj;
		if (id != other.id)
			return false;
		if (level == null) {
			if (other.level != null)
				return false;
		} else if (!level.equals(other.level))
			return false;
		if (name == null) {
			if (other.name != null)
				return false;
		} else if (!name.equals(other.name))
			return false;
		if (password == null) {
			if (other.password != null)
				return false;
		} else if (!password.equals(other.password))
			return false;
		if (point != other.point)
			return false;
		return true;
	}
}
