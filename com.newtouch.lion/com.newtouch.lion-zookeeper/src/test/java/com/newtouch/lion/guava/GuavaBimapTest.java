/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: GuavaBimapTest.java 9552 2015年11月5日 下午4:39:45 WangLijun$
*/
package com.newtouch.lion.guava; 

import org.junit.Test;

import com.google.common.collect.BiMap;
import com.google.common.collect.HashBiMap;

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
public class GuavaBimapTest {
	
	/***
	 * 
	 * 　BiMap提供了一种新的集合类型，它提供了key和value的双向关联的数据结构。
	 */
	@Test
	public void inverse(){
		BiMap<Integer,String> logfileMap = HashBiMap.create(); 
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log"); 
        System.out.println("logfileMap:"+logfileMap); 
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
	}
	
	
	/***
	 * Bimap数据的强制唯一性Bimap数据的强制唯一性 对Value要求是唯一的
	 * 在使用BiMap时，会要求Value的唯一性。如果value重复了则会抛出错误：java.lang.IllegalArgumentException，例如：
	 */
	@Test
    public void BimapTest1(){
        BiMap<Integer,String> logfileMap = HashBiMap.create(); 
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log");         
        logfileMap.put(4,"d.log"); 
        logfileMap.put(5,"d.log"); 
    }
	/***
	 * 理解inverse方法
　　	 * inverse方法会返回一个反转的BiMap，但是注意这个反转的map不是新的map对象，
	 * 它实现了一种视图关联，这样你对于反转后的map的所有操作都会影响原先的map对象。例如：
	 */
	@Test
    public void BimapTest(){
        BiMap<Integer,String> logfileMap = HashBiMap.create(); 
        logfileMap.put(1,"a.log");
        logfileMap.put(2,"b.log");
        logfileMap.put(3,"c.log"); 
        System.out.println("logfileMap:"+logfileMap); 
        BiMap<String,Integer> filelogMap = logfileMap.inverse();
        System.out.println("filelogMap:"+filelogMap);
        
        logfileMap.put(4,"d.log"); 

        System.out.println("logfileMap:"+logfileMap); 
        System.out.println("filelogMap:"+filelogMap); 
    }
	/***
	 * 　logfileMap.put(5,"d.log") 会抛出java.lang.IllegalArgumentException: value already present: d.log的错误。
	 * 如果我们确实需要插入重复的value值，那可以选择forcePut方法。但是我们需要注意的是前面的key也会被覆盖了。
	 */
	@Test
	public void BimapforcePut(){
		  BiMap<Integer,String> logfileMap = HashBiMap.create(); 
          logfileMap.put(1,"a.log");
          logfileMap.put(2,"b.log");
          logfileMap.put(3,"c.log");
          logfileMap.put(4,"d.log"); 
          logfileMap.forcePut(5,"d.log"); 
          System.out.println("logfileMap:"+logfileMap); 
	}
}

	