package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Books;

public interface BooksDao {
	// 添加图书
		public int addBooks(Books books) throws Exception;
	// 删除图书
		public int deleteBooks(String name) throws Exception;
	//修改图书
		public int reviseBooks(String name,int counts) throws Exception;
		//查询所有图书
		public List<Books> findAllBooks() throws Exception;
		//根据书名查询图书
		public List<Books> findNameBooks(String name) throws Exception;
		//根据作者名查询图书
		public List<Books> findAuthorBooks(String author) throws Exception;
		//根据类型名查询图书
		public List<Books> findTypeBooks(String type) throws Exception;
		//根据书名关键字查询图书
		public List<Books> findOnename(String name) throws Exception;
		//下架前判断是否被借走
		public int inorout(int id) throws Exception;
		//上架图书
		public int onBooks(String name) throws Exception;
		//下架图书
		public int downBooks(int id) throws Exception;
		//判断书名是否重复
		public boolean ifbook(String name) throws Exception;
}
