package com.iotek.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iotek.dao.AdminUserDao;
import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;

public class AdminUserDaoImpl extends BaseDao implements AdminUserDao{

//修改积分
	@Override
	public int reUserPoint(int uid, int point) throws Exception {
		connection=getConnection();
		sql = "update users set point=? where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, point);
		ps.setInt(2, uid);
		int res=-1;
		res = ps.executeUpdate();
		new UserBookDaoImpl().changelevel(uid, point);
		closeAll();
		return res;
	}
//修改等级
	@Override
	public int reUserlevel(String name, String level) throws Exception {
		connection=getConnection();
		sql = "update users set level=? where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, level);
		ps.setString(2, name);
		int res=-1;
		res = ps.executeUpdate();
		closeAll();
		return res;
	}
	//查看所有用户
	@Override
	public List<Users> showAllUsers() throws Exception {
		List<Users> list = new ArrayList<Users>();
		connection = getConnection();
		sql = "select * from users";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			String password = rs.getString("password");
			int point = rs.getInt("point");
			String level = rs.getString("level");
			Users users = new Users(id, name, password, point, level);
			list.add(users);
		}
		closeAll();
		return list;
	}
	//查看冻结表
	@Override
	public List<Frozentime> showFrozen() throws Exception {
		List<Frozentime> list = new ArrayList<Frozentime>();
		connection = getConnection();
		sql = "select * from frozentime";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			int uid = rs.getInt("uid");
			String frozentime=rs.getString("frozentime");
			String unfrozentime=rs.getString("unfrozentime");
			Frozentime frozen=new Frozentime(id, uid, frozentime, unfrozentime);
			list.add(frozen);
		}
		closeAll();
		return list;
	}
	//解冻用户
	@Override
	public int changefrozen(int id) throws Exception {
		connection=getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String unfrozentime = df.format(new Date());
		int res=-1;
		sql = "select unfrozentime from frozentime where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		String unfrozentime1=null;
		while (rs.next()) {
			unfrozentime1=rs.getString("unfrozentime");
		}
		if(unfrozentime1==null) {
			sql = "update frozentime set unfrozentime=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, unfrozentime);
			ps.setInt(2, id);
			res=ps.executeUpdate();
		}
		return res;
	}
	//冻结用户
	public int frozenUser(int uid) throws Exception{
		connection=getConnection();
		int res=-1;
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String frozentime = df.format(new Date());
		sql = "insert into frozentime(uid,frozentime)values(?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setString(2, frozentime);
		res = ps.executeUpdate();
		closeAll();
		return res;
	}

}
