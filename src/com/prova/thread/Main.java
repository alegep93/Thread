package com.prova.thread;

import java.util.ArrayList;
import java.util.List;

import com.prova.thread.outputter.CountingOutputterMoltoIntelligente;
import com.prova.thread.outputter.Outputter;

public class Main {
	private static Outputter out = new Outputter() {
		
		@Override
		public synchronized void println(String s) {
			System.out.println("  " + s);
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		int numOfIteration = 100;
		List<MyThread> mtList = new ArrayList<>();
		List<Thread> tList = new ArrayList<>();
		CountingOutputterMoltoIntelligente outi = new CountingOutputterMoltoIntelligente(out);

		for(int i=0; i<3; i++){
			MyThread mt = new MyThread("Thread " + i, numOfIteration, outi);
			mtList.add(mt);
		}

		for(MyThread mt : mtList){
			Thread t = new Thread(mt);
			tList.add(t);
		}
		
		for(Thread t : tList){
			t.start();
		}
		
		for(Thread t : tList){
			t.join();
		}
		
		outi.flush();
		System.out.println("End of work!");
	}
}