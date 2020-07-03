package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Books;

public interface BooksBiz {
	// ���ͼ��(��ͼ������������)
	public boolean addBooks(Books books) throws Exception;
	//ɾ��ͼ��
	public boolean deleteBooks(String name) throws Exception;
	//�޸�ͼ��
	public boolean reviseBooks(String name,int counts) throws Exception;
	// ��ѯ����ͼ��
	public List<Books> findAllBooks() throws Exception;
	//����������ѯͼ��
	public List<Books> findNameBooks(String name) throws Exception;
	//��������������
	public List<Books> findAuthorBooks(String author) throws Exception;
	//�������Ͳ�ѯͼ��
	public List<Books> findTypeBooks(String type) throws Exception;
	//���������ؼ��ֲ�ѯͼ��
	public List<Books> findOnename(String name) throws Exception;
	//�ϼ�ͼ��
	public boolean onBooks(String name)throws Exception;
	//�¼�ǰ�ж����Ƿ񱻽���
	public int inorout(int id)throws Exception;
	//�¼�ͼ��
	public boolean downBooks(int id)throws Exception;
	//�ж��ϼ����Ƿ��ظ�
	public boolean ifbook(String name)throws Exception;

}
