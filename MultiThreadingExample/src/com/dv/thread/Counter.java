package com.dv.thread;

import org.apache.log4j.Logger;

public class Counter implements Runnable {
	
	public static final Logger logger = Logger.getLogger(Counter.class);
	
	private Count countValue;
	
	public Counter(){
		countValue = new Count();
	}

	@Override
	public void run() {
		countValue.setCount(countValue.getCount()+1);
		//System.out.println("Count value incremented to: "+countValue.getCount());
		logger.debug("Count value incremented to: "+countValue.getCount());
	}

}
