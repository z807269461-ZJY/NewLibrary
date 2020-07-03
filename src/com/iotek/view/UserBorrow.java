package com.iotek.view;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Scanner;

import com.iotek.biz.BooksBiz;
import com.iotek.biz.UserBiz;
import com.iotek.biz.UserBookBiz;
import com.iotek.biz.impl.BooksBizImpl;
import com.iotek.biz.impl.UserBizImpl;
import com.iotek.biz.impl.UserBookBizImpl;
import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Records;
import com.iotek.entity.ShowInfo;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class UserBorrow {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	private String bname;
	private int biid;
	private int id;
	private UserBiz ub;
	private UserBookBiz UBbiz;
	private BooksBiz booksBiz;
	private Utils util;

	public void userborrow(Users user) throws Exception {
		while (true) {
			System.out.println("--------����������-------");
			System.out.println("-------1.����-------");
			System.out.println("-------2.����-------");
			System.out.println("-------3.ԤԼ-------");
			System.out.println("-------4.����-------");
			System.out.println("-------5.����ϲ��-------");
			System.out.println("-------0.������һ��-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// ����
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						new UserViewBook().showAllBooks();
						borrowBooks(user);
						break;
					}
					System.out.println("�����ڵ���û�������Ȼ���");
					break;
				}
				System.out.println("�˺��Ѿ����ᣬ�޷����飬����ϵ����Ա");
				break;
			case "2":
				// ����
				returnBooks(user);
				break;
			case "3":
				// ԤԼ
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						orderBooks(user);
						break;
					}
					System.out.println("�����ڵ���û�������Ȼ���");
					break;
				}
				System.out.println("�˺��Ѿ����ᣬ����ϵ����Ա");
				break;
			case "4":
				// ����
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						renewBooks(user);
						break;
					}
					System.out.println("�����ڵ���û�������Ȼ���");
					break;
				}
				System.out.println("�˺��Ѿ����ᣬ����ϵ����Ա");
				break;
			case "5":
				Guess(user);
				break;
			case "0":
				new UserViewBook().userviewbook(user);
				break;
			default:
				System.out.println("��������������������......");
				continue;
			}
		}
	}

	// ���鷽��
	public void borrowBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.showPoint(user.getName());
		UBbiz = new UserBookBizImpl();
		booksBiz = new BooksBizImpl();
		boolean flag1 = UBbiz.ifborrow(user.getId(), point);
		if (flag1) {
			System.out.println("��������Ҫ������id");
			biid = util.inputIsNum();
			int inorout = booksBiz.inorout(biid);
			if (inorout == -1) {
				System.out.println("����������");
			} else if (inorout == 0) {
				System.out.println("�鱾�Ѿ�������");
			} else {
				int state = UBbiz.BookState(biid);
				if (state == 0) {
					System.out.println("�鱾�Ѿ����¼ܣ��޷�����");
				} else {
					boolean flag = UBbiz.borrowBooks(biid, user.getId());
					if (flag) {
						System.out.println("����ɹ�");
						UBbiz.booksDiscount(biid);// �������+1
						List<Book> list = UBbiz.findorder(user.getId());
						int bid = UBbiz.findbiidBooks(biid).getId();
						int res = UBbiz.AfterBorrow(list, bid);
						if (res != -1) {
							if (UBbiz.changeOrder(res)) {
								System.out.println("�Ȿ������ԤԼ���飬��ϲ������");
							}
						}
					} else {
						System.out.println("����ʧ��");
					}
				}
			}
		} else {
			System.out.println("��������Ѵ����ޣ��޷�����");
			System.out.println("�Ƿ���Ҫ��ֵ��������飬��Ҫ������Y");
			String choose = scanner.next();
			if (choose.equals("y") || choose.equals("Y")) {
				new UserViewBook().money(user.getId());
				borrowBooks(user);
			}
		}
	}

	// ����
	public void returnBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point;
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow = UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord = UBbiz.showAllrecord(UBbiz.showborrow(user.getId()), listShow);
		if (listrecord.isEmpty()) {
			System.out.println("�޼�¼");
		} else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
			System.out.println("��������Ҫ�������id");
			id = util.inputIsNum();
			UBbiz = new UserBookBizImpl();
			boolean flag = UBbiz.ifReturnBook(id, user.getId());
			if (flag) {
				int day = UBbiz.returnBooks(id, user.getId());
				if (day == -2) {
					System.out.println("�����Ѿ��黹");
				} else if (day == -1) {
					System.out.println("�黹ʧ��");
				} else {
					if (day > 30) {
						System.out.println(day + "��" + "�������ڣ��۳�30����");
						point = UBbiz.money(user.getId(), -30);
						System.out.println("��ǰ����");
					} else {
						System.out.println(day + "��" + "�������飬��30����");
						point = UBbiz.money(user.getId(), 30);
					}
					System.out.println("���ֽ���ɹ�");
					System.out.println("��ǰ����" + point);
					System.out.println("����ɹ�");
					System.out.println("�Ƿ�Ҫд���ۣ���Ҫ������y");
					choose = scanner.next();
					if (choose.equals("y") || choose.equals("Y")) {
						System.out.println("����������");
						String str = scanner.next();
						flag = UBbiz.writeComment(id, str);
						if (flag) {
							System.out.println("���۳ɹ�");
						} else {
							System.out.println("����ʧ��");
						}
					}
				}
			} else {
				System.out.println("ID�������");
			}
		}
	}

	// ԤԼ
	public void orderBooks(Users user) throws Exception {
		booksBiz = new BooksBizImpl();
		UBbiz = new UserBookBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		ArrayList<Integer> BooksID = new ArrayList<Integer>();
		if (list.isEmpty()) {
			System.out.println("��ͼ��");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
				BooksID.add(books.getId());
			}
		}
		System.out.println("��������ҪԤԼ����ID");
		int bid = scanner.nextInt();
		if (BooksID.contains(bid)) {
			List<Book> list1 = UBbiz.findorder(user.getId());
			ArrayList<Integer> orderID = new ArrayList<Integer>();
			for (Book book : list1) {
				if (book.getState() == 1) {
					orderID.add(book.getBid());
				}
			}
			if (orderID.contains(bid)) {
				System.out.println("���Ѿ�ԤԼ���Ȿ���ˣ��޷��ظ�ԤԼ");
			} else {
				boolean flag = UBbiz.beforeOrder(user.getId(), bid);
				if (flag) {
					int order = UBbiz.orderBooks(bid, user.getId());
					if (order == -2) {
						System.out.println("�Ȿ���Ѿ��ϼܣ�����ֱ�ӽ���");
					} else if (order == -1) {
						System.out.println("ԤԼʧ��");
					} else {
						System.out.println("ԤԼ�ɹ�");
					}
				} else {
					System.out.println("���Ѿ������Ȿ�飬���ȹ黹");
				}
			}
		} else {
			System.out.println("�鱾������");
		}
	}

	// ����
	public void renewBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord=UBbiz.showAllrecord(UBbiz.showborrow(user.getId()), listShow);
		if(listrecord.isEmpty()) {
			System.out.println("�޼�¼");
		}else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
			System.out.println("��������Ҫ����ļ�¼id");
			id = util.inputIsNum();
			int borrow = UBbiz.renewBooks(user.getId(), id);
			if (borrow != -1 && borrow != -2) {
				System.out.println("����ɹ�");
			} else if (borrow == -2) {
				System.out.println("�Ȿ���Ѿ����ˣ���������");
				System.out.println("����ʧ��");
			} else {
				System.out.println("����ʧ��");
			}
		}


	}

	// ����ϲ��
	public void Guess(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Records> list = UBbiz.showborrow(user.getId());
		if (list.isEmpty()) {
			System.out.println("����δ���ͼ�飬�����������鼮��");
			booksBiz = new BooksBizImpl();
			List<Books> list1 = booksBiz.findAllBooks();
			Collections.sort(list1, new Comparator<Books>() {
				@Override
				public int compare(Books o1, Books o2) {
					// TODO Auto-generated method stub
					return o2.getDiscount() - o1.getDiscount();
				}
			});
			for (Books books : list1) {
				System.out.println(books);
			}
		} else {
			String Type = UBbiz.GuessTYPE(list);
			System.out.println("����ϲ����������" + Type);
			System.out.println("�����Ƽ����Ƶ��飺");
			booksBiz = new BooksBizImpl();
			List<Books> list2 = booksBiz.findTypeBooks(Type);
			for (Books books : list2) {
				System.out.println(books.toString());
			}
			String Author = UBbiz.GuessAuthor(list);
			System.out.println("����ϲ����������" + Author);
			System.out.println("�����Ƽ������飺");
			List<Books> list3 = booksBiz.findAuthorBooks(Author);
			for (Books books : list3) {
				System.out.println(books.toString());
			}
		}
	}

}
