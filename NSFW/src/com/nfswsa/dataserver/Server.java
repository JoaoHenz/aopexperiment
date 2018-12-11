package com.nfswsa.dataserver;

import java.sql.Time;

import com.nfswsa.worker.Worker;

public class Server extends Thread{

	public Worker workers[];
	public String IPv4;
	public boolean program_hasended;
	public int worker_beingattended;
	public boolean wba_haschanged;
	public long maximum_time;
	public long current_time;
	public long starting_time;
	public boolean is_answering;
	public boolean mustreturn;
	
	
	
	public void receiveOrder(int id,String worker_ipv4,String order) {
		if (this.worker_beingattended == 99 || this.worker_beingattended == id) {
			if (this.worker_beingattended == 99) {
				this.worker_beingattended = id;
				this.wba_haschanged = true;
				this.starting_time = System.currentTimeMillis();
				this.current_time = 0;
				this.is_answering = true;
			}
			
			
			ServerInterface.answerWorker(this,id,order,worker_ipv4);
			
		}
		else {
			System.out.println(" ignored");
		}
		System.out.print("\n");
		
	}
	
	public Server(Worker[] workers, String IPv4) {
		this.workers = workers;
		this.IPv4 = IPv4;
		this.program_hasended = false;
		this.worker_beingattended = 99;
		this.wba_haschanged = false;
		this.maximum_time = 2_000;
		this.current_time = 0;
		this.starting_time = System.nanoTime();
		this.is_answering = false;
	}
	
	
	public void run() {
		while (!program_hasended) {
			
			if (is_answering) {
				this.current_time = System.currentTimeMillis() - this.starting_time;
			}
			try {
				Thread.sleep(10);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
	}
}
