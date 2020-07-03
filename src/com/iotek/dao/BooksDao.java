package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Books;

public interface BooksDao {
	// ���ͼ��
		public int addBooks(Books books) throws Exception;
	// ɾ��ͼ��
		public int deleteBooks(String name) throws Exception;
	//�޸�ͼ��
		public int reviseBooks(String name,int counts) throws Exception;
		//��ѯ����ͼ��
		public List<Books> findAllBooks() throws Exception;
		//����������ѯͼ��
		public List<Books> findNameBooks(String name) throws Exception;
		//������������ѯͼ��
		public List<Books> findAuthorBooks(String author) throws Exception;
		//������������ѯͼ��
		public List<Books> findTypeBooks(String type) throws Exception;
		//���������ؼ��ֲ�ѯͼ��
		public List<Books> findOnename(String name) throws Exception;
		//�¼�ǰ�ж��Ƿ񱻽���
		public int inorout(int id) throws Exception;
		//�ϼ�ͼ��
		public int onBooks(String name) throws Exception;
		//�¼�ͼ��
		public int downBooks(int id) throws Exception;
		//�ж������Ƿ��ظ�
		public boolean ifbook(String name) throws Exception;
}
