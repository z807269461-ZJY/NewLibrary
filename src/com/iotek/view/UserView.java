package com.iotek.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iotek.biz.UserBiz;
import com.iotek.biz.UserBookBiz;
import com.iotek.biz.impl.UserBizImpl;
import com.iotek.biz.impl.UserBookBizImpl;
import com.iotek.entity.Book;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class UserView {
	private Scanner scanner = new Scanner(System.in);
	private String choice;
	private UserBiz ub = new UserBizImpl();
	private UserBookBiz UBbiz;
	private Utils util =new Utils();
	
	public void userView() throws Exception {
		while (true) {
			System.out.println("------用户主菜单------");
			System.out.println("------1.注册------");
			System.out.println("------2.登陆------");
			System.out.println("------0.返回上一级------");
			choice = scanner.next();
			switch (choice) {
			case "1"://注册
				register();
				break;
			case "2":
				loginMenu();
				break;
			case "0":
				new MainView().mainView();
			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
	}
	// 注册方法
		public void register() {
			while (true) {
				System.out.println("账号");
				String name = scanner.next();
				boolean flag = false;
				try {
					flag = ub.exist(name);
					if (flag) {
						System.out.println("密码");
						String password = scanner.next();
						password=util.getMD5(password);
						Users user = new Users(name, password);
						// view做的事情做完了找业务逻辑层帮忙
						if (ub.register(user)) {
							System.out.println("注册成功");
						} else {
							System.out.println("注册失败");
						}
						if (!Utils.isGoOn("是否还要注册,需要请输入y,不需要请输入n")) {
							break;
						}
					} else {
						System.out.println("用户名已存在,请重新输入");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// 登录界面
		public void loginMenu() throws Exception {
			while (true) {
				System.out.println("账号");
				String name = scanner.next();
				System.out.println("密码");
				String password = scanner.next();
				password=util.getMD5(password);
				Users user = new Users(name, password);
				if (ub.login(user)) {
					Users user1=ub.findUserByName(name);
					if(!ub.ifFrozen(user1.getId())) {
						System.out.println("账号被冻结，无法借书、预约、续借，请联系管理员");
						System.out.println("============");
					}
					if(!ub.ifovertime(user1.getId())) {
						System.out.println("有逾期未还的书本，还书后才能进行借书等操作");
						System.out.println("============");
					}
					UBbiz = new UserBookBizImpl();
					List<Book> list1 = UBbiz.findorder(user1.getId());
					ArrayList<Integer> orderID=new ArrayList<Integer>();
					for (Book book : list1) {
						if(book.getState()==1) {//收集预约中书本的ID
							orderID.add(book.getBid());
						}
					}
					ArrayList<Integer> orderNewId=ub.loginifborrow(orderID);
					if(!orderNewId.isEmpty()) {
						for (Integer bid : orderNewId) {
							System.out.println("您预约的书本已经上架，书本信息如下：");
							System.out.println(UBbiz.findbidBooks(bid));
						}
					}
					System.out.println("登录成功");
					new UserViewBook().userviewbook(user1);
				} else {
					System.out.println("登录失败");
				}
				if (!Utils.isGoOn("是否还要登录")) {
					break;
				}
			}
		}
}
