/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: WebSocketServer.java 9552 2015年4月15日 下午10:19:54 WangLijun$
*/
package com.newtouch.lion.websocket; 

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.stream.ChunkedWriteHandler;
 

/**
 * <p>
 * Title: WebSocketServer服务器类
 * </p>
 * <p>
 * Description: WebSocketServer服务器类
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
public class WebSocketServer {
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(WebSocketServer.class);
	/**http-codec*/
	private static final String HTTP_CODEC="http-codec";
	/**aggregator*/
	private static final String AGGREGATOR="aggregator";
	/**Handler*/
	private static final String HANDLER="handler";
	/**http-chunked*/
	private static final String HTTP_CHUNKED="http-chunked";
	
	
	public void run(int port){
		EventLoopGroup bossGroup=new NioEventLoopGroup();
		EventLoopGroup workerGroup=new NioEventLoopGroup();
		
		ServerBootstrap bootstrap=new ServerBootstrap();
		bootstrap.group(bossGroup, workerGroup);
		bootstrap.channel(NioServerSocketChannel.class);
		bootstrap.childHandler(new WebSocketChannelInitializer());
		
		//启动
		try {
			Channel channel=bootstrap.bind(port).sync().channel();
			logger.info("WebSocket Server started at port:{}. Open your brower and navigate to http://localhost:{}/",port,port);
			channel.closeFuture().sync();
		} catch (InterruptedException e) {
			 logger.error("",e);
		}finally{
			bossGroup.shutdownGracefully();
			workerGroup.shutdownGracefully();
		}
	}
	
	public class WebSocketChannelInitializer extends  ChannelInitializer<SocketChannel>{
		
	 
		
		@Override
		protected void initChannel(SocketChannel socketChannel) throws Exception {
			ChannelPipeline pipeline=socketChannel.pipeline();
			pipeline.addLast(HTTP_CODEC,new HttpServerCodec());
			pipeline.addLast(AGGREGATOR,new HttpObjectAggregator(65536));
			pipeline.addLast(HTTP_CHUNKED,new ChunkedWriteHandler());
			pipeline.addLast(HANDLER,new WebSocketServerHandler());
		}
	}
	
	
	public static void main(String[] args) {
		int port=8081;
		new WebSocketServer().run(port);
	}
}

	