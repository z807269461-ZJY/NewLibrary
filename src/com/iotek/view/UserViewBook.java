package com.iotek.view;

import java.util.List;
import java.util.Scanner;

import com.iotek.biz.BooksBiz;
import com.iotek.biz.UserBookBiz;
import com.iotek.biz.impl.BooksBizImpl;
import com.iotek.biz.impl.UserBookBizImpl;
import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;
import com.iotek.entity.ShowInfo;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class UserViewBook {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	private UserBookBiz UBbiz;
	private BooksBiz booksBiz;
	private String name;
	private int id;
	private Utils util;

	public void userviewbook(Users user) throws Exception {
		while (true) {
			System.out.println("--------用户主界面-------");
			System.out.println("-------1.查询图书-------");
			System.out.println("-------2.借还图书-------");
			System.out.println("-------3.查询记录-------");
			System.out.println("-------4.预约查询-------");
			System.out.println("-------5.查询积分-------");
			System.out.println("-------6.查看评价-------");
			System.out.println("-------7.充值-------");
			System.out.println("-------0.返回上一级-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// 查询图书
				findBooks(user);
				break;
			case "2":
				// 借还图书
				new UserBorrow().userborrow(user);
				break;
			case "3":
				// 查询记录
				showborrow(user.getId());
				break;
			case "4":
				// 预约查询
				showorder(user);
				break;
			case "5":
				// 查询积分
				showPoint(user.getName());
				break;
			case "6":
				// 查看评价
				showcomment();
				break;
			case "7":
				// 充值
				money(user.getId());
				break;
			case "0":
				new UserView().userView();
				break;

			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
	}

	// 查询图书界面
	public void findBooks(Users user) throws Exception {
		System.out.println("-------查询菜单-------");
		System.out.println("-------1.查询所有图书-------");
		System.out.println("-------2.根据书名查询图书-------");
		System.out.println("-------3.根据书名关键字查询图书-------");
		System.out.println("-------0.返回上一级-------");
		choose = scanner.next();
		switch (choose) {
		case "1":
			// 查询所有图书
			showAllBooks();
			break;
		case "2":// 根据书名查询图书
			showNameBooks();
			break;
		case "3":// 根据书名关键字查询图书
			showOnenameBooks();
			break;
		case "0":
			new UserViewBook().userviewbook(user);
			break;
		default:
			break;
		}
	}

	// 查询所有图书
	public void showAllBooks() throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		if (listShow.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (ShowInfo showInfo : listShow) {
				System.out.println(showInfo);
			}
		}
	}

	// 根据书名查询图书
	private void showNameBooks() throws Exception {
		System.out.println("请输入需要查找的书名");
		name = scanner.next();
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listName=UBbiz.findNameBooks(name, listShow);
		if (listName.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (ShowInfo showInfo : listName) {
				System.out.println(showInfo);
			}
		}
	}

	// 根据书名关键字查询图书
	private void showOnenameBooks() throws Exception {
		System.out.println("请输入需要查找的书名");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findOnename(name);
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findOneNameBooks(name);
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		if (listShow.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (ShowInfo showInfo : listShow) {
				System.out.println(showInfo);
			}
		}
	}

	// 查询记录
	public void showborrow(int id) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord=UBbiz.showAllrecord(UBbiz.showborrow(id), listShow);
		if(listrecord.isEmpty()) {
			System.out.println("无记录");
		}else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
		}
	}

	// 预约查询
	private void showorder(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Book> list = UBbiz.findorder(user.getId());
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> showorder=UBbiz.showorder(list, list1);
		if (showorder.isEmpty()) {
			System.out.println("无记录");
		} else {
			for (ShowInfo showInfo : showorder) {
				System.out.println(showInfo.toShowOrder());
			}
		}
	}

	// 查询积分
	public void showPoint(String username) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.showPoint(username);
		if (point == -1) {
			System.out.println("查询失败");
		} else {
			System.out.println("您的积分是：" + point);
		}
	}

	// 查看评价
	public void showcomment() throws Exception {
		UBbiz = new UserBookBizImpl();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		List<ShowInfo> list1 = UBbiz.showComments(list);
		if (list1.isEmpty()) {
			System.out.println("无评价");
		} else {
			for (ShowInfo books : list1) {
				System.out.println(books.toShowCom());
			}
		}
	}

	// 充值
	public void money(int uid) throws Exception {
		System.out.println("输入需要充值的金额");
		int money = util.inputIsNum();
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.money(uid, money);
		System.out.println("充值成功");
		System.out.println("当前积分" + point);
	}
}
