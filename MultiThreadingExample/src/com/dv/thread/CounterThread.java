package com.dv.thread;

import org.apache.log4j.Logger;

public class CounterThread extends Thread {
	
	public static final Logger logger = Logger.getLogger(CounterThread.class);
	
	String name=null;
	
	public CounterThread(Runnable run,String name){
		super(run,name);
		this.name=name;
	}
	
	@Override
	public void start(){
		super.start();
		//System.out.println(this.name);
		logger.debug(this.name);
	}

}
