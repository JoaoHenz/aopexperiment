import java.util.concurrent.ThreadLocalRandom;

import com.nfswsa.worker.Worker;
import com.nfswsa.dataserver.Server;

public class NSFW {

		static void orderAndSleep(Worker worker,String order) {
				
			worker.makeOrder(order);
			
			try {
				Thread.sleep(1000);;
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		
		public static void main(String args[]) {
			
			char[][][] mbs_server = new char[2][40][40]; //mb = message buffers
			char[][] mbs_worker = new char[2][30];
			
			//TODO open map1 and map2
			char[][][] maps = new char[2][40][40];
			int i,j,k;
			for (i=0;i<40;i++) {
				for (j=0;j<40;j++) {
					for (k=0;k<2;k++) {
						maps[k][j][i] = 'O';
					}
				}
			}
			
			Worker worker1 = new Worker("worker1","192.0.0.2",1);
			Worker worker2 = new Worker("worker2","192.0.0.3",2);
			Worker[] workerlist = {worker1,worker2};
			Server server = new Server(workerlist,"192.0.0.2");
			
			worker1.server = server;
			worker2.server = server;
			worker1.start();
			worker2.start();
			server.start();
			
			
			String order = "Change shoe 39 price to $540";
			orderAndSleep(worker1,order);
			
			order = "Change shoe 14 price to $100";
			orderAndSleep(worker2,order);
			
			order = "Change shoe 92 price to $12";
			orderAndSleep(worker2,order);	
		
			order = "Change shoe 47 price to $2500";
			orderAndSleep(worker2,order);
			
			
			
			try {
				Thread.sleep(100);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			worker1.program_hasended = true;
			worker2.program_hasended = true;
			server.program_hasended = true;
			
			try {
				worker1.join();
				worker2.join();
				server.join();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			System.out.println("End of the execution.............");

	}

}
