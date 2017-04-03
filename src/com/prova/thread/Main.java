package com.prova.thread;

import com.prova.thread.outputter.CountingOutputterIntelligente;
import com.prova.thread.outputter.Outputter;

public class Main {
	private static Outputter out = new Outputter() {
		private int c = 5;
		
		@Override
		public void println(String s) {
			if(c%3 != 0){
				System.out.print(s +  " ");
				c++;
			}else{
				System.out.println();
				c++;
			}
		}
	};
	
	public static void main(String[] args) throws InterruptedException {
		int numOfIteration = 20;
		Thread[] tArray = new Thread[2];
		CountingOutputterIntelligente outi = new CountingOutputterIntelligente(out);

		for(int i=0; i<tArray.length; i++){
			MyThread mt = new MyThread("  Thread " + i, numOfIteration, outi);
			tArray[i] = new Thread(mt);
		}

		for(Thread t : tArray){
			t.start();
		}
		
		for(Thread t : tArray){
			t.join(); //Aspetta che il thread abbia concluso il suo lavoro
		}
		
		outi.flush();
		System.out.println("--------------------------------------");
		System.out.println("Numero di thread visti: " + (outi.getSommaThread()));
		System.out.println("End of work!");
	}
}