package com.prova.thread.outputter;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import com.prova.thread.contatore.Misura;


public class CountingOutputterMoltoIntelligente implements Outputter {	
	private String ultimo = "";
	private int numero = 0;
	private long ultima_timestamp;
	private Outputter delegate;
	private Map<String, List<Misura>> sommaThreadVisti = new HashMap<>();
	
	public CountingOutputterMoltoIntelligente(Outputter delegate) {
		this.delegate = delegate;
	}
	
	@Override
	public synchronized void println(String s) {
		if(ultimo.equals(s)){
			List<Misura> clist = sommaThreadVisti.get(s);
			int pos = clist.size() - 1;
			Misura e = clist.get(pos);
			e.setCounter(e.getCounter()+1);
			e.setMilliseconds((new Date()).getTime() - ultima_timestamp);
			numero++;
		}else {
			ultima_timestamp = (new Date()).getTime();
			List<Misura> list = sommaThreadVisti.get(s);
			if(list == null){
				list = new ArrayList<>();
				list.add(new Misura(1,0));
				sommaThreadVisti.put(s, list);
			}else{
				list.add(new Misura(1,0));
			}
			
			if(numero > 0)
				System.out.println("Visto " + numero + " volte " + ultimo);
			
			System.out.println("--------------------------------------");
			System.out.println("Iniziamo con: " + s);
			
			ultimo = s;
			numero = 1;
		}
		
		delegate.println(s);
	}
	
	public void flush() {
		System.out.println("Visto " + numero + " volte " + ultimo);
		
		for (Entry<String, List<Misura>> e: sommaThreadVisti.entrySet()) {
			int sum = 0;
			System.out.println("--------------------------------------");
			System.out.println(e.getKey() + " eseguito in " + e.getValue().size() + " blocchi");
			for (Misura m : e.getValue()) {				
				System.out.println(e.getKey() + ": " + m.getCounter() + " volte (" + m.getMilliseconds() + "ms)");
				sum += m.getCounter();
			}
			System.out.println(e.getKey() + ": visto "+ sum + " volte in totale");
		}
		System.out.println("--------------------------------------");
	}
}
