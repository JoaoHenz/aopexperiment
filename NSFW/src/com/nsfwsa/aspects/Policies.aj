package com.nsfwsa.aspects;

import com.nfswsa.dataserver.Server;
import com.nfswsa.dataserver.ServerInterface;



public aspect Policies {
	
	pointcut answeringworker(): call(void ServerInterface.answerWorker(Server,int ,String ,String));
	
	void around(Server server): answeringworker() && this(server){
		if(server.current_time>server.maximum_time) {
			server.worker_beingattended = 99;
			server.is_answering = false;
			System.out.printf(" Maximum time reached! %f \n",(server.current_time/1_000f));
		}
		else{
			proceed(server);
		}
	}


}
