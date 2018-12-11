package com.nfswsa.dataserver;

public class ServerInterface {

	public static void answerWorker(Server server,int id,String order,String worker_ipv4) {
		System.out.println(" not ignored");
		//System.out.printf("The worker %d is being asnwered right now!\n",id);
		System.out.println("The order is: "+order);
	}
}
