package com.iotek.dao;

import java.util.ArrayList;
import java.util.List;

import com.iotek.entity.Users;

public interface UsersDao {
	// �����û�����ѯ
		public Users findUserByName(String name) throws Exception;
		// ����û� ע��
		public int addUser(Users user) throws Exception;

		// �����û����������ѯ(��¼)
		public List<Users> findUserByNameAndPass(String name, String password) throws Exception;
		//�Ƿ񶳽�
		public void frozen(String name,int point)throws Exception;
		//��¼����Ƿ񶳽�
		//public void loginfrozen(String name)throws Exception;
		//����Ƿ񶳽�
		public boolean ifFrozen(int id) throws Exception;
		//����Ƿ������ڵ���û��
		public boolean ifovertime(int uid) throws Exception;
		//��¼ʱ�ж�ԤԼ�����Ƿ��ϼܿ��Խ���
		public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception;
		
}
