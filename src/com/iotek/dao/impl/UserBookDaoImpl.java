package com.iotek.dao.impl;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.iotek.biz.impl.UserBizImpl;
import com.iotek.dao.BooksDao;
import com.iotek.dao.UserBookDao;
import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;

public class UserBookDaoImpl extends BaseDao implements UserBookDao {
//查询所有书
	@Override
	public List<Bookinfo> findAllBooks() throws Exception {
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		connection = getConnection();
		sql = "select * from bookinfo";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			int bid = rs.getInt("bid");
			int inorout = rs.getInt("inorout");
			int state = rs.getInt("state");
			int lost = rs.getInt("lost");
			Bookinfo books = new Bookinfo(id, bid, inorout, state, lost);
			list.add(books);
		}
		closeAll();
		return list;
	}

//根据书名查
	@Override
	public List<Bookinfo> findNameBooks(String name) throws Exception {
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		connection = getConnection();
		sql = "select id from books where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			sql = "select * from bookinfo where bid=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				int bid = rs.getInt("bid");
				int inorout = rs.getInt("inorout");
				int state = rs.getInt("state");
				int lost = rs.getInt("lost");
				Bookinfo books = new Bookinfo(id, bid, inorout, state, lost);
				list.add(books);
			}
		}
		closeAll();
		return list;
	}

	// 根据关键字查
	@Override
	public List<Bookinfo> findOneNameBooks(String name) throws Exception {
		List<Bookinfo> list = new ArrayList<Bookinfo>();
		connection = getConnection();
		sql = "select * from books where name like '%" + name + "%'";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			sql = "select * from bookinfo where bid=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			rs = ps.executeQuery();
			while (rs.next()) {
				id = rs.getInt("id");
				int bid = rs.getInt("bid");
				int inorout = rs.getInt("inorout");
				int state = rs.getInt("state");
				int lost = rs.getInt("lost");
				Bookinfo books = new Bookinfo(id, bid, inorout, state, lost);
				list.add(books);
			}
		}
		closeAll();
		return list;
	}

	// 借书前判断书是否能接
	public int BookState(int id) throws Exception {
		connection = getConnection();
		sql = "select state from bookinfo where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int state = -1;
		while (rs.next()) {
			state = rs.getInt("state");
		}
		closeAll();
		return state;
	}

	// 借书
	@Override
	public int borrowBooks(int biid, int uid) throws Exception {
		connection = getConnection();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
		String lendTime = df.format(new Date());
		sql = "insert into records(uid,biid,lendTime)values(?,?,?)";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		ps.setInt(2, biid);
		ps.setString(3, lendTime);
		ps.executeUpdate();
		sql = "update bookinfo set inorout=0 where id=?";
		int res = -1;
		ps = connection.prepareStatement(sql);
		ps.setInt(1, biid);
		res = ps.executeUpdate();
		return res;

	}

	// 判断还书编号选择是否正确
	public boolean ifReturnBook(int id, int uid) throws Exception {
		connection = getConnection();
		sql = "select uid from records where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int newuid = 0;
		while (rs.next()) {
			newuid = rs.getInt("uid");
		}
		closeAll();
		if (newuid == uid) {
			return true;
		} else {
			return false;
		}
	}

	// 还书
	@Override
	public int returnBooks(int id, int uid) throws Exception {
		connection = getConnection();
		sql = "select returnTime from records where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int res = -1;
		while (rs.next()) {
			String returnTime1 = rs.getString("returnTime");
			if (returnTime1 == null) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String returnTime = df.format(new Date());
				sql = "select lendTime from records where id=?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					String lendTime = rs.getString("lendTime");
					Date d1 = df.parse(lendTime);
					Date d2 = df.parse(returnTime);
					long diff = d2.getTime() - d1.getTime();// 这样得到的差值是毫秒级别
					long days = diff / (1000 * 60 * 60 * 24);
					res = (int) days;
				}
				sql = "update records set returnTime=? where id=?";
				ps = connection.prepareStatement(sql);
				ps.setString(1, returnTime);
				ps.setInt(2, id);
				ps.executeUpdate();
				sql = "select biid from records where id=?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				rs = ps.executeQuery();
				while (rs.next()) {
					int biid = rs.getInt("biid");
					sql = "update bookinfo set inorout=1 where id=?";
					ps = connection.prepareStatement(sql);
					ps.setInt(1, biid);
					ps.executeUpdate();
				}

			} else
				res = -2;
		}
		closeAll();
		return res;
	}

	// 预约前检查是否已经借了这本书
	public boolean beforeOrder(int uid, int bid) throws Exception {
		connection = getConnection();
		sql = "select returnTime,biid from records where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		rs = ps.executeQuery();
		String returntime;
		boolean flag = true;
		while (rs.next()) {
			returntime = rs.getString("returnTime");
			int biid = rs.getInt("biid");
			if (returntime == null) {
				sql = "select bid from bookinfo where id=?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, biid);
				rs = ps.executeQuery();
				while (rs.next()) {
					int newbid = rs.getInt("bid");
					if (newbid == bid) {
						flag = false;
					}
				}
			}
		}
		return flag;

	}

	// 预约
	@Override
	public int orderBooks(int bid, int id) throws Exception {
		connection = getConnection();
		ArrayList<Integer> list = new ArrayList<Integer>();
		int res = -1;
		sql = "select inorout,state from bookinfo where bid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, bid);
		rs = ps.executeQuery();
		while (rs.next()) {
			int inorout = rs.getInt("inorout");
			int state = rs.getInt("state");
			if (inorout == 1 && state == 1) {
				list.add(inorout);
			}
		}
		if (list.contains(1)) {
			res = -2;
		} else {
			sql = "insert into book (uid,bid) values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, id);
			ps.setInt(2, bid);
			res = ps.executeUpdate();
		}
		closeAll();
		return res;
	}

	// 查找借书记录
	@Override
	public List<Records> showborrow(int id) throws Exception {
		List<Records> list = new ArrayList<Records>();
		connection = getConnection();
		sql = "select * from records where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			id = rs.getInt("id");
			int uid = rs.getInt("uid");
			int biid = rs.getInt("biid");
			String lendTime = rs.getString("lendTime");
			String returnTime = rs.getString("returnTime");
			Records RC = new Records(id, uid, biid, lendTime, returnTime);
			list.add(RC);
		}

		closeAll();
		return list;
	}

	// 根据biid查询
	@Override
	public Books findbiidBooks(int biid) throws Exception {
		Books books = null;
		connection = getConnection();
		sql = "select bid from bookinfo where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, biid);
		rs = ps.executeQuery();
		while (rs.next()) {
			int bid = rs.getInt("bid");
			sql = "select * from books where id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			while (rs.next()) {
				int id = rs.getInt("id");
				String name = rs.getString("name");
				int count = rs.getInt("count");
				String type = rs.getString("type");
				String author = rs.getString("author");
				int discount = rs.getInt("discount");
				
				books = new Books(id, name, count, type, author, discount);
			}
		}
		return books;
	}
	// 查预约记录

	@Override
	public List<Book> findorder(int uid) throws Exception {
		List<Book> list = new ArrayList<Book>();
		connection = getConnection();
		sql = "select * from book where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			uid = rs.getInt("uid");
			int bid = rs.getInt("bid");
			int state = rs.getInt("state");
			Book book = new Book(id, uid, bid, state);
			list.add(book);
		}
		closeAll();
		return list;
	}

	// 根据bid查询
	@Override
	public List<Books> findbidBooks(int bid) throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, bid);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int count = rs.getInt("count");
			String type = rs.getString("type");
			String author = rs.getString("author");
			int discount = rs.getInt("discount");
			Books books = new Books(id, name, count, type, author, discount);
			list.add(books);
		}
		closeAll();
		return list;
	}

	// 查询积分
	@Override
	public int showPoint(String name) throws Exception {
		connection = getConnection();
		sql = "select point from users where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		int point = -1;
		while (rs.next()) {
			point = rs.getInt("point");
		}
		closeAll();
		return point;
	}

	// 续借
	@Override
	public int renewBooks(int uid, int id) throws Exception {
		connection = getConnection();
		int res = -1;
		sql = "select returnTime,uid from records where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		while (rs.next()) {
			String returnTime = rs.getString("returnTime");
			int newuid = rs.getInt("uid");
			if (returnTime == null && newuid == uid) {
				SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");// 设置日期格式
				String lendTime = df.format(new Date());
				sql = "update records set lendTime=? where id=?";
				ps = connection.prepareStatement(sql);
				ps.setString(1, lendTime);
				ps.setInt(2, id);
				res = ps.executeUpdate();
			} else if (newuid != uid) {
				res = -3;
			} else {
				res = -2;
			}
		}
		closeAll();
		return res;
	}

