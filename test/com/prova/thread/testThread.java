package com.prova.thread;

import static org.junit.Assert.*;

import org.junit.Test;

import com.prova.thread.outputter.Outputter;

public class testThread {
	
	@Test
	public void testStampaIlNomeCorretto() {
		String nomeThread = "NomeThread";
		MyThread mt = new MyThread(nomeThread, 20, new Outputter() {
				@Override
				public void println(String s) {
					if(!nomeThread.equals(s))
						fail("Nome non corretto - " + s + " != " + nomeThread);
				}
			});
		mt.run();
		assertTrue(true); //Equivale al contrario di "fail" dunque "success"
	}
	
	public class CountingOutputter implements Outputter {
		private int count = 0;
		@Override
		public void println(String s) {
			count++;
		}
		
		public int getCount(){
			return count;
		}
	}
	
	@Test
	public void numeroDiStampeCorretto(){
		int numStampe = 20;
		CountingOutputter cout = new CountingOutputter();
		MyThread mt = new MyThread("Thread", numStampe, cout);
		mt.run();
		assertTrue(numStampe == cout.getCount());
	}
}
