package com.iotek.view;

public class Start {
	public static void main(String[] args) {
		try {
			new MainView().mainView();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
