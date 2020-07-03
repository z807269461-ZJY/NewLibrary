package com.iotek.biz.impl;

import java.util.List;

import com.iotek.biz.AdminUserBiz;
import com.iotek.dao.AdminUserDao;
import com.iotek.dao.impl.AdminUserDaoImpl;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;

public class AdminUserBizImpl implements AdminUserBiz {
	private AdminUserDao AdUDao =new AdminUserDaoImpl();
	//修改积分
	@Override
	public boolean reUserPoint(int uid, int point) throws Exception {
		int res = AdUDao.reUserPoint(uid, point);
		if (res != -1&&res!=0) {
			return true;
		}else if(res == 0) {
			return false;
		}
		return false;
	}
	//修改等级
	@Override
	public boolean reUserlevel(String name, String level) throws Exception {
		int res = AdUDao.reUserlevel(name, level);
		if (res != -1&&res!=0) {
			return true;
		}else if(res == 0) {
			System.out.println("用户名不存在");
			return false;
		}
		return false;
	}
	@Override
	public List<Users> showAllUsers() throws Exception {
		// TODO Auto-generated method stub
		return AdUDao.showAllUsers();
	}
	@Override
	public List<Frozentime> showFrozen() throws Exception {
		// TODO Auto-generated method stub
		return AdUDao.showFrozen();
	}
	@Override
	public int changefrozen(int id) throws Exception {
		return AdUDao.changefrozen(id);
	}
	//冻结用户
	public boolean frozenUser(int uid) throws Exception{
		boolean flag=true;
		int res=AdUDao.frozenUser(uid);
		if(res==-1) {
			flag=false;
		}
		return flag;
	}

}
