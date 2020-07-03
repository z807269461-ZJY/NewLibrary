package com.iotek.biz.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import com.iotek.biz.UserBookBiz;
import com.iotek.dao.UserBookDao;
import com.iotek.dao.impl.UserBookDaoImpl;
import com.iotek.entity.Book;
import com.iotek.entity.Bookinfo;
import com.iotek.entity.Books;
import com.iotek.entity.Comments;
import com.iotek.entity.Records;
import com.iotek.entity.ShowInfo;

public class UserBookBizImpl implements UserBookBiz{
	private UserBookDao UBDao=new UserBookDaoImpl();
	//�鿴������
	public List<ShowInfo> showAllBooks(List<Bookinfo> listinfo,List<Books> list) throws Exception{
		List<ShowInfo> listShow=new ArrayList<ShowInfo>();
		for (Bookinfo BookInfo : listinfo) {
			for (Books Books : list) {
				if(BookInfo.getBid()==Books.getId()) {
					int id=BookInfo.getId();
					String bookname=Books.getName();
					String type=Books.getType();
					String author=Books.getAuthor();
					int inorout=BookInfo.getInorout();
					int state=BookInfo.getState();
					ShowInfo show=new ShowInfo(id, bookname, type, author, inorout, state);
					listShow.add(show);
				}
			}
		}
		return listShow;
		
	}

	@Override
	public List<Bookinfo> findAllBooks() throws Exception {
		// TODO Auto-generated method stub
		return UBDao.findAllBooks();
	}

	@Override
	public List<ShowInfo> findNameBooks(String name,List<ShowInfo> listAll) throws Exception {
		List<ShowInfo> showName=new ArrayList<ShowInfo>();
		for (ShowInfo showInfo : listAll) {
			if(showInfo.getBookName().equals(name)) {
				showName.add(showInfo);
			}
		}
		return showName;
	}

	@Override
	public List<Bookinfo> findOneNameBooks(String name) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.findOneNameBooks(name);
	}
	
//����
	@Override
	public boolean borrowBooks(int biid,int uid) throws Exception {
		int res = UBDao.borrowBooks(biid, uid);
		if (res != -1) {
			return true;
		}
		return false;
	}
	// �жϻ�����ѡ���Ƿ���ȷ
	public boolean ifReturnBook(int id, int uid) throws Exception{
		return UBDao.ifReturnBook(id, uid);
		}
	//����
	@Override
	public int returnBooks(int biid, int uid) throws Exception {
		return UBDao.returnBooks(biid, uid);
	}
	// ԤԼǰ����Ƿ��Ѿ������Ȿ��
	public boolean beforeOrder(int uid,int bid)throws Exception{
		return UBDao.beforeOrder(uid, bid);
	}
	//ԤԼ
	@Override
	public int orderBooks(int bid, int id) throws Exception {
		return UBDao.orderBooks(bid, id);
	
	}

	@Override
	public List<Records> showborrow(int id) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.showborrow(id);
	}
	//�����¼չʾ
	public List<ShowInfo> showAllrecord(List<Records> listrecord,List<ShowInfo> listbooks){
		List<ShowInfo> showrecord=new ArrayList<ShowInfo>();
		for (Records record : listrecord) {
			for (ShowInfo showbook : listbooks) {
				if(showbook.getId()==record.getBiid()) {
					int id=record.getId();
					String name =showbook.getBookName();
					String lendtime=record.getLendTime();
					String returntime=record.getReturnTime();
					ShowInfo records=new ShowInfo(id, name, lendtime, returntime);
					showrecord.add(records);
				}
			}
		}
		return showrecord;
	}

	@Override
	public Books findbiidBooks(int biid) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.findbiidBooks(biid);
	}

	//��ѯԤԼ��¼
	@Override
	public List<Book> findorder(int uid) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.findorder(uid);
	}
	//��ԤԼ����ԤԼ��¼
	public List<ShowInfo> showorder(List<Book> listBook,List<Books> listall)throws Exception{
		List<ShowInfo> showOrder =new ArrayList<ShowInfo>();
		for (Book order : listBook) {
			for (Books books : listall) {
				if(order.getBid()==books.getId()) {
					int id=order.getId();
					String name=books.getName();
					int state=order.getState();
					ShowInfo orders=new ShowInfo(id, name, state);
					showOrder.add(orders);
				}
			}
		}
		
		return showOrder;
	}

	@Override
	public List<Books> findbidBooks(int bid) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.findbidBooks(bid);
	}
