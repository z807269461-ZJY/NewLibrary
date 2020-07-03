package com.iotek.view;

import java.util.Scanner;

public class MainView {
	private Scanner scanner = new Scanner(System.in);
	private String choose;

	public void mainView() throws Exception {
		while (true) {
			System.out.println("-------欢迎来到XXXX图书管理系统-------");
			System.out.println("-------1.管理员-------");
			System.out.println("-------2.用    户-------");
			System.out.println("-------0.退出-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// 管理员
				adminLogin();
				break;
			case "2":
				// 用户
				new UserView().userView();
				break;
			case "0":
				System.out.println("退出成功!!!");
				System.exit(0);
				break;
			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
	}

	public void adminLogin() throws Exception {
		System.out.println("------管理员登陆界面------");
		while (true) {
			System.out.println("请输入用户名......");
			String userName = scanner.next();
			System.out.println("请输入密码......");
			String password = scanner.next();
			if (userName.equals("admin") && password.equals("111")) {
				System.out.println("登陆成功");
				// 管理员主界面
				new AdminView().adminView();
			} else {
				System.out.println("用户名或密码错误,请重新登陆");
			}
		}
	}
}
