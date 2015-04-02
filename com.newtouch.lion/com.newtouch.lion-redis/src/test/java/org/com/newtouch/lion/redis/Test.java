package org.com.newtouch.lion.redis;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import com.newtouch.lion.redis.base.RedisClientTemplate;

public class Test {
	public static void main(String[] args) {
        ApplicationContext ac =  new ClassPathXmlApplicationContext("classpath:/data-source.xml");
        RedisClientTemplate redisClient = (RedisClientTemplate)ac.getBean("redisClientTemplate");
        //redisClient.set("c", "abc");
        //redisClient.set("d", "中国");
        System.out.println(redisClient.get("channel"));
       // pool.returnResource(jedis); 
        
	}
}
