package com.prova.thread;

import com.prova.thread.outputter.Outputter;

public class MyThread implements Runnable{
	private String message;
	private int num;
	private Outputter out;
	
	public MyThread(String message, int num, Outputter out){
		this.message = message;
		this.num = num;
		this.out = out;
	}
	
	@Override
	public void run(){
		for(int i=0; i<num; i++){
			out.println(message);
		}
	}
}