//查评价
	@Override
	public List<Comments> showComments() throws Exception {
		connection = getConnection();
		List<Comments> list = new ArrayList<Comments>();
		sql = "select * from comments ";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			int bid = rs.getInt("bid");
			String comment = rs.getString("comment");
			Comments comments = new Comments(id, bid, comment);
			list.add(comments);
		}
		closeAll();
		return list;
	}

	// 修改等级
	@Override
	public void changelevel(int uid, int point) throws Exception {
		connection = getConnection();
		if (point < 0) {
			sql = "update users set level=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "冻结");
			ps.setInt(2, uid);
			ps.executeUpdate();
		} else if (point >= 0 && point < 50) {
			sql = "update users set level=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "普通用户");
			ps.setInt(2, uid);
			ps.executeUpdate();
		} else if (point >= 50 && point < 100) {
			sql = "update users set level=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "初级会员");
			ps.setInt(2, uid);
			ps.executeUpdate();
		} else if (point >= 100 && point < 150) {
			sql = "update users set level=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "高级会员");
			ps.setInt(2, uid);
			ps.executeUpdate();
		} else {
			sql = "update users set level=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setString(1, "特级会员");
			ps.setInt(2, uid);
			ps.executeUpdate();
		}
	}

//写评价
	@Override
	public int writeComment(int id, String str) throws Exception {
		connection = getConnection();
		sql = "select biid from records where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int biid = -1;
		while (rs.next()) {
			biid = rs.getInt("biid");
		}
		sql = "select bid from bookinfo where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, biid);
		rs = ps.executeQuery();
		int res = -1;
		while (rs.next()) {
			int bid = rs.getInt("bid");
			sql = "insert into comments (bid,comment) values(?,?)";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bid);
			ps.setString(2, str);
			res = ps.executeUpdate();
		}
		return res;
	}

	// 充值
	@Override
	public int money(int uid, int money) throws Exception {
		connection = getConnection();
		sql = "select point from users where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		rs = ps.executeQuery();
		int res = -1;
		int point = 0;
		while (rs.next()) {
			point = rs.getInt("point");
			point = point + money;
			sql = "update users set point=? where id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, point);
			ps.setInt(2, uid);
			res = ps.executeUpdate();
			new UserBookDaoImpl().changelevel(uid, point);
		}
		closeAll();
		return point;
	}

	// 检查借的书是否超过上限
	@Override
	public boolean ifborrow(int uid, int point) throws Exception {
		connection = getConnection();
		int num = 0;
		sql = "select returnTime from records where uid=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, uid);
		rs = ps.executeQuery();
		boolean flag = false;
		while (rs.next()) {
			String returnTime = rs.getString("returnTime");
			if (returnTime == null) {
				num++;
			}
		}

		if (point >= 0 && point < 50) {
			if (num < 1) {
				flag = true;
			}
		} else if (point >= 50 && point < 100) {
			if (num < 2) {
				flag = true;
			}
		} else if (point >= 100 && point < 150) {
			if (num < 3) {
				flag = true;
			}
		} else if (point >= 150) {
			if (num < 4) {
				flag = true;
			}
		}
		return flag;
	}

	@Override
	public int adbook(int biid) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	// 借书的时候增加此书的被借书次数和已借书数量
	/*
	 * @Override public int adbook(int biid) throws Exception { connection =
	 * getConnection(); sql = "select bid from bookinfo where id=?"; ps =
	 * connection.prepareStatement(sql); ps.setInt(1, biid); rs = ps.executeQuery();
	 * int res = -1; while (rs.next()) { int bid = rs.getInt("bid"); }
	 * 
	 * return 0; }
	 */
	// 借书时被借次数+1
	public void booksDiscount(int biid) throws Exception {
		connection = getConnection();
		int bid = findbiidBooks(biid).getId();
		sql = "select discount from books where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, bid);
		rs = ps.executeQuery();
		int discount1 = 0;
		while (rs.next()) {
			discount1 = rs.getInt("discount");
			discount1++;
		}
		sql = "update books set discount=? where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, discount1);
		ps.setInt(2, bid);
		ps.executeUpdate();
		closeAll();
	}

	// 把预约的书借走后改变state
	public int changeOrder(int id) throws Exception {
		int res = -1;
		connection = getConnection();
		sql = "update book set state=0 where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		res = ps.executeUpdate();
		closeAll();
		return res;
	}

}
