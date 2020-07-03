package com.iotek.dao;

import java.util.ArrayList;
import java.util.List;

import com.iotek.entity.Users;

public interface UsersDao {
	// 根据用户名查询
		public Users findUserByName(String name) throws Exception;
		// 添加用户 注册
		public int addUser(Users user) throws Exception;

		// 根据用户名和密码查询(登录)
		public List<Users> findUserByNameAndPass(String name, String password) throws Exception;
		//是否冻结
		public void frozen(String name,int point)throws Exception;
		//登录检测是否冻结
		//public void loginfrozen(String name)throws Exception;
		//检查是否冻结
		public boolean ifFrozen(int id) throws Exception;
		//检测是否有逾期的书没还
		public boolean ifovertime(int uid) throws Exception;
		//登录时判断预约的书是否上架可以借了
		public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception;
		
}
