package com.iotek.util;

import java.math.BigInteger;
import java.security.MessageDigest;
import java.util.Scanner;

public class Utils {
	public static Scanner scanner = new Scanner(System.in);

	public static boolean isGoOn(String str) {
		System.out.println(str);
		String choice = scanner.next();
		if (choice.equals("n")) {
			return false;
		}
		return true;
	}
	
	// ����������
		public static int inputIsNum() throws Exception {
			while (true) {
				try {
					int temp = scanner.nextInt();
					if (temp < 0) {
						throw new Exception("���������Ϊ" + temp + "����������");
					}
					return temp;
				} catch (Exception e) {
					System.out.println("����������� ......��");
					System.out.println("����������");
					scanner.nextLine();
				}
			}
		}
		//MD5����
		public String getMD5(String str) {
			try {
				// ����һ��MD5���ܼ���ժҪ
				MessageDigest md = MessageDigest.getInstance("MD5");
				// ����md5����
				md.update(str.getBytes());
				//update����������ʹ��ָ�����ֽڸ���ժҪ��
				// digest()���ȷ������md5 hashֵ������ֵΪ8Ϊ�ַ�������Ϊmd5 hashֵ��16λ��hexֵ��ʵ���Ͼ���8λ���ַ�
				// BigInteger������8λ���ַ���ת����16λhexֵ�����ַ�������ʾ���õ��ַ�����ʽ��hashֵ
				return new BigInteger(1, md.digest()).toString(16);
				//ͨ��ִ���������֮������ղ�����ɹ�ϣ���㡣
				//digest(byte[] buf, int offset, int len) 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
}
