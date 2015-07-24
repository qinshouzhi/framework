/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: Subscriber.java 9552 2015年7月24日 上午12:40:23 WangLijun$
*/
package com.newtouch.watchman.zookeeper; 

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.common.base.Stopwatch;
import com.newtouch.lion.zookeeper.model.Subscriber;

/**
 * <p>
 * Title: 
 * </p>
 * <p>
 * Description: 
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class SubscriberTest {

	private static final Logger logger=LoggerFactory.getLogger(SubscriberTest.class);
	
	
	public static void main(String[] args) throws InterruptedException {
		List<String> serverList = new ArrayList< String >();
		serverList.add( "127.0.0.1:2181" );
		//serverList.add( "10.232.37.128:2181");
		//serverList.add( "10.232.37.126:2181");
		Stopwatch stopwatch=new Stopwatch();
		stopwatch.start();
		for( String server : serverList ){
			
			Subscriber sub = null;
			try {
				sub = new Subscriber( server, "/wanglijun/test","zookeeper",1,1);
				 
				if( !sub.checkAlive() ){			 
					logger.info( server + " error" );
				 
				}else{
					logger.info( server + " alive" );
				}
				Thread.sleep( 1000 * 5 );
			} catch ( Exception e ) {
				logger.error( server + "" + e.getMessage() );
			}finally{
				if( null != sub )
					sub.close();
			}
		}
		stopwatch.stop();
		System.out.println(stopwatch.elapsed(TimeUnit.NANOSECONDS));
		Thread.sleep( 5000 );
	}
	 
}

	