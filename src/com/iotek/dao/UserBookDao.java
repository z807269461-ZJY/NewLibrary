package com.iotek.dao;

import java.util.List;

import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;

public interface UserBookDao {
	//��ѯ�����鼮
	public List<Bookinfo> findAllBooks() throws Exception;
	//�������Ʋ�ѯ�鼮
	public List<Bookinfo> findNameBooks(String name) throws Exception;
	//���������ؼ��ֲ�ѯ
	public List<Bookinfo> findOneNameBooks(String name) throws Exception;
	//����ǰ�ж��Ƿ��ܽ�
	public int BookState (int id)throws Exception;
	//����
	public int borrowBooks(int biid,int uid) throws Exception;
	// �жϻ�����ѡ���Ƿ���ȷ
	public boolean ifReturnBook(int id, int uid) throws Exception;
	//����
	public int returnBooks(int biid,int uid) throws Exception;
	// ԤԼǰ����Ƿ��Ѿ������Ȿ��
	public boolean beforeOrder(int uid,int bid)throws Exception;
	//ԤԼ
	public int orderBooks(int bid,int id) throws  Exception;
	//����
	public int renewBooks(int uid,int id) throws Exception;
	//��ѯ�����¼
	public List<Records> showborrow(int id) throws Exception;
	//����biid��ѯ��
	public Books findbiidBooks(int biid) throws Exception;
	//��ѯԤԼ��¼
	public List<Book> findorder(int uid) throws Exception;
	//����bid��ѯ��
	public List<Books> findbidBooks(int bid) throws Exception;
	//�����
	public int showPoint(String name) throws Exception;
	//������
	public List<Comments> showComments() throws Exception;
	//�ı����ʱ�ı�level
	public void changelevel(int uid,int point)throws Exception;
	//д����
	public int writeComment(int biid,String str) throws Exception;
	//��ֵ
	public int money(int uid,int money) throws Exception;
	//�ж��Ƿ��ܼ�������
	public boolean ifborrow(int uid,int point) throws Exception;
	//�����ʱ�����Ӵ���ı�����������ѽ�������
	public int adbook(int biid) throws Exception;
	//����ʱ�������+1
	public void booksDiscount (int biid) throws Exception;
	//��ԤԼ������ߺ�ı�state
	public int changeOrder(int id)throws Exception;

}
