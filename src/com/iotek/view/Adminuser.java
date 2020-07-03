package com.iotek.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iotek.biz.AdminUserBiz;
import com.iotek.biz.UserBiz;
import com.iotek.biz.impl.AdminUserBizImpl;
import com.iotek.biz.impl.UserBizImpl;
import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class Adminuser {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	private String name;
	private String password;
	private AdminUserBiz AdUBiz;
	private int point;
	private String level;
	private Utils util;
	private UserBiz ub;

	public void adminuser() throws Exception {
		while (true) {
			System.out.println("--------�û��������-------");
			System.out.println("-------1.ע���û�-------");
			System.out.println("-------2.�޸��û�����-------");
			System.out.println("-------3.�鿴�����û�-------");
			System.out.println("-------4.�鿴�����-------");
			System.out.println("-------5.�ⶳ�û�-------");
			System.out.println("-------6.�����û�-------");
			System.out.println("-------0.������һ��-------");
			choose = scanner.next();
			switch (choose) {
			case "1":// ע���û�
				new UserView().register();
				break;
			case "2":// �޸��û�����
				showAllUsers();
				reUserPoint();
				break;
			case "3":// �鿴�����û�
				showAllUsers();
				break;
			case "4":// �鿴�����
				showFrozen();
				break;
			case "5":// �ⶳ�û�
				changeFrozen();
				break;
			case "6":// �����û�
				showAllUsers();
				Frozen();
				break;
			case "0":
				new AdminView().adminView();
				break;
			default:
				System.out.println("��������������������......");
				continue;

			}
		}
	}
	//�޸��û�����
	public void reUserPoint() throws Exception{
		System.out.println("��������Ҫ�޸ĵ��û�ID");
		int uid=util.inputIsNum();
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		ArrayList<Integer> idlist=new ArrayList<Integer>();
		for (Users users : list) {
			idlist.add(users.getId());
		}
		if(idlist.contains(uid)) {
			System.out.println("�������»���");
			point=util.inputIsNum();
			AdUBiz=new AdminUserBizImpl();
			boolean flag=AdUBiz.reUserPoint(uid, point);
			if (flag) {
				System.out.println("�޸ĳɹ�");
			} else {
				System.out.println("�޸�ʧ��");
			}
		}else {
			System.out.println("�˺Ų�����");
		}
		
	}
	//�޸��û��ȼ�
	public void reUserlevel() throws Exception{
		System.out.println("��������Ҫ�޸ĵ��û���");
		name=scanner.next();
		System.out.println("�������µȼ�");
		level=scanner.next();
		AdUBiz=new AdminUserBizImpl();
		boolean flag=AdUBiz.reUserlevel(name, level);
		if (flag) {
			System.out.println("�޸ĳɹ�");
		} else {
			System.out.println("�޸�ʧ��");
		}
	}
	//�鿴�����û�
	public void showAllUsers() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		if (list.isEmpty()) {
			System.out.println("���û�");
		} else {
			for (Users users : list) {
				System.out.println(users.toString());
			}
		}
	}
	//�鿴�����
	public void showFrozen() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Frozentime> list = AdUBiz.showFrozen();
		if (list.isEmpty()) {
			System.out.println("���û�����");
		} else {
			for (Frozentime users : list) {
				System.out.println(users.toString());
			}
		}
	}
	//�ⶳ�û�
	public void changeFrozen() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Frozentime> list = AdUBiz.showFrozen();
		if (list.isEmpty()) {
			System.out.println("���û�����");
		} else {
			for (Frozentime users : list) {
				System.out.println(users.toString());
			}
			System.out.println("������Ҫ�ⶳ��id");
			int id=util.inputIsNum();
			int res=AdUBiz.changefrozen(id);
			if (res>0) {
				System.out.println("�ⶳ�ɹ�");
			} else if(res==-1){
				System.out.println("���˻��Լ��ⶳ");
			}else {
				System.out.println("ѡ����󣬶���ʧ��");
			}
		}
	}
	//�����û�
	public void Frozen() throws Exception{
		System.out.println("��������Ҫ������˻�ID");
		int uid =util.inputIsNum();
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		ArrayList<Integer> idlist=new ArrayList<Integer>();
		for (Users users : list) {
			idlist.add(users.getId());
		}
		if(idlist.contains(uid)) {
			ub=new UserBizImpl();
			if(ub.ifFrozen(uid)) {
				if(AdUBiz.frozenUser(uid)) {
					System.out.println("����ɹ�");
				}else {
					System.out.println("����ʧ��");
				}
			}else {
				System.out.println("�˻��ѱ�����,�޷���������");
			}
		}else {
			System.out.println("�˺Ų�����");
		}
	}
}
