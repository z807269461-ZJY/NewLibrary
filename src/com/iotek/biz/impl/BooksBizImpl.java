package com.iotek.biz.impl;

import java.util.List;

import com.iotek.biz.BooksBiz;
import com.iotek.dao.BooksDao;
import com.iotek.dao.impl.BooksDaoImpl;
import com.iotek.entity.Books;

public class BooksBizImpl implements BooksBiz {
	private BooksDao booksDao=new BooksDaoImpl();
//���ͼ��
	@Override
	public boolean addBooks(Books books) throws Exception {
		int res = booksDao.addBooks(books);
		if (res != -1) {
			return true;
		}
		return false;
	}
//��������ͼ��
	@Override
	public List<Books> findAllBooks() throws Exception {
		// TODO Auto-generated method stub
		return booksDao.findAllBooks();
	}
//ɾ��ͼ��
	@Override
	public boolean deleteBooks(String name) throws Exception {
		int res=booksDao.deleteBooks(name);
		if (res != -1&&res!=0) {
			return true;
		}else if(res==0) {
			System.out.println("����������");
			return false;
		}
		return false;
	}
//�޸�ͼ��
	@Override
	public boolean reviseBooks(String name, int counts) throws Exception {
		int res = booksDao.reviseBooks(name, counts);
		if (res != -1&&res!=0) {
			return true;
		}else if(res==0) {
			System.out.println("����������");
			return false;
		}
		return false;
	}
//�ϼ�ͼ��
	@Override
	public boolean onBooks(String name) throws Exception {
		int res = booksDao.onBooks(name);
		if (res != -1&&res!=-2) {
			return true;
		}else if(res==-2){
			System.out.println("�鱾�������㣬�뼰ʱ����");
			return false;
		}
		return false;
	}
//�¼�ͼ��
	@Override
    public boolean downBooks(int id) throws Exception {
	int res = booksDao.downBooks(id);
	if (res != -1&&res!=-2) {
		
		return true;
	}else if(res==-2){
		System.out.println("�鱾�������㣬�뼰ʱ����");
		return false;
	}
	return false;
}
//����������ѯͼ��
@Override
    public List<Books> findNameBooks(String name) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findNameBooks(name);
}
//������������ѯͼ��
@Override
public List<Books> findAuthorBooks(String author) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findAuthorBooks(author);
}
//������������ѯͼ��
@Override
public List<Books> findTypeBooks(String type) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.findTypeBooks(type);
}
//���������ؼ��ֲ�ѯͼ��
@Override
public List<Books> findOnename(String name) throws Exception {
	return booksDao.findOnename(name);
}
@Override
public boolean ifbook(String name) throws Exception {
	// TODO Auto-generated method stub
	return booksDao.ifbook(name);
}
//�¼�ǰ�ж��Ƿ񱻽���
@Override
public int inorout(int id) throws Exception {
	return booksDao.inorout(id);
}

}