//��ѯ����
	@Override
	public int showPoint(String name) throws Exception {
		int point=UBDao.showPoint(name);
		return point;
	}
	//����
	@Override
	public int renewBooks(int uid, int id) throws Exception {
		return UBDao.renewBooks(uid, id);
		
	}
	//������
	@Override
	public List<ShowInfo> showComments(List<Books> list) throws Exception {
		List<Comments> listcomments=UBDao.showComments();
		List<ShowInfo> showCom=new ArrayList<ShowInfo>();
		for (Comments comment : listcomments) {
			for (Books book : list) {
				if(comment.getBid()==book.getId()) {
					int id=comment.getId();
					String name=book.getName();
					String comments=comment.getComment();
					ShowInfo com=new ShowInfo(id, name, comments);
					showCom.add(com);
				}
			}
		}
		return showCom;
	}
	//�ı����ʱ�ı�level
	@Override
	public void changelevel(int point) throws Exception {
		// TODO Auto-generated method stub
		
	}
//д����
	@Override
	public boolean writeComment(int biid, String str) throws Exception {
		int res = UBDao.writeComment(biid, str);
		if (res != -1) {
			return true;
		}
		return false;
	}
	//��ֵ
	@Override
	public int money(int uid, int money) throws Exception {
		
		return UBDao.money(uid, money);
	}
	//�ж��Ƿ��ܼ�������

	@Override
	public boolean ifborrow(int uid,int point) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.ifborrow(uid, point);
	}
	//�����ʱ�����Ӵ���ı�����������ѽ�������
	@Override
	public boolean adbook(int biid) throws Exception {
		// TODO Auto-generated method stub
		return false;
	}
	//����ǰ�ж��Ƿ��ܽ�
	@Override
	public int BookState(int id) throws Exception {
		// TODO Auto-generated method stub
		return UBDao.BookState(id);
	}
	//����ʱ�������+1
	public void booksDiscount (int biid) throws Exception{
		UBDao.booksDiscount(biid);
	}
	//������ж��Ȿ���ǲ����Լ�ԤԼ����
	public int AfterBorrow(List<Book> list,int bid) {
		int res=-1;
		if(!list.isEmpty()) {
			for (Book book : list) {
				if(book.getState()==1) {
					if(book.getBid()==bid);
					res=book.getId();
				}
			}
		}
		return res;
	}
	//��ԤԼ������ߺ�ı�state
	public boolean changeOrder(int id)throws Exception{
		boolean flag=false;
		int res=UBDao.changeOrder(id);
		if(res!=-1) {
			flag=true;
		}
		return flag;
		
	}
	//����ϲ��������
	public String GuessTYPE(List<Records> list)throws Exception{
		List<Books> Books =new ArrayList<Books>();
		for (Records records : list) {
			Books.add(UBDao.findbiidBooks(records.getBiid()));
		}
		Map<String, Integer> Type=new HashMap<String, Integer>();
		for (Books books2 : Books) {
			if(Type.containsKey(books2.getType())) {
				Integer num=Type.get(books2.getType());
				Type.put(books2.getType(),num+1);
			}else {
				Type.put(books2.getType(), 1);
			}
		}
		int max=-1;
		String TYPE=null;
		for (Map.Entry<String, Integer> entry : Type.entrySet()) {
		    if(max<entry.getValue()) {
		    	max= entry.getValue();
		    	TYPE=entry.getKey();
		    }
		}
		return TYPE;
	}
	//����ϲ��������
	public String GuessAuthor(List<Records> list)throws Exception{
		List<Books> Books =new ArrayList<Books>();
		for (Records records : list) {
			Books.add(UBDao.findbiidBooks(records.getBiid()));
		}
		Map<String, Integer> Author=new HashMap<String, Integer>();
		for (Books books2 : Books) {
			if(Author.containsKey(books2.getAuthor())) {
				Integer num=Author.get(books2.getAuthor());
				Author.put(books2.getAuthor(),num+1);
			}else {
				Author.put(books2.getAuthor(), 1);
			}
		}
		int max=-1;
		String AUTHOR=null;
		for (Map.Entry<String, Integer> entry : Author.entrySet()) {
		    if(max<entry.getValue()) {
		    	max= entry.getValue();
		    	AUTHOR=entry.getKey();
		    }
		}
		return AUTHOR;
		
	}

}
