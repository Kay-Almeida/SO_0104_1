package view;
 
import java.util.concurrent.Semaphore;
 
import controller.ThreadCavaleiros;
 
public class Main {

	public static void main(String[] args) {

		Semaphore semaforo = new Semaphore (1); 

		for(int id=1; id<5; id++) {

			Thread tcavalo = new ThreadCavaleiros(id, semaforo); 

			tcavalo.start();

		}

	}
 
}
