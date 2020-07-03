package com.iotek.view;

import java.util.Scanner;

public class AdminView {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	public void adminView()throws Exception {
		while (true) {
			System.out.println("--------管理员主界面-------");
			System.out.println("-------1.图书管理-------");
			System.out.println("-------2.用户管理-------");
			System.out.println("-------0.返回上一级-------");
			choose = scanner.next();
			switch (choose) {
			case "1"://图书管理
				new Adminbook().adminbook();
				break;
			case"2"://用户管理
				new Adminuser().adminuser();
				break;
			case"0":
				new MainView().mainView();
				break;
			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
		
	}
	
}
