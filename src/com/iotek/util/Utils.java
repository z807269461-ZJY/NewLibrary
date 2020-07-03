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
	
	// 纯数字输入
		public static int inputIsNum() throws Exception {
			while (true) {
				try {
					int temp = scanner.nextInt();
					if (temp < 0) {
						throw new Exception("您输入的数为" + temp + "不是正整数");
					}
					return temp;
				} catch (Exception e) {
					System.out.println("您输入的有误 ......！");
					System.out.println("请重新输入");
					scanner.nextLine();
				}
			}
		}
		//MD5加密
		public String getMD5(String str) {
			try {
				// 生成一个MD5加密计算摘要
				MessageDigest md = MessageDigest.getInstance("MD5");
				// 计算md5函数
				md.update(str.getBytes());
				//update方法参数是使用指定的字节更新摘要。
				// digest()最后确定返回md5 hash值，返回值为8为字符串。因为md5 hash值是16位的hex值，实际上就是8位的字符
				// BigInteger函数则将8位的字符串转换成16位hex值，用字符串来表示；得到字符串形式的hash值
				return new BigInteger(1, md.digest()).toString(16);
				//通过执行诸如填充之类的最终操作完成哈希计算。
				//digest(byte[] buf, int offset, int len) 
			} catch (Exception e) {
				e.printStackTrace();
				return null;
			}
		}
}
