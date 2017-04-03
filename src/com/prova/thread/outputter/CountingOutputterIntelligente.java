package com.prova.thread.outputter;

public class CountingOutputterIntelligente implements Outputter {
	//String[] nomiVisti = new String[20];
	
	private String ultimo = "";
	private int numero = 0, sommaThread = 0;
	private Outputter delegate;
	
	public CountingOutputterIntelligente(Outputter delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public synchronized void println(String s) {
		if(ultimo.equals(s)){
			numero++;
		}else {
			if(numero > 0)
				System.out.println("Visto " + numero + " volte " + ultimo);
			
			System.out.println("--------------------------------------");
			System.out.println("Iniziamo con: " + s);
			
			ultimo = s;
			numero = 1;
		}
		
		sommaThread++;
		delegate.println(s);
	}
	
	public void flush() {
		System.out.println("Visto " + numero + " volte " + ultimo);
		ultimo = "";
	}
	
	public int getSommaThread(){
		return sommaThread;
	}
}
