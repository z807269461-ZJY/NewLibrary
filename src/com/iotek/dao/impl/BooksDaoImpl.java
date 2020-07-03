package com.iotek.dao.impl;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.iotek.dao.BooksDao;
import com.iotek.entity.Books;

public class BooksDaoImpl extends BaseDao implements BooksDao{
//��ͼ������
	@Override
	public int addBooks(Books books) throws Exception {
		connection=getConnection();
		sql = "insert into books(name,count,type,author,discount)values(?,?,?,?,?,?)";
		int res=-1;
		ps = connection.prepareStatement(sql);
		ps.setString(1, books.getName());
		ps.setInt(2, books.getCount());
		ps.setString(3, books.getType());
		ps.setString(4, books.getAuthor());
		ps.setInt(5, books.getDiscount());
		res = ps.executeUpdate();
		closeAll();
		return res;
	}
//����ͼ��
	@Override
	public List<Books> findAllBooks() throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books";
		ps = connection.prepareStatement(sql);
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
//ɾ��ͼ��
	@Override
	public int deleteBooks(String name) throws Exception {
		connection=getConnection();
		sql = "delete from books where name=?";
		int res=-1;
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		res = ps.executeUpdate();
		closeAll();
		return res;
	}
//�޸�ͼ��
	@Override
	public int reviseBooks(String name, int counts) throws Exception {
		connection=getConnection();
		sql = "update books set count=? where name=?";
		int res=-1;
		ps = connection.prepareStatement(sql);
		ps.setInt(1, counts);
		ps.setString(2, name);
		res = ps.executeUpdate();
		closeAll();
		return res;
	}
//�ϼ�ͼ��
	@Override
	public int onBooks(String name) throws Exception {
		connection=getConnection();
		sql = "select id,count from books where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		int res=-1;
		while(rs.next()) {
			int count=rs.getInt("count");
			if(count<1) {
				res=-2;
			}else {
				count--;
				int id = rs.getInt("id");
				sql = "insert into bookinfo(bid)values(?)";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, id);
				ps.executeUpdate();
				sql = "update books set count=? where name=?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, count);
				ps.setString(2, name);
				res = ps.executeUpdate();
			}
		}
		closeAll();
		return res;
	}
	//�¼�ͼ���Ƿ��ڹ�
	public int inorout(int id) throws Exception {
		connection=getConnection();
		sql="select inorout from bookinfo where id=?";
		ps=connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int inorout=-1;
		while(rs.next()) {
			inorout=rs.getInt("inorout");
		}
		closeAll();
		return inorout;
	}
//�¼�ͼ��
	@Override
	public int downBooks(int id) throws Exception {//ͬ���鼮һ���¼�
		connection=getConnection();
		sql = "update bookinfo set state=0 where id=?";
		ps =connection.prepareStatement(sql);
		ps.setInt(1, id);
		ps.executeUpdate();
		sql = "select bid from bookinfo where id=?";
		ps = connection.prepareStatement(sql);
		ps.setInt(1, id);
		rs = ps.executeQuery();
		int res=-1;
		int bid;
		while(rs.next()) {
			bid = rs.getInt("bid");
			sql = "select count from books where id=?";
			ps = connection.prepareStatement(sql);
			ps.setInt(1, bid);
			rs = ps.executeQuery();
			while(rs.next()) {
				int count= rs.getInt("count");
				count++;
				sql = "update books set count=? where id=?";
				ps = connection.prepareStatement(sql);
				ps.setInt(1, count);
				ps.setInt(2, bid);
				res = ps.executeUpdate();
			}
		}
		closeAll();
		return res;
	}
//����������ѯͼ��
	@Override
	public List<Books> findNameBooks(String name) throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books where name=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, name);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			name = rs.getString("name");
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
//������������ѯͼ��
	@Override
	public List<Books> findAuthorBooks(String author) throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books where author=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, author);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int count = rs.getInt("count");
			String type = rs.getString("type");
			author = rs.getString("author");
			int discount = rs.getInt("discount");
			
			Books books = new Books(id, name, count, type, author, discount);
			list.add(books);
		}
		closeAll();
		return list;
	}
//�������Ͳ�ѯͼ��
	@Override
	public List<Books> findTypeBooks(String type) throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books where type=?";
		ps = connection.prepareStatement(sql);
		ps.setString(1, type);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			String name = rs.getString("name");
			int count = rs.getInt("count");
			type = rs.getString("type");
			String author = rs.getString("author");
			int discount = rs.getInt("discount");
			
			Books books = new Books(id, name, count, type, author, discount);
			list.add(books);
		}
		closeAll();
		return list;
	}
//���������ؼ��ֲ�ѯͼ��
	@Override
	public List<Books> findOnename(String name) throws Exception {
		List<Books> list = new ArrayList<Books>();
		connection = getConnection();
		sql = "select * from books where name like '%"+name+"%'";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			int id = rs.getInt("id");
			name = rs.getString("name");
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
	@Override
	public boolean ifbook(String name) throws Exception {
		List<String> list = new ArrayList<String>();
		connection = getConnection();
		sql = "select name from books";
		ps = connection.prepareStatement(sql);
		rs = ps.executeQuery();
		while (rs.next()) {
			String bname=rs.getString("name");
			list.add(bname);
		}
		if(list.contains(name)) {
			closeAll();
			return false;
		}else {
			closeAll();
			return true;
		}
		
	}
}
