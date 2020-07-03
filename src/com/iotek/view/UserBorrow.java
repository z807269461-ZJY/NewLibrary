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
			System.out.println("--------借书主界面-------");
			System.out.println("-------1.借书-------");
			System.out.println("-------2.还书-------");
			System.out.println("-------3.预约-------");
			System.out.println("-------4.续借-------");
			System.out.println("-------5.猜你喜欢-------");
			System.out.println("-------0.返回上一级-------");
			choose = scanner.next();
			switch (choose) {
			case "1":
				// 借书
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						new UserViewBook().showAllBooks();
						borrowBooks(user);
						break;
					}
					System.out.println("有逾期的书没还，请先还书");
					break;
				}
				System.out.println("账号已经冻结，无法借书，请联系管理员");
				break;
			case "2":
				// 还书
				returnBooks(user);
				break;
			case "3":
				// 预约
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						orderBooks(user);
						break;
					}
					System.out.println("有逾期的书没还，请先还书");
					break;
				}
				System.out.println("账号已经冻结，请联系管理员");
				break;
			case "4":
				// 续借
				ub = new UserBizImpl();
				if (ub.ifFrozen(user.getId())) {
					if (ub.ifovertime(user.getId())) {
						renewBooks(user);
						break;
					}
					System.out.println("有逾期的书没还，请先还书");
					break;
				}
				System.out.println("账号已经冻结，请联系管理员");
				break;
			case "5":
				Guess(user);
				break;
			case "0":
				new UserViewBook().userviewbook(user);
				break;
			default:
				System.out.println("你输入有误，请重新输入......");
				continue;
			}
		}
	}

	// 借书方法
	public void borrowBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point = UBbiz.showPoint(user.getName());
		UBbiz = new UserBookBizImpl();
		booksBiz = new BooksBizImpl();
		boolean flag1 = UBbiz.ifborrow(user.getId(), point);
		if (flag1) {
			System.out.println("请输入需要借的书的id");
			biid = util.inputIsNum();
			int inorout = booksBiz.inorout(biid);
			if (inorout == -1) {
				System.out.println("编号输入错误");
			} else if (inorout == 0) {
				System.out.println("书本已经被借走");
			} else {
				int state = UBbiz.BookState(biid);
				if (state == 0) {
					System.out.println("书本已经被下架，无法借走");
				} else {
					boolean flag = UBbiz.borrowBooks(biid, user.getId());
					if (flag) {
						System.out.println("借书成功");
						UBbiz.booksDiscount(biid);// 被借次数+1
						List<Book> list = UBbiz.findorder(user.getId());
						int bid = UBbiz.findbiidBooks(biid).getId();
						int res = UBbiz.AfterBorrow(list, bid);
						if (res != -1) {
							if (UBbiz.changeOrder(res)) {
								System.out.println("这本书是您预约的书，恭喜您借走");
							}
						}
					} else {
						System.out.println("借书失败");
					}
				}
			}
		} else {
			System.out.println("借书次数已达上限，无法借书");
			System.out.println("是否需要充值后继续借书，需要请输入Y");
			String choose = scanner.next();
			if (choose.equals("y") || choose.equals("Y")) {
				new UserViewBook().money(user.getId());
				borrowBooks(user);
			}
		}
	}

	// 还书
	public void returnBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		int point;
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow = UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord = UBbiz.showAllrecord(UBbiz.showborrow(user.getId()), listShow);
		if (listrecord.isEmpty()) {
			System.out.println("无记录");
		} else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
			System.out.println("请输入需要还的书的id");
			id = util.inputIsNum();
			UBbiz = new UserBookBizImpl();
			boolean flag = UBbiz.ifReturnBook(id, user.getId());
			if (flag) {
				int day = UBbiz.returnBooks(id, user.getId());
				if (day == -2) {
					System.out.println("此书已经归还");
				} else if (day == -1) {
					System.out.println("归还失败");
				} else {
					if (day > 30) {
						System.out.println(day + "天" + "还书逾期，扣除30积分");
						point = UBbiz.money(user.getId(), -30);
						System.out.println("当前积分");
					} else {
						System.out.println(day + "天" + "正常还书，加30积分");
						point = UBbiz.money(user.getId(), 30);
					}
					System.out.println("积分结算成功");
					System.out.println("当前积分" + point);
					System.out.println("还书成功");
					System.out.println("是否要写评论，需要请输入y");
					choose = scanner.next();
					if (choose.equals("y") || choose.equals("Y")) {
						System.out.println("请输入评价");
						String str = scanner.next();
						flag = UBbiz.writeComment(id, str);
						if (flag) {
							System.out.println("评价成功");
						} else {
							System.out.println("评价失败");
						}
					}
				}
			} else {
				System.out.println("ID输入错误");
			}
		}
	}

	// 预约
	public void orderBooks(Users user) throws Exception {
		booksBiz = new BooksBizImpl();
		UBbiz = new UserBookBizImpl();
		List<Books> list = booksBiz.findAllBooks();
		ArrayList<Integer> BooksID = new ArrayList<Integer>();
		if (list.isEmpty()) {
			System.out.println("无图书");
		} else {
			for (Books books : list) {
				System.out.println(books.toString());
				BooksID.add(books.getId());
			}
		}
		System.out.println("请输入需要预约的书ID");
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
				System.out.println("你已经预约过这本书了，无法重复预约");
			} else {
				boolean flag = UBbiz.beforeOrder(user.getId(), bid);
				if (flag) {
					int order = UBbiz.orderBooks(bid, user.getId());
					if (order == -2) {
						System.out.println("这本书已经上架，可以直接借书");
					} else if (order == -1) {
						System.out.println("预约失败");
					} else {
						System.out.println("预约成功");
					}
				} else {
					System.out.println("你已经借了这本书，请先归还");
				}
			}
		} else {
			System.out.println("书本不存在");
		}
	}

	// 续借
	public void renewBooks(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Bookinfo> list = UBbiz.findAllBooks();
		booksBiz = new BooksBizImpl();
		List<Books> list1 = booksBiz.findAllBooks();
		List<ShowInfo> listShow=UBbiz.showAllBooks(list, list1);
		List<ShowInfo> listrecord=UBbiz.showAllrecord(UBbiz.showborrow(user.getId()), listShow);
		if(listrecord.isEmpty()) {
			System.out.println("无记录");
		}else {
			for (ShowInfo showInfo : listrecord) {
				System.out.println(showInfo.toShowBorrow());
			}
			System.out.println("请输入需要续借的记录id");
			id = util.inputIsNum();
			int borrow = UBbiz.renewBooks(user.getId(), id);
			if (borrow != -1 && borrow != -2) {
				System.out.println("续借成功");
			} else if (borrow == -2) {
				System.out.println("这本书已经还了，不能续借");
				System.out.println("续借失败");
			} else {
				System.out.println("续借失败");
			}
		}


	}

	// 猜你喜欢
	public void Guess(Users user) throws Exception {
		UBbiz = new UserBookBizImpl();
		List<Records> list = UBbiz.showborrow(user.getId());
		if (list.isEmpty()) {
			System.out.println("您还未借过图书，以下是热门书籍：");
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
			System.out.println("你最喜欢的类型是" + Type);
			System.out.println("向你推荐类似的书：");
			booksBiz = new BooksBizImpl();
			List<Books> list2 = booksBiz.findTypeBooks(Type);
			for (Books books : list2) {
				System.out.println(books.toString());
			}
			String Author = UBbiz.GuessAuthor(list);
			System.out.println("你最喜欢的作者是" + Author);
			System.out.println("向你推荐他的书：");
			List<Books> list3 = booksBiz.findAuthorBooks(Author);
			for (Books books : list3) {
				System.out.println(books.toString());
			}
		}
	}

}
