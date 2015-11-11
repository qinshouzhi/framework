/*
 * Copyright (c)  2015, Newtouch
 * All rights reserved. 
 *
 * $id: SSHHelper.java 9552 2015年5月27日 上午9:16:37 WangLijun$
 */
package com.newtouch.lion.os.ssh;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.JSchException;
import com.jcraft.jsch.Session;
import com.newtouch.lion.os.constant.Command;

/**
 * <p>
 * Title: Linux 连接工具
 * </p>
 * <p>
 * Description: Linux 连接工具
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
public class JschHelper {
	/** 日志 */
	private static final Logger logger = LoggerFactory.getLogger(JschHelper.class);
	/**换行*/
	private static final String LINE_ENTER="\r\n";

	/***
	 * 
	 * @param config
	 * @return
	 */
	public static String exec(JschConfig config, String command) {
		return JschHelper.exec(config.getIp(), config.getUsername(),config.getPassword(), config.getPort(), command);
	}

	/**
	 * 远程 执行命令并返回结果调用过程 是同步的（执行完才会返回）
	 * 
	 * @param host
	 *            主机名
	 * @param username
	 *            用户名
	 * @param password
	 *            密码
	 * @param port
	 *            端口
	 * @param command
	 *            命令
	 * @return
	 */
	public static String exec(String host, String username, String password,
			int port, String command) {
		StringBuilder resultBuilder =new StringBuilder();
		Session session = null;
		ChannelExec openChannel = null;
		try {
			JSch jsch = new JSch();
			session = jsch.getSession(username, host, port);
			java.util.Properties config = new java.util.Properties();
			config.put("StrictHostKeyChecking", "no");
			session.setConfig(config);
			session.setPassword(password);
			session.connect();
			openChannel = (ChannelExec) session.openChannel(Command.EXEC);
			openChannel.setCommand(command);
			int exitStatus = openChannel.getExitStatus();
			logger.info("exitStatus:{}", exitStatus);
			openChannel.connect();
			InputStream in = openChannel.getInputStream();
			BufferedReader reader = new BufferedReader(
					new InputStreamReader(in));
			String buffer= null;
			while ((buffer = reader.readLine()) != null) {
				resultBuilder.append(buffer);
				resultBuilder.append(LINE_ENTER);
			}
		} catch (JSchException | IOException e) {
			 	resultBuilder.append(e.getMessage());
		} finally {
			if (openChannel != null && !openChannel.isClosed()) {
				openChannel.disconnect();
			}
			if (session != null && session.isConnected()) {
				session.disconnect();
			}
		}
		return resultBuilder.toString();
	}
	

}
