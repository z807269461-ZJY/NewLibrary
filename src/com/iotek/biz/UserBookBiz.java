package com.iotek.biz;

import java.util.List;

import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;
import com.iotek.entity.ShowInfo;

public interface UserBookBiz {
	//�鿴������
		public List<ShowInfo> showAllBooks(List<Bookinfo> listinfo,List<Books> list) throws Exception;
	// ��ѯ����ͼ��
		public List<Bookinfo> findAllBooks() throws Exception;
		//����������ͼ��
		public List<ShowInfo> findNameBooks(String name,List<ShowInfo> listAll) throws Exception;
		//���������ؼ��ֲ�ѯ
		public List<Bookinfo> findOneNameBooks(String name) throws Exception;
		//����ǰ�ж��Ƿ��ܽ�
		public int BookState (int id)throws Exception;
		//����
		public boolean borrowBooks(int biid,int uid) throws Exception;
		// �жϻ�����ѡ���Ƿ���ȷ
		public boolean ifReturnBook(int id, int uid) throws Exception;
		//����
		public int returnBooks(int biid,int uid) throws Exception;
		// ԤԼǰ����Ƿ��Ѿ������Ȿ��
		public boolean beforeOrder(int uid,int bid)throws Exception;
		//ԤԼ
		public int orderBooks(int bid,int id) throws Exception;
		//����
		public int renewBooks(int uid,int id) throws Exception;
		//��ѯ��¼
		public List<Records> showborrow(int id) throws Exception;
		//�����¼չʾ
		public List<ShowInfo> showAllrecord(List<Records> listrecord,List<ShowInfo> listbooks);
		//����biid��ѯ��
		public Books findbiidBooks(int biid) throws Exception;
		//��ѯԤԼ
		public  List<Book> findorder (int uid) throws Exception;
		//��ԤԼ����ԤԼ��¼
		public List<ShowInfo> showorder(List<Book> listBook,List<Books> listall)throws Exception;
		//����bid��ѯ��
		public List<Books> findbidBooks(int bid) throws Exception;
		//��ѯ����
		public int showPoint(String name)throws Exception;
		//��ѯ����
		public List<ShowInfo> showComments(List<Books> list) throws Exception;
		//���ֱ䶯ʱ�ı�VIP�ȼ�
		public void changelevel(int point)throws Exception;
		//д����
		public boolean writeComment(int biid,String str) throws Exception;
		//��ֵ
		public int money(int uid,int money) throws Exception;
		//�ж��Ƿ��ܼ�������
		public boolean ifborrow(int uid,int point) throws Exception;
		//�����ʱ�����Ӵ���ı�����������ѽ�������
		public boolean adbook(int biid) throws Exception;
		//����ʱ�������+1
		public void booksDiscount (int biid) throws Exception;
		//������ж��Ȿ���ǲ����Լ�ԤԼ����
		public int AfterBorrow(List<Book> list,int bid);
		//��ԤԼ������ߺ�ı�state
		public boolean changeOrder(int id)throws Exception;
		//����ϲ��
		public String GuessTYPE(List<Records> list)throws Exception;
		//����ϲ��������
		public String GuessAuthor(List<Records> list)throws Exception;
}
