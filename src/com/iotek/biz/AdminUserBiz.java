package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;

public interface AdminUserBiz {
	//�޸Ļ���
	public boolean reUserPoint(int uid,int point) throws Exception;
	//�޸ĵȼ�
	public boolean reUserlevel(String name,String level) throws Exception;
	//�鿴�����û�
	public List<Users> showAllUsers() throws Exception;
	//�鿴�����
	public List<Frozentime> showFrozen() throws Exception;
	//�ⶳ�û�
	public int changefrozen(int id) throws Exception;
	//�����û�
	public boolean frozenUser(int uid) throws Exception;
}
