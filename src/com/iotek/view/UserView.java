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
			System.out.println("------�û����˵�------");
			System.out.println("------1.ע��------");
			System.out.println("------2.��½------");
			System.out.println("------0.������һ��------");
			choice = scanner.next();
			switch (choice) {
			case "1"://ע��
				register();
				break;
			case "2":
				loginMenu();
				break;
			case "0":
				new MainView().mainView();
			default:
				System.out.println("��������������������......");
				continue;
			}
		}
	}
	// ע�᷽��
		public void register() {
			while (true) {
				System.out.println("�˺�");
				String name = scanner.next();
				boolean flag = false;
				try {
					flag = ub.exist(name);
					if (flag) {
						System.out.println("����");
						String password = scanner.next();
						password=util.getMD5(password);
						Users user = new Users(name, password);
						// view����������������ҵ���߼����æ
						if (ub.register(user)) {
							System.out.println("ע��ɹ�");
						} else {
							System.out.println("ע��ʧ��");
						}
						if (!Utils.isGoOn("�Ƿ�Ҫע��,��Ҫ������y,����Ҫ������n")) {
							break;
						}
					} else {
						System.out.println("�û����Ѵ���,����������");
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		
		// ��¼����
		public void loginMenu() throws Exception {
			while (true) {
				System.out.println("�˺�");
				String name = scanner.next();
				System.out.println("����");
				String password = scanner.next();
				password=util.getMD5(password);
				Users user = new Users(name, password);
				if (ub.login(user)) {
					Users user1=ub.findUserByName(name);
					if(!ub.ifFrozen(user1.getId())) {
						System.out.println("�˺ű����ᣬ�޷����顢ԤԼ�����裬����ϵ����Ա");
						System.out.println("============");
					}
					if(!ub.ifovertime(user1.getId())) {
						System.out.println("������δ�����鱾���������ܽ��н���Ȳ���");
						System.out.println("============");
					}
					UBbiz = new UserBookBizImpl();
					List<Book> list1 = UBbiz.findorder(user1.getId());
					ArrayList<Integer> orderID=new ArrayList<Integer>();
					for (Book book : list1) {
						if(book.getState()==1) {//�ռ�ԤԼ���鱾��ID
							orderID.add(book.getBid());
						}
					}
					ArrayList<Integer> orderNewId=ub.loginifborrow(orderID);
					if(!orderNewId.isEmpty()) {
						for (Integer bid : orderNewId) {
							System.out.println("��ԤԼ���鱾�Ѿ��ϼܣ��鱾��Ϣ���£�");
							System.out.println(UBbiz.findbidBooks(bid));
						}
					}
					System.out.println("��¼�ɹ�");
					new UserViewBook().userviewbook(user1);
				} else {
					System.out.println("��¼ʧ��");
				}
				if (!Utils.isGoOn("�Ƿ�Ҫ��¼")) {
					break;
				}
			}
		}
}
