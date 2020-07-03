package com.iotek.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import com.iotek.dao.UsersDao;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class UserDaoImpl extends BaseDao implements UsersDao{
	private Scanner scanner = new Scanner(System.in);
	private Utils util;
	// 根据用户名查询
	@Override
	public Users findUserByName(String name) throws Exception {
		connection = getConnection();
		Users user = null;
		sql = "select * from users where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			name = rs.getString("name");
			String password = rs.getString("password");
			user = new Users(id, name, password);
		}
		closeAll();
		return user;
	}
	// 添加用户 注册
	@Override
	public int addUser(Users user) throws Exception {
		connection = getConnection();
		sql = "insert into users(name,password)values(?,?)";
		int res = -1;
		ps = connection.prepareStatement(sql);
		ps.setString(1, user.getName());
		ps.setString(2, user.getPassword());
		res = ps.executeUpdate();
		closeAll();
		return res;
	}
	// 根据用户名和密码查询(登录)
	@Override
	public List<Users> findUserByNameAndPass(String name, String password) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}
	//检查积分
	@Override
	public void frozen(String name,int point) throws Exception {
		connection = getConnection();
		if(point>=0) {
			System.out.println("账户积分为："+point);
		}else {
			System.out.println("积分"+point+"少于0，账户冻结");
			sql = "select id from users where name=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, name);
			rs = ps.executeQuery();
			while (rs.next()) {
				int uid=rs.getInt("id");
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String frozentime = df.format(new Date());
				sql = "insert into frozentime(uid,frozentime)values(?,?)";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, uid);
				ps.setString(2, frozentime);
				ps.executeUpdate();
			}
		}
	}

	/*
	 * @Override public void loginfrozen(String name) throws Exception { connection
	 * = getConnection(); sql = "select point from users where name=?"; ps =
	 * connection.prepareStatement(sql); ps.setString(1, name); rs =
	 * ps.executeQuery(); while (rs.next()) { int point=rs.getInt("point");
	 * if(point<0) {
	 * System.out.println("对不起，您的积分为"+point+"少于0,账户被冻结，是否需要充值解冻？需要请输入y"); String
	 * choose=scanner.next(); if(choose.equals("Y")||choose.equals("y")) {
	 * System.out.println("请输入充值金额（1元等于1积分）"); int money=util.inputIsNum();
	 * System.out.println("充值成功"); point=point+money; sql =
	 * "update users set point=? where name=?"; ps =
	 * connection.prepareStatement(sql); ps.setInt(1, point); ps.setString(2, name);
	 * ps.executeUpdate(); new UserBookDaoImpl().changelevel(name, point);
	 * loginfrozen( name); }else { System.out.println("拒绝充值，请联系管理员,再见!!!!");
	 * System.exit(0); } } } }
	 */
	//检查是否冻结
	public boolean ifFrozen(int id) throws Exception{
		connection = getConnection();
		sql = "select unfrozentime from frozentime where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		boolean flag=true;
		while(rs.next()) {
			String unfrozentime=rs.getString("unfrozentime");
			if(unfrozentime==null) {
				flag=false;
				break;
			}
		}
		closeAll();
		return flag;
	}
	//检测是否有逾期的书没还
	public boolean ifovertime(int uid) throws Exception{
		boolean flag=true;
		connection = getConnection();
		sql = "select lendTime,returnTime from records where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		rs = ps.executeQuery();
		while(rs.next()) {
		    String lendTime=rs.getString("lendTime");
			String returntime=rs.getString("returnTime");
			if(returntime==null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
				String returntime1 = df.format(new Date());
				Date d1 = df.parse(lendTime);
				Date d2 = df.parse(returntime1);
				long diff = d2.getTime() - d1.getTime();// 这样得到的差值是毫秒级别
				long days = diff / (1000 * 60 * 60 * 24);
				if(days>30) {
					flag=false;
				}
			}
		}
		closeAll();
		return flag;
		
	}
	//登录时判断预约的书是否上架可以借了
	public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception{
		ArrayList<Integer> orderNewID =new ArrayList<Integer>();
		connection = getConnection();
		sql = "select inorout,state from bookinfo where bid=?";
		ps = connection.prepareStatement(sql);
		for (Integer bid : orderID) {
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			while(rs.next()) {
				int inorout =rs.getInt("inorout");
				int state= rs.getInt("state");
				if(inorout==1&&state==1) {
					orderNewID.add(bid);
				}
			}
		}
		closeAll();
		return orderNewID;
		
	}

}
