package com.iotek.view;

import java.util.Scanner;

public class AdminView {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	public void adminView()throws Exception {
		while (true) {
			System.out.println("--------����Ա������-------");
			System.out.println("-------1.ͼ�����-------");
			System.out.println("-------2.�û�����-------");
			System.out.println("-------0.������һ��-------");
			choose = scanner.next();
			switch (choose) {
			case "1"://ͼ�����
				new Adminbook().adminbook();
				break;
			case"2"://�û�����
				new Adminuser().adminuser();
				break;
			case"0":
				new MainView().mainView();
				break;
			default:
				System.out.println("��������������������......");
				continue;
			}
		}
		
	}
	
}
