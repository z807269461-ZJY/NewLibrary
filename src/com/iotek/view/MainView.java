package com.iotek.view;

import java.util.Scanner;

public class MainView {
	private Scanner scanner = new Scanner(System.in);
	private String choose;

	public void mainView() throws Exception {
		while (true) {
			System.out.println("-------��ӭ����XXXXͼ�����ϵͳ-------");
			System.out.println("-------1.����Ա-------");
			System.out.println("-------2.��    ��-------");
			System.out.println("-------0.�˳�-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// ����Ա
				adminLogin();
				break;
			case "2":
				// �û�
				new UserView().userView();
				break;
			case "0":
				System.out.println("�˳��ɹ�!!!");
				System.exit(0);
				break;
			default:
				System.out.println("��������������������......");
				continue;
			}
		}
	}

	public void adminLogin() throws Exception {
		System.out.println("------����Ա��½����------");
		while (true) {
			System.out.println("�������û���......");
			String userName = scanner.next();
			System.out.println("����������......");
			String password = scanner.next();
			if (userName.equals("admin") && password.equals("111")) {
				System.out.println("��½�ɹ�");
				// ����Ա������
				new AdminView().adminView();
			} else {
				System.out.println("�û������������,�����µ�½");
			}
		}
	}
}
