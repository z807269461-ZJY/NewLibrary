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
			System.out.println("--------�û�������-------");
			System.out.println("-------1.��ѯͼ��-------");
			System.out.println("-------2.�軹ͼ��-------");
			System.out.println("-------3.��ѯ��¼-------");
			System.out.println("-------4.ԤԼ��ѯ-------");
			System.out.println("-------5.��ѯ����-------");
			System.out.println("-------6.�鿴����-------");
			System.out.println("-------7.��ֵ-------");
			System.out.println("-------0.������һ��-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// ��ѯͼ��
				findBooks(user);
				break;
			case "2":
				// �軹ͼ��
				new UserBorrow().userborrow(user);
				break;
			case "3":
				// ��ѯ��¼
				showborrow(user.getId());
				break;
			case "4":
				// ԤԼ��ѯ
				showorder(user);
				break;
			case "5":
				// ��ѯ����
				showPoint(user.getName());
				break;
			case "6":
				// �鿴����
				showcomment();
				break;
			case "7":
				// ��ֵ
				money(user.getId());
				break;
			case "0":
				new UserView().userView();
				break;

			default:
				System.out.println("��������������������......");
				continue;
			}
		}
	}

	// ��ѯͼ�����
	public void findBooks(Users user) throws Exception {
		System.out.println("-------��ѯ�˵�-------");
		System.out.println("-------1.��ѯ����ͼ��-------");
		System.out.println("-------2.����������ѯͼ��-------");
		System.out.println("-------3.���������ؼ��ֲ�ѯͼ��-------");
		System.out.println("-------0.������һ��-------");
		choose = scanner.next();
		switch (choose) {
		case "1":
			// ��ѯ����ͼ��
			showAllBooks();
			break;
		case "2":// ����������ѯͼ��
			showNameBooks();
			break;
		case "3":// ���������ؼ��ֲ�ѯͼ��
			showOnenameBooks();
			break;
		case "0":
			new UserViewBook().userviewbook(user);
			break;
		default:
			break;
		}
	}

	// ��ѯ����ͼ��
	public void showAllBooks() throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		if (listShow.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (ShowInfo showInfo : listShow) {
				System.out.println(showInfo);
			}
		}
	}

	// ����������ѯͼ��
	private void showNameBooks() throws Exception {
		System.out.println("��������Ҫ���ҵ�����");
		name = scanner.next();
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listName=UBbiz.findNameBooks(name, listShow);
		if (listName.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (ShowInfo showInfo : listName) {
				System.out.println(showInfo);
			}
		}
	}

	// ���������ؼ��ֲ�ѯͼ��
	private void showOnenameBooks() throws Exception {
		System.out.println("��������Ҫ���ҵ�����");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findOnename(name);
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findOneNameBooks(name);
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		if (listShow.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (ShowInfo showInfo : listShow) {
				System.out.println(showInfo);
			}
		}
	}

	// ��ѯ��¼
	public void showborrow(int id) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord=UBbiz.showAllrecord(UBbiz.showborrow(id), listShow);
		if(listrecord.isEmpty()) {
			System.out.println("�޼�¼");
		}else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
		}
	}

	// ԤԼ��ѯ
	private void showorder(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Book> list = UBbiz.findorder(user.getId());
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> showorder=UBbiz.showorder(list, list1);
		if (showorder.isEmpty()) {
			System.out.println("�޼�¼");
		} else {
			for (ShowInfo showInfo : showorder) {
				System.out.println(showInfo.toShowOrder());
			}
		}
	}

	// ��ѯ����
	public void showPoint(String username) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.showPoint(username);
		if (point == -1) {
			System.out.println("��ѯʧ��");
		} else {
			System.out.println("���Ļ����ǣ�" + point);
		}
	}

	// �鿴����
	public void showcomment() throws Exception {
		UBbiz = new UserBookBizImpl();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		List<ShowInfo> list1 = UBbiz.showComments(list);
		if (list1.isEmpty()) {
			System.out.println("������");
		} else {
			for (ShowInfo books : list1) {
				System.out.println(books.toShowCom());
			}
		}
	}

	// ��ֵ
	public void money(int uid) throws Exception {
		System.out.println("������Ҫ��ֵ�Ľ��");
		int money = util.inputIsNum();
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.money(uid, money);
		System.out.println("��ֵ�ɹ�");
		System.out.println("��ǰ����" + point);
	}
}
