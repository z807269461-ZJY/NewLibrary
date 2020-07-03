package com.iotek.biz.impl;

import java.util.ArrayList;

import com.iotek.biz.UserBiz;
import com.iotek.dao.UsersDao;
import com.iotek.dao.impl.UserDaoImpl;
import com.iotek.entity.Users;

public class UserBizImpl implements UserBiz {
	private UsersDao userDao= new UserDaoImpl();
	// ��֤�û����Ƿ����
	@Override
	public boolean exist(String userName) throws Exception {
		Users user = userDao.findUserByName(userName);
		if (user == null) {
			return true;
		}
		return false;
	}
	// ע��
	@Override
	public boolean register(Users user) throws Exception {
		int res = userDao.addUser(user);
		if (res != -1) {
			return true;
		}
		return false;
	}
	// ��½
	@Override
	public boolean login(Users user) throws Exception {
		Users u = userDao.findUserByName(user.getName());
		if (u == null) {
			return false;
		}
		if (u.getPassword().equals(user.getPassword())) {
			return true;
		}
		return false;
	}
	//�Ƿ񶳽�
	@Override
	public void frozen(String name,int point) throws Exception {
		userDao.frozen(name, point);
	}
	//������
	
	/*
	 * public void loginfrozen(String name) throws Exception {
	 * userDao.loginfrozen(name);
	 * 
	 * }
	 */
	@Override
	public Users findUserByName(String name) throws Exception {
		// TODO Auto-generated method stub
		return userDao.findUserByName(name);
	}
	//����Ƿ񶳽�
	public boolean ifFrozen(int id) throws Exception{
		return userDao.ifFrozen(id);
	}
	//����Ƿ������ڵ���û��
	public boolean ifovertime(int uid) throws Exception{
		return userDao.ifovertime(uid);
	}
	//��¼ʱ�ж�ԤԼ�����Ƿ��ϼܿ��Խ���
		public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception{
			return userDao.loginifborrow(orderID);
		}

}
