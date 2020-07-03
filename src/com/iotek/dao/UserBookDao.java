package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;

public interface UserBookDao {
	//查询所有书籍
	public List<Bookinfo> findAllBooks() throws Exception;
	//根据名称查询书籍
	public List<Bookinfo> findNameBooks(String name) throws Exception;
	//根据书名关键字查询
	public List<Bookinfo> findOneNameBooks(String name) throws Exception;
	//借书前判断是否能借
	public int BookState (int id)throws Exception;
	//借书
	public int borrowBooks(int biid,int uid) throws Exception;
	// 判断还书编号选择是否正确
	public boolean ifReturnBook(int id, int uid) throws Exception;
	//还书
	public int returnBooks(int biid,int uid) throws Exception;
	// 预约前检查是否已经借了这本书
	public boolean beforeOrder(int uid,int bid)throws Exception;
	//预约
	public int orderBooks(int bid,int id) throws  Exception;
	//续借
	public int renewBooks(int uid,int id) throws Exception;
	//查询借书记录
	public List<Records> showborrow(int id) throws Exception;
	//根据biid查询表
	public Books findbiidBooks(int biid) throws Exception;
	//查询预约记录
	public List<Book> findorder(int uid) throws Exception;
	//根据bid查询表
	public List<Books> findbidBooks(int bid) throws Exception;
	//查积分
	public int showPoint(String name) throws Exception;
	//查评价
	public List<Comments> showComments() throws Exception;
	//改变积分时改变level
	public void changelevel(int uid,int point)throws Exception;
	//写评价
	public int writeComment(int biid,String str) throws Exception;
	//充值
	public int money(int uid,int money) throws Exception;
	//判断是否能继续借书
	public boolean ifborrow(int uid,int point) throws Exception;
	//借书的时候增加此书的被借书次数和已借书数量
	public int adbook(int biid) throws Exception;
	//借书时被借次数+1
	public void booksDiscount (int biid) throws Exception;
	//把预约的书借走后改变state
	public int changeOrder(int id)throws Exception;

}
