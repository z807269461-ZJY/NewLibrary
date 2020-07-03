package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Books;

public interface BooksBiz {
	// 添加图书(向图书表中添加数据)
	public boolean addBooks(Books books) throws Exception;
	//删除图书
	public boolean deleteBooks(String name) throws Exception;
	//修改图书
	public boolean reviseBooks(String name,int counts) throws Exception;
	// 查询所有图书
	public List<Books> findAllBooks() throws Exception;
	//根据书名查询图书
	public List<Books> findNameBooks(String name) throws Exception;
	//根据作者名查找
	public List<Books> findAuthorBooks(String author) throws Exception;
	//根据类型查询图书
	public List<Books> findTypeBooks(String type) throws Exception;
	//根据书名关键字查询图书
	public List<Books> findOnename(String name) throws Exception;
	//上架图书
	public boolean onBooks(String name)throws Exception;
	//下架前判断书是否被借走
	public int inorout(int id)throws Exception;
	//下架图书
	public boolean downBooks(int id)throws Exception;
	//判断上架书是否重复
	public boolean ifbook(String name)throws Exception;

}
