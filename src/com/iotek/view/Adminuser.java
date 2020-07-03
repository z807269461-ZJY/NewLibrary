package com.iotek.view;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.iotek.biz.AdminUserBiz;
import com.iotek.biz.UserBiz;
import com.iotek.biz.impl.AdminUserBizImpl;
import com.iotek.biz.impl.UserBizImpl;
import com.iotek.entity.Books;
import com.iotek.entity.Frozentime;
import com.iotek.entity.Users;
import com.iotek.util.Utils;

public class Adminuser {
	private Scanner scanner = new Scanner(System.in);
	private String choose;
	private String name;
	private String password;
	private AdminUserBiz AdUBiz;
	private int point;
	private String level;
	private Utils util;
	private UserBiz ub;

	public void adminuser() throws Exception {
		while (true) {
			System.out.println("--------用户管理界面-------");
			System.out.println("-------1.注册用户-------");
			System.out.println("-------2.修改用户积分-------");
			System.out.println("-------3.查看所有用户-------");
			System.out.println("-------4.查看冻结表-------");
			System.out.println("-------5.解冻用户-------");
			System.out.println("-------6.冻结用户-------");
			System.out.println("-------0.返回上一级-------");
			choose = scanner.next();
			switch (choose) {
			case "1":// 注册用户
				new UserView().register();
				break;
			case "2":// 修改用户积分
				showAllUsers();
				reUserPoint();
				break;
			case "3":// 查看所有用户
				showAllUsers();
				break;
			case "4":// 查看冻结表
				showFrozen();
				break;
			case "5":// 解冻用户
				changeFrozen();
				break;
			case "6":// 冻结用户
				showAllUsers();
				Frozen();
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
	//修改用户积分
	public void reUserPoint() throws Exception{
		System.out.println("请输入需要修改的用户ID");
		int uid=util.inputIsNum();
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		ArrayList<Integer> idlist=new ArrayList<Integer>();
		for (Users users : list) {
			idlist.add(users.getId());
		}
		if(idlist.contains(uid)) {
			System.out.println("请输入新积分");
			point=util.inputIsNum();
			AdUBiz=new AdminUserBizImpl();
			boolean flag=AdUBiz.reUserPoint(uid, point);
			if (flag) {
				System.out.println("修改成功");
			} else {
				System.out.println("修改失败");
			}
		}else {
			System.out.println("账号不存在");
		}
		
	}
	//修改用户等级
	public void reUserlevel() throws Exception{
		System.out.println("请输入需要修改的用户名");
		name=scanner.next();
		System.out.println("请输入新等级");
		level=scanner.next();
		AdUBiz=new AdminUserBizImpl();
		boolean flag=AdUBiz.reUserlevel(name, level);
		if (flag) {
			System.out.println("修改成功");
		} else {
			System.out.println("修改失败");
		}
	}
	//查看所有用户
	public void showAllUsers() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		if (list.isEmpty()) {
			System.out.println("无用户");
		} else {
			for (Users users : list) {
				System.out.println(users.toString());
			}
		}
	}
	//查看冻结表
	public void showFrozen() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Frozentime> list = AdUBiz.showFrozen();
		if (list.isEmpty()) {
			System.out.println("无用户冻结");
		} else {
			for (Frozentime users : list) {
				System.out.println(users.toString());
			}
		}
	}
	//解冻用户
	public void changeFrozen() throws Exception{
		AdUBiz=new AdminUserBizImpl();
		List<Frozentime> list = AdUBiz.showFrozen();
		if (list.isEmpty()) {
			System.out.println("无用户冻结");
		} else {
			for (Frozentime users : list) {
				System.out.println(users.toString());
			}
			System.out.println("输入需要解冻的id");
			int id=util.inputIsNum();
			int res=AdUBiz.changefrozen(id);
			if (res>0) {
				System.out.println("解冻成功");
			} else if(res==-1){
				System.out.println("此账户以及解冻");
			}else {
				System.out.println("选择错误，冻结失败");
			}
		}
	}
	//冻结用户
	public void Frozen() throws Exception{
		System.out.println("请输入需要冻结的账户ID");
		int uid =util.inputIsNum();
		AdUBiz=new AdminUserBizImpl();
		List<Users> list = AdUBiz.showAllUsers();
		ArrayList<Integer> idlist=new ArrayList<Integer>();
		for (Users users : list) {
			idlist.add(users.getId());
		}
		if(idlist.contains(uid)) {
			ub=new UserBizImpl();
			if(ub.ifFrozen(uid)) {
				if(AdUBiz.frozenUser(uid)) {
					System.out.println("冻结成功");
				}else {
					System.out.println("冻结失败");
				}
			}else {
				System.out.println("账户已被冻结,无法继续冻结");
			}
		}else {
			System.out.println("账号不存在");
		}
	}
}
