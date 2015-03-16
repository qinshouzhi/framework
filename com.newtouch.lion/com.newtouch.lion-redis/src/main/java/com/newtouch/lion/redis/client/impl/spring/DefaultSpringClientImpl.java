package com.newtouch.lion.redis.client.impl.spring;

import java.util.Collection;
import java.util.concurrent.CountDownLatch;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.newtouch.lion.redis.config.spring.ConfigManager4Spring;
import com.newtouch.lion.redis.shard.ShardHandler;
import com.newtouch.lion.redis.shard.Sharded4Jedis;

/**
 * 默认的Spring Client实现
 *
 * 
 */
public class DefaultSpringClientImpl {
	/**
	 * 字符串常量
	 */
	public static final String UNSUPPORT = "Current configuration does not support this operation";
	/**
	 * 日志
	 */
	private static Logger logger = LoggerFactory
			.getLogger(DefaultSpringClientImpl.class);
	/**
	 * 分片
	 */
	protected Sharded4Jedis sharded4Jedis;

	/**
	 * 配置管理器
	 */
	@Autowired
	protected ConfigManager4Spring configManager4Spring;

	/**
	 * 返回配置管理器
	 * 
	 * @return 配置管理器
	 */
	public ConfigManager4Spring getConfigManager4Spring() {
		return configManager4Spring;
	}

	/**
	 * 构造方法
	 * 
	 * @param configManager4Spring
	 *            spring配置
	 */
	public void setConfigManager4Spring(
			ConfigManager4Spring configManager4Spring) {
		this.configManager4Spring = configManager4Spring;
	}

	/**
	 * 构造方法
	 */
	public DefaultSpringClientImpl() {

	}

	/**
	 * 判断是否是分片
	 * 
	 * @return 判断结果
	 */
	protected boolean isSharding() {
		return configManager4Spring.isSharding();
	}

	/**
	 * 功能描述：初始化
	 */
	protected synchronized void init() {
		/** 加载配置 */
		configManager4Spring.loadConfig();
		sharded4Jedis = new Sharded4Jedis(
				configManager4Spring.getLstInfo4Jedis());
	}

	/**
	 * 刷新配置
	 * 
	 * @param config
	 *            配置信息
	 */
	public synchronized void refresh(String config) {
		Sharded4Jedis old = sharded4Jedis;
		if (!StringUtils.isBlank(config)) {
			configManager4Spring.setConfig(config);
		}
		this.init();
		// 销毁旧池
		Collection<ShardHandler> allShards = old.getAllShards();
		for (ShardHandler shardHandler : allShards) {
			shardHandler.destroy();
		}
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 刷新所有数据
	 * 
	 * @return 返回刷新结果
	 */
	public String flushDB() {
		Collection<ShardHandler> allShards = sharded4Jedis.getAllShards();
		final CountDownLatch endSignal = new CountDownLatch(allShards.size());
		for (final ShardHandler shard : allShards) {
			// 多线程同时flushDB 提高效率
			new Thread(new Runnable() {
				@Override
				public void run() {
					shard.flushShard();
					endSignal.countDown();
				}
			}).start();
		}
		try {
			endSignal.await();
		} catch (InterruptedException e) {
			logger.error(e.getMessage());
		}
		return "OK";
	}

	/**
	 * 
	 * 功能描述: <br>
	 * 清除节点不可用的标记
	 * 
	 */
	public void clearDisableFlags() {
		// 所有shard中所有连接池
		Collection<ShardHandler> allShards = sharded4Jedis.getAllShards();
		for (ShardHandler shard : allShards) {
			shard.clearDisableFlags();
		}
	}
}
