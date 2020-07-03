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
			System.out.println("--------ͼ�����������-------");
			System.out.println("-------1.���ͼ��-------");
			System.out.println("-------2.�޸�ͼ��-------");
			System.out.println("-------3.��ѯͼ��-------");
			System.out.println("-------4.�ϼ�ͼ��-------");
			System.out.println("-------5.�¼�ͼ��-------");
			System.out.println("-------0.������һ��-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// ���ͼ��
				addBooks();
				break;
			case "2":
				// �޸�ͼ��
				reviseBooks();
				break;
			case "3":
				// ��ѯͼ��
				findBooks();
				break;
			case "4":
				// �ϼ�ͼ��
				onBooks();
				break;
			case "5":
				// �¼�ͼ��
				downBooks();
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

	// ���ͼ��ķ���
	public void addBooks() throws Exception {
				System.out.println("����������");
				name = scanner.next();
				booksBiz = new BooksBizImpl();
				if(booksBiz.ifbook(name)) {
					System.out.println("��������ӵ�����");
					count = util.inputIsNum();
					System.out.println("����������");
					type = scanner.next();
					System.out.println("����������");
					author = scanner.next();
					Books books = new Books(name, count, type, author);
					boolean flag = booksBiz.addBooks(books);
					if (flag) {
						System.out.println("��ӳɹ�");
					} else {
						System.out.println("���ʧ��");
					}
				}else {
					System.out.println("�����Ѿ�����");
				}
		}
	// �޸�ͼ��
	public void reviseBooks() throws Exception {
				System.out.println("��������Ҫ�޸ĵ�����");
				name = scanner.next();
				System.out.println("�������޸ĵ�ʣ������");
				count = util.inputIsNum();
				booksBiz = new BooksBizImpl();
				boolean flag = booksBiz.reviseBooks(name, count);
				if (flag) {
					System.out.println("�޸ĳɹ�");
				} else {
					System.out.println("�޸�ʧ��");
				}
	}

	// ��ѯͼ��Ĳ˵�
	public void findBooks() throws Exception {
		System.out.println("-------��ѯ�˵�-------");
		System.out.println("-------1.��ѯ����ͼ��-------");
		System.out.println("-------2.����������ѯͼ��-------");
		System.out.println("-------3.�������߲�ѯͼ��-------");
		System.out.println("-------4.�������Ͳ�ѯͼ��-------");
		System.out.println("-------5.���������ؼ��ֲ�ѯͼ��-------");
		System.out.println("-------6.�鿴�ϼ��鼮-------");
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
		case "3":// �������߲�ѯͼ��
			showAuthorBooks();
			break;
		case "4":// �������Ͳ�ѯͼ��
			showTypeBooks();
			break;
		case "5":// ���������ؼ��ֲ�ѯͼ��
			showOnenameBooks();
			break;
		case "6":// ��ѯ�����ϼ���
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

	// ��ѯ����ͼ��
	public void showAllBooks() throws Exception {
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// ����������ѯͼ��
	public void showNameBooks() throws Exception {
		System.out.println("��������Ҫ���ҵ�����");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findNameBooks(name);
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// �������߲�ѯͼ��
	public void showAuthorBooks() throws Exception {
		System.out.println("��������Ҫ���ҵ�������");
		author = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findAuthorBooks(author);
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// �������Ͳ�ѯͼ��
	public void showTypeBooks() throws Exception {
		System.out.println("��������Ҫ���ҵ�������");
		type = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findTypeBooks(type);
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// ���������ؼ��ֲ�ѯͼ��
	public void showOnenameBooks() throws Exception {
		System.out.println("�����������ؼ���");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		List<Books> list = booksBiz.findOnename(name);
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
			}
		}
	}

	// �ϼ�ͼ��
	public void onBooks() throws Exception {
		System.out.println("��������Ҫ�ϼܵ�����");
		name = scanner.next();
		booksBiz = new BooksBizImpl();
		boolean flag = booksBiz.onBooks(name);
		if (flag) {
			System.out.println("�ϼܳɹ�");
		} else {
			System.out.println("��ͼ��,�ϼ�ʧ��");
		}
	}

	// �¼�ͼ��
	public void downBooks() throws Exception {
		new UserViewBook().showAllBooks();
		System.out.println("��������Ҫ�¼ܵ���id");
		id = util.inputIsNum();
		booksBiz = new BooksBizImpl();
		int inorout =booksBiz.inorout(id);
		if(inorout==-1) {
			System.out.println("����������");
		}else if(inorout==0) {
			System.out.println("�鱾�ѱ����ߣ��޷��¼�");
		}else {
			boolean flag = booksBiz.downBooks(id);
			if (flag) {
				System.out.println("�¼ܳɹ�");
			} else {
				System.out.println("�¼�ʧ��");
			}
		}
	}
}
