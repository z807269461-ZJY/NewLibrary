package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;

public interface AdminUserDao {
	//�޸Ļ���
	public int reUserPoint(int uid,int point) throws Exception;
	//�޸ĵȼ�
	public int reUserlevel(String name,String level) throws Exception;
	//�������û�
	public List<Users> showAllUsers() throws Exception;
	//�鿴�����
	public List<Frozentime> showFrozen() throws Exception;
	//�ⶳ�û�
	public int changefrozen(int id) throws Exception;
	//�����û�
	public int frozenUser(int uid) throws Exception;

}
