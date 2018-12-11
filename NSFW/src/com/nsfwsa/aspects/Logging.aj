package com.nsfwsa.aspects;

import java.io.Writer;
import java.text.SimpleDateFormat;

import com.nfswsa.worker.Worker;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Calendar;
import java.util.Date;

public aspect Logging {

	pointcut makingOrder(String order): call(void Worker.makeOrder(String)) && args(order);
	
	after(): makingOrder(String){
		
		Writer output;
		try {
			output = new BufferedWriter(new FileWriter("log.txt",true));
			String timestamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
			String exit = "Order: "+(String)thisJoinPoint.getArgs()[0]+ " | Timestamp: " + timestamp+ System.getProperty("line.separator");
			output.append(exit);
			output.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
}
