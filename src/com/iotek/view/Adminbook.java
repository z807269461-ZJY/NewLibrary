package com.iotek.view;

import java.util.List;
import java.util.Scanner;

import com.iotek.biz.BooksBiz;
import com.iotek.biz.impl.BooksBizImpl;
import com.iotek.entity.Books;
import com.iotek.util.Utils;

public class Adminbook {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	private String name;
	private int count;
	private int id;
	private String type;
	private String author;
	private BooksBiz booksBiz;
	private Utils util;

	public void adminbook() throws Exception {
		while (true) {
			System.out.println("--------图书管理主界面-------");
			System.out.println("-------1.添加图书-------");
			System.out.println("-------2.修改图书-------");
			System.out.println("-------3.查询图书-------");
			System.out.println("-------4.上架图书-------");
			System.out.println("-------5.下架图书-------");
			System.out.println("-------0.返回上一级-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// 添加图书
				addBooks();
				break;
			case "2":
				// 修改图书
				reviseBooks();
				break;
			case "3":
				// 查询图书
				findBooks();
				break;
			case "4":
				// 上架图书
				onBooks();
				break;
			case "5":
				// 下架图书
				downBooks();
				break;
			case "0":
				new AdminView().adminView();
				break;

			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
	}

	// 添加图书的方法
	public void addBooks() throws Exception {
				System.out.println("请输入书名");
				name = scanner.next();
				booksBiz = new BooksBizImpl();
				if(booksBiz.ifbook(name)) {
					System.out.println("请输入添加的数量");
					count = util.inputIsNum();
					System.out.println("请输入类型");
					type = scanner.next();
					System.out.println("请输入作者");
					author = scanner.next();
					Books books = new Books(name, count, type, author);
					boolean flag = booksBiz.addBooks(books);
					if (flag) {
						System.out.println("添加成功");
					} else {
						System.out.println("添加失败");
					}
				}else {
					System.out.println("书名已经存在");
				}
		}
	// 修改图书
	public void reviseBooks() throws Exception {
				System.out.println("请输入需要修改的书名");
				name = scanner.next();
				System.out.println("请输入修改的剩余数量");
				count = util.inputIsNum();
				booksBiz = new BooksBizImpl();
				boolean flag = booksBiz.reviseBooks(name, count);
				if (flag) {
					System.out.println("修改成功");
				} else {
					System.out.println("修改失败");
				}
	}

	// 查询图书的菜单
	public void findBooks() throws Exception {
		System.out.println("-------查询菜单-------");
		System.out.println("-------1.查询所有图书-------");
		System.out.println("-------2.根据书名查询图书-------");
		System.out.println("-------3.根据作者查询图书-------");
		System.out.println("-------4.根据类型查询图书-------");
		System.out.println("-------5.根据书名关键字查询图书-------");
		System.out.println("-------6.查看上架书籍-------");
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
		case "3":// 根据作者查询图书
			showAuthorBooks();
			break;
		case "4":// 根据类型查询图书
			showTypeBooks();
			break;
		case "5":// 根据书名关键字查询图书
			showOnenameBooks();
			break;
		case "6":// 查询所有上架书
			new UserViewBook().showAllBooks();
			;
			break;
		case "0":
			new Adminbook().adminbook();
			break;
		default:
			break;
		}
	}

	// 查询所有图书
	public void showAllBooks() throws Exception {
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// 根据书名查询图书
	public void showNameBooks() throws Exception {
		System.out.println("请输入需要查找的书名");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findNameBooks(name);
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// 根据作者查询图书
	public void showAuthorBooks() throws Exception {
		System.out.println("请输入需要查找的作者名");
		author = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAuthorBooks(author);
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// 根据类型查询图书
	public void showTypeBooks() throws Exception {
		System.out.println("请输入需要查找的类型名");
		type = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findTypeBooks(type);
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// 根据书名关键字查询图书
	public void showOnenameBooks() throws Exception {
		System.out.println("请输入书名关键字");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findOnename(name);
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// 上架图书
	public void onBooks() throws Exception {
		System.out.println("请输入需要上架的书名");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		boolean flag = booksBiz.onBooks(name);
		if (flag) {
			System.out.println("上架成功");
		} else {
			System.out.println("无图书,上架失败");
		}
	}

	// 下架图书
	public void downBooks() throws Exception {
		new UserViewBook().showAllBooks();
		System.out.println("请输入需要下架的书id");
		id = util.inputIsNum();
		booksBiz = new BooksBizImpl();
		int inorout =booksBiz.inorout(id);
		if(inorout==-1) {
			System.out.println("编号输入错误");
		}else if(inorout==0) {
			System.out.println("书本已被借走，无法下架");
		}else {
			boolean flag = booksBiz.downBooks(id);
			if (flag) {
				System.out.println("下架成功");
			} else {
				System.out.println("下架失败");
			}
		}
	}
}
