package com.iotek.biz;


import java.util.ArrayList;

import com.iotek.entity.Users;

public interface UserBiz {
	// ��֤�û����Ƿ����
		public boolean exist(String userName) throws Exception;
		// ע��
		public boolean register(Users user) throws Exception;
		// ��½
		public boolean login(Users user)throws Exception;
		//����Ƿ񶳽�
		public void frozen(String name,int point)throws Exception;
		//������
		//public void loginfrozen(String name)throws Exception;
		// �����û�����ѯ
		public Users findUserByName(String name) throws Exception;
		//����Ƿ񶳽�
		public boolean ifFrozen(int id) throws Exception;
		//����Ƿ������ڵ���û��
		public boolean ifovertime(int uid) throws Exception;
		//��¼ʱ�ж�ԤԼ�����Ƿ��ϼܿ��Խ���
		public ArrayList<Integer> loginifborrow (ArrayList<Integer> orderID) throws Exception;
}
