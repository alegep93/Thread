package com.prova.thread.contatore;

public class Misura {
	private int counter;
	private long milliseconds;
	
	public Misura(int c,long ms){
		counter = c;
		milliseconds = ms;
	}

	public int getCounter() {
		return counter;
	}

	public void setCounter(int counter) {
		this.counter = counter;
	}

	public long getMilliseconds() {
		return milliseconds;
	}

	public void setMilliseconds(long milliseconds) {
		this.milliseconds = milliseconds;
	}
}
