/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: WebSocketServerHandler.java 9552 2015年4月15日 下午10:51:55 WangLijun$
*/
package com.newtouch.lion.websocket; 

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpRequest;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;
import io.netty.handler.codec.http.websocketx.CloseWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PingWebSocketFrame;
import io.netty.handler.codec.http.websocketx.PongWebSocketFrame;
import io.netty.handler.codec.http.websocketx.TextWebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketFrame;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshaker;
import io.netty.handler.codec.http.websocketx.WebSocketServerHandshakerFactory;
import io.netty.util.CharsetUtil;

import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.newtouch.lion.common.date.DateUtil;

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
public class WebSocketServerHandler extends SimpleChannelInboundHandler<Object> {
	
	/**日志*/
	private static final Logger logger=LoggerFactory.getLogger(WebSocketServerHandler.class);
	/**websocket名称*/
	private static final String WEBSOCKET="websocket";
	/**Upgrade*/
	private static final String UPGARDE="Upgrade";
	/**HTTP OK status 200*/
	private static final int HTTP_OK=200;
	/**WebSocket请求路径*/
	public static final String WEBSOCKET_URL="ws://localhost:8080/websocket/";
	
	private WebSocketServerHandshaker handshaker;

	@Override
	protected void channelRead0(ChannelHandlerContext context, Object message)
			throws Exception {
		//传统的HTTP接入
		if(message instanceof FullHttpRequest){
			this.handleHttpRequest(context, (FullHttpRequest) message);
		}else if(message instanceof WebSocketFrame){
			//判断 WebSocket接入方式
			this.handleWebSocketFrame(context,(WebSocketFrame) message);
		}else{
			//其它方式接入
			logger.warn("websocket other channel",message);
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
		ctx.flush();
	}
	/***
	 * 处理WebSocket接入请求
	 * @param context
	 * @param message
	 */
	private void handleWebSocketFrame(ChannelHandlerContext context, WebSocketFrame frame) {
		//判断是否是关闭连路的指令
		if(frame instanceof CloseWebSocketFrame){
			this.handshaker.close(context.channel(), (CloseWebSocketFrame) frame.retain());
			return;
		}
		
		//判断是否是ping消息
		if(frame instanceof PingWebSocketFrame){
			context.write(new PongWebSocketFrame(frame.content().retain()));
			return;
		}
		
		//文本消息处理
		if(frame instanceof TextWebSocketFrame){
			String  requestText=((TextWebSocketFrame)frame).text();
			logger.info("{} received {}",context.channel(),requestText);
			String requestStr=requestText+",Netty Websocket 服务"+DateUtil.formatDate(new Date(), DateUtil.FORMAT_DATETIME_YYYY_MM_DD_HH_MM_SS_SSS);
			context.channel().write(new TextWebSocketFrame(requestStr));
		}
		//异常处理
		throw new UnsupportedOperationException(String.format("%s frame types not supported",frame.getClass().getName()));
		
	}

	//处理非WebSocket方式接入
	private void handleHttpRequest(ChannelHandlerContext context,FullHttpRequest request){
		if((!request.getDecoderResult().isSuccess())||(!WEBSOCKET.equals(request.headers().get(UPGARDE)))){
			this.sendHttpResponse(context, request,new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,HttpResponseStatus.BAD_REQUEST));
			return;
		}
		
		//构造握手响应返回，本机测试
		WebSocketServerHandshakerFactory wsFactory=new WebSocketServerHandshakerFactory(WEBSOCKET_URL,null,false);
		handshaker=wsFactory.newHandshaker(request);
		if(this.handshaker==null){
			WebSocketServerHandshakerFactory.sendUnsupportedVersionResponse(context.channel());
		}else{
			this.handshaker.handshake(context.channel(),request);
		}
	}
	
	//发送响应
	public void sendHttpResponse(ChannelHandlerContext context,FullHttpRequest request,FullHttpResponse response){
		//返回应答给客户端
		if(response.getStatus().code()!=HTTP_OK){
			ByteBuf byteBuf=Unpooled.copiedBuffer(response.getStatus().toString(),CharsetUtil.UTF_8);
		    response.content().writeBytes(byteBuf);
		    byteBuf.release();
		    setConentLength(response,response.content().readableBytes());
		}
		
		//如果是非keep-Alive,关闭连接
		ChannelFuture future=context.channel().writeAndFlush(response);
		if(!isKeepAlive(request)||response.getStatus().code()!=200){
			future.addListener(ChannelFutureListener.CLOSE);
		}
	}
	private static boolean isKeepAlive(FullHttpRequest request) {
 
		return false;
	}

	//设置响应内容长度
	private static void setConentLength(FullHttpResponse response,
			int readableBytes) {
		response.content().setLong(0, readableBytes);
	}

	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause)
			throws Exception {
		logger.error(cause.getMessage(),cause);
		ctx.close();
	}
}

	