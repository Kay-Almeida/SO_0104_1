package controller;

import java.util.concurrent.Semaphore;

public class ThreadCavaleiros extends Thread{
	int id; 
	static private boolean tocha = true; 
	static private boolean pedra = true; 
	static private int portacerta = (int) ((Math.random()*3)+1); 
	private Semaphore semaforo; 
	public ThreadCavaleiros (int id, Semaphore semaforo) {
		this.semaforo = semaforo; 
		this.id = id; 
	}
	public void run() {
		int distanciamax = 2000;
		int distanciapercorrida = 0; 
		int distancia = (int) ((Math.random()*2)+2); 
		int km = 0; 
		while(distanciapercorrida<distanciamax) {
			distanciapercorrida = distanciapercorrida + distancia; 
			if(distanciapercorrida>=km) {
			System.out.println("O Cavaleiro "+ id+ " percorreu "+ distanciapercorrida+ " metros");
			km+=100; 
			}
			int tempo = 50; 
			try {
				sleep(tempo);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			try {
				semaforo.acquire();
			if(distanciapercorrida>=500 && tocha==true) {
				distancia += 2; 
				System.out.println("O Cavaleiro "+ id+ " pegou a Tocha");
				tocha = false; 
			}
			if (distanciapercorrida>=1500 && pedra==true) {
				distancia += 2; 
				System.out.println("O Cavaleiro "+ id+ " pegou a Pedra Brilhante");
				pedra = false;
 
			}
			} catch (InterruptedException e) {
				e.printStackTrace();
			}finally {
				semaforo.release();
			}
		}
		porta(); 
	}
	public void porta() {
	 if (id==portacerta) {
		 System.out.println("O Cavaleiro "+ id+ " entrou na Porta "+ portacerta + " e conseguiu escapar do corredor escuro");
	 }else {
		 System.out.println("O Cavaleiro "+ id+ " entrou na Porta "+ id + " e morreu devorado pelo medo abundante das verdades");
	 }
	}

}