package com.iotek.biz.impl;

import java.util.List;

import com.iotek.biz.BooksBiz;
import com.iotek.dao.BooksDao;
import com.iotek.dao.impl.BooksDaoImpl;
import com.iotek.entity.Books;

public class BooksBizImpl implements BooksBiz {
	private BooksDao booksDao=new BooksDaoImpl();
//添加图书
	@Override
	public boolean addBooks(Books books) throws Exception {
		int res = booksDao.addBooks(books);
		if (res != -1) {
			return true;
		}
		return false;
	}
//查找所有图书
	@Override
	public List<Books> findAllBooks() throws Exception {
		// TODO Auto-generated method stub
		return booksDao.findAllBooks();
	}
//删除图书
	@Override
	public boolean deleteBooks(String name) throws Exception {
		int res=booksDao.deleteBooks(name);
		if (res != -1&&res!=0) {
			return true;
		}else if(res==0) {
			System.out.println("书名不存在");
			return false;
		}
		return false;
	}
//修改图书
	@Override
	public boolean reviseBooks(String name, int counts) throws Exception {
		int res = booksDao.reviseBooks(name, counts);
		if (res != -1&&res!=0) {
			return true;
		}else if(res==0) {
			System.out.println("书名不存在");
			return false;
		}
		return false;
	}
//上架图书
	@Override
	public boolean onBooks(String name) throws Exception {
		int res = booksDao.onBooks(name);
		if (res != -1&&res!=-2) {
			return true;
		}else if(res==-2){
			System.out.println("书本数量不足，请及时补充");
			return false;
		}
		return false;
	}
//下架图书
	@Override
    public boolean downBooks(int id) throws Exception {
	int res = booksDao.downBooks(id);
	if (res != -1&&res!=-2) {
		
		return true;
	}else if(res==-2){
		System.out.println("书本数量不足，请及时补充");
		return false;
	}
	return false;
}
//根据书名查询图书
@Override
    public List<Books> findNameBooks(String name) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findNameBooks(name);
}
//根据作者名查询图书
@Override
public List<Books> findAuthorBooks(String author) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findAuthorBooks(author);
}
//根据类型名查询图书
@Override
public List<Books> findTypeBooks(String type) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findTypeBooks(type);
}
//根据书名关键字查询图书
@Override
public List<Books> findOnename(String name) throws Exception {
	return booksDao.findOnename(name);
}
@Override
public boolean ifbook(String name) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.ifbook(name);
}
//下架前判断是否被借走
@Override
public int inorout(int id) throws Exception {
	return booksDao.inorout(id);
}

}
