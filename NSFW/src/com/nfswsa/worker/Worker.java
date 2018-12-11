package com.nfswsa.worker;

import com.nfswsa.dataserver.Server;
public class Worker extends Thread{
	
	int id;
	String name;
	int number;
	String IPv4;
	public Server server;
	public boolean must_sendorder;
	public boolean program_hasended;
	char[][] map;
	String order;
	
	public Worker(String name, String IPv4,int id) {
		this.name = name;
		this.IPv4 = IPv4;
		this.must_sendorder = false;
		this.program_hasended = false;
		this.id = id;
		this.order = null;
	}
	
	public void makeOrder(String order) {
		this.must_sendorder = true;
		this.order = order;
	}
	
	public void run() {
		while (!program_hasended) {
			
			if (must_sendorder) {
				System.out.print(this.name+" is sending a order to the server!");
				this.server.receiveOrder(this.id,this.IPv4,this.order);
				this.must_sendorder = false;
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
