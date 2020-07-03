package com.iotek.biz;


import java.util.ArrayList;

import com.iotek.entity.Users;

public interface UserBiz {
	// 验证用户名是否存在
		public boolean exist(String userName) throws Exception;
		// 注册
		public boolean register(Users user) throws Exception;
		// 登陆
		public boolean login(Users user)throws Exception;
		//检测是否冻结
		public void frozen(String name,int point)throws Exception;
		//检查积分
		//public void loginfrozen(String name)throws Exception;
		// 根据用户名查询
		public Users findUserByName(String name) throws Exception;
		//检查是否冻结
		public boolean ifFrozen(int id) throws Exception;
		//检测是否有逾期的书没还
		public boolean ifovertime(int uid) throws Exception;
		//登录时判断预约的书是否上架可以借了
		public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception;
}
