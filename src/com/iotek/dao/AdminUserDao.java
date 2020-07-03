package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;

public interface AdminUserDao {
	//修改积分
	public int reUserPoint(int uid,int point) throws Exception;
	//修改等级
	public int reUserlevel(String name,String level) throws Exception;
	//查所有用户
	public List<Users> showAllUsers() throws Exception;
	//查看冻结表
	public List<Frozentime> showFrozen() throws Exception;
	//解冻用户
	public int changefrozen(int id) throws Exception;
	//冻结用户
	public int frozenUser(int uid) throws Exception;

}
