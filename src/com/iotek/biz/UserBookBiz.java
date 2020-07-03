package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;
import com.iotek.entity.ShowInfo;

public interface UserBookBiz {
	//查看所有书
		public List<ShowInfo> showAllBooks(List<Bookinfo> listinfo,List<Books> list) throws Exception;
	// 查询所有图书
		public List<Bookinfo> findAllBooks() throws Exception;
		//根据书名查图书
		public List<ShowInfo> findNameBooks(String name,List<ShowInfo> listAll) throws Exception;
		//根据书名关键字查询
		public List<Bookinfo> findOneNameBooks(String name) throws Exception;
		//借书前判断是否能借
		public int BookState (int id)throws Exception;
		//借书
		public boolean borrowBooks(int biid,int uid) throws Exception;
		// 判断还书编号选择是否正确
		public boolean ifReturnBook(int id, int uid) throws Exception;
		//还书
		public int returnBooks(int biid,int uid) throws Exception;
		// 预约前检查是否已经借了这本书
		public boolean beforeOrder(int uid,int bid)throws Exception;
		//预约
		public int orderBooks(int bid,int id) throws Exception;
		//续借
		public int renewBooks(int uid,int id) throws Exception;
		//查询记录
		public List<Records> showborrow(int id) throws Exception;
		//借书记录展示
		public List<ShowInfo> showAllrecord(List<Records> listrecord,List<ShowInfo> listbooks);
		//根据biid查询表
		public Books findbiidBooks(int biid) throws Exception;
		//查询预约
		public  List<Book> findorder (int uid) throws Exception;
		//查预约完整预约记录
		public List<ShowInfo> showorder(List<Book> listBook,List<Books> listall)throws Exception;
		//根据bid查询表
		public List<Books> findbidBooks(int bid) throws Exception;
		//查询积分
		public int showPoint(String name)throws Exception;
		//查询评价
		public List<ShowInfo> showComments(List<Books> list) throws Exception;
		//积分变动时改变VIP等级
		public void changelevel(int point)throws Exception;
		//写评价
		public boolean writeComment(int biid,String str) throws Exception;
		//充值
		public int money(int uid,int money) throws Exception;
		//判断是否能继续借书
		public boolean ifborrow(int uid,int point) throws Exception;
		//借书的时候增加此书的被借书次数和已借书数量
		public boolean adbook(int biid) throws Exception;
		//借书时被借次数+1
		public void booksDiscount (int biid) throws Exception;
		//借书后判断这本书是不是自己预约的书
		public int AfterBorrow(List<Book> list,int bid);
		//把预约的书借走后改变state
		public boolean changeOrder(int id)throws Exception;
		//猜你喜欢
		public String GuessTYPE(List<Records> list)throws Exception;
		//猜你喜欢的作者
		public String GuessAuthor(List<Records> list)throws Exception;
}
