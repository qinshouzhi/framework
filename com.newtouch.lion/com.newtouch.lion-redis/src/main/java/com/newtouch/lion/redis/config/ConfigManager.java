package com.newtouch.lion.redis.config;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import redis.clients.jedis.JedisPoolConfig;

import com.newtouch.lion.redis.exception.RedisClientException;
import com.newtouch.lion.redis.shard.NodeInfo4Jedis;
import com.newtouch.lion.redis.shard.ShardInfo4Jedis;
import com.newtouch.lion.redis.util.ResourceUtils;

/**
 * 
 * 
 * 功能描述： 配置信息管理类
 * 
 * @version 1.0.0
 */
public class ConfigManager {

    /**
     * 重试次数
     */
    public static final int RETRYTIMES = 3;

    /**
     * 默认ip
     */
    public static final String DEFAULT_HOST = "127.0.0.1";
    /**
     * 默认端口
     */
    public static final String DEFAULT_PORT = "6379";
    /**
     * 默认过期时间
     */
    public static final String DEFAULT_TIMEOUT_IN_MILL_SECONDS = "2000";
    /**
     * 默认索引初始值
     */
    public static final String DEFAULT_DB_INDEX = "0";

    /**
     * 默认配置文件
     */
    private static final String CONFIG = "caches.xml";

    /**
     * logger
     */
    private static Logger logger = LoggerFactory.getLogger(ConfigManager.class);

    /**
     * lstInfo4Jedis
     */
    protected List<ShardInfo4Jedis> lstInfo4Jedis;

    /**
     * config
     */
    private String config = CONFIG;

    /**
     * isSharding
     */
    private boolean isSharding;

    /**
     * 
     * 
     * ConfigManager constructor
     */
    public ConfigManager() {
    }

    /**
     * 构造方法
     * 
     * @param config 配置
     */
    public ConfigManager(String config) {
        this.config = config;
    }

    /**
     * getLstInfo4Jedis
     * 
     * @return List<ShardInfo4Jedis> 分片的Redis节点信息列表
     */
    public List<ShardInfo4Jedis> getLstInfo4Jedis() {
        return lstInfo4Jedis;
    }

    /**
     * 
     * 功能描述：是否配置分片
     * 
     * @return boolean返回值
     */
    public boolean isSharding() {
        return isSharding;
    }

    /**
     * 
     * 功能描述： 加载配置文件
     * 
     */
    public synchronized void loadConfig() {
        InputStream inputStream = null;
        try {
            inputStream = ResourceUtils.getResourceAsStream(config);
            Document doc = new SAXReader().read(inputStream);
            // 解析配置文件
            parserWithDoc(doc);
        } catch (IOException e) {
            logger.error("IOException!", e);
            throw new RedisClientException("IOException!", e);
        } catch (DocumentException e) {
            logger.error("SAXReader parse cache xml error!", e);
            throw new RedisClientException("SAXReader parse cache xml error!", e);
        } finally {
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e) {
                    logger.error(e.getMessage());
                }
            }
        }

    }

    /**
     * 
     * 功能描述：设置配置文件
     * 
     * @param cfg 参数说明 返回值: 类型 <说明>
     */
    public synchronized void setConfig(String cfg) {
        this.config = cfg;
    }

    /**
     * 
     * 功能描述：解析配置文件
     * 
     * @param serverElement 参数说明 返回值: 类型 <说明>
     * @param poolConfig 参数说明 返回值: 类型 <说明>
     */
    @SuppressWarnings("unchecked")
    private void parseShardingConfig(Element serverElement, JedisPoolConfig poolConfig) {
        lstInfo4Jedis = new ArrayList<ShardInfo4Jedis>();
        List<Element> shards = serverElement.element("shardConfig").elements("shard");
        for (Element shardElement : shards) {
            String shardName = shardElement.attributeValue("name").trim();
            List<Element> servers = shardElement.elements("server");
            if (servers.size() > 2) {
                throw new RedisClientException("Configuration error,no more than 2 servers each shard");
            }
            Set<NodeInfo4Jedis> nodes = new HashSet<NodeInfo4Jedis>();
            for (Element element : servers) {
                String ip = element.elementTextTrim("ip");
                String port = element.elementTextTrim("port");
                String password = element.elementTextTrim("password");
                String dbIndex = element.elementTextTrim("dbIndex");
                String timeOut = element.elementTextTrim("timeOut");
                if (StringUtils.isBlank(ip)) {
                    // 默认ip
                    ip = DEFAULT_HOST;
                }
                if (StringUtils.isBlank(port)) {
                    // 默认端口
                    port = DEFAULT_PORT;
                }
                if (StringUtils.isBlank(dbIndex)) {
                    // 默认dbIndex
                    dbIndex = DEFAULT_DB_INDEX;
                }
                if (StringUtils.isBlank(timeOut)) {
                    // 默认操作超时时间
                    timeOut = DEFAULT_TIMEOUT_IN_MILL_SECONDS;
                }
                NodeInfo4Jedis nodeInfo4Jedis = new NodeInfo4Jedis(ip, Integer.valueOf(port), password,
                        Integer.valueOf(dbIndex), Integer.valueOf(timeOut), poolConfig);
                nodes.add(nodeInfo4Jedis);
            }
            lstInfo4Jedis.add(new ShardInfo4Jedis(shardName, nodes));
        }
        isSharding = lstInfo4Jedis.size() > 1 ? true : false;
    }

    /**
     * 
     * 功能描述： parserWithDoc
     * 
     * @param doc 参数说明 返回值: 类型 <说明>
     */
    private void parserWithDoc(Document doc) {
        Element serverElement = doc.getRootElement();
        JedisPoolConfig poolConfig = this.parsePoolConfig(serverElement);
        parseShardingConfig(serverElement, poolConfig);
    }

    /**
     * 
     * 功能描述： parsePoolConfig
     * 
     * @param serverElement 参数说明 返回值: 类型 <说明>
     * @return Config 返回值
     */
    private JedisPoolConfig parsePoolConfig(Element serverElement) {
    	JedisPoolConfig config = new JedisPoolConfig();
        config.setMaxWaitMillis(2000L);
        Element poolConfigTag = serverElement.element("poolConfig");
        if (poolConfigTag != null) {
            String maxIdle = poolConfigTag.elementTextTrim("maxIdle");
            String minIdle = poolConfigTag.elementTextTrim("minIdle");
            String maxActive = poolConfigTag.elementTextTrim("maxActive");
            String maxWait = poolConfigTag.elementTextTrim("maxWait");
            String whenExhaustedAction = poolConfigTag.elementTextTrim("whenExhaustedAction");
            String testOnBorrow = poolConfigTag.elementTextTrim("testOnBorrow");
            String testOnReturn = poolConfigTag.elementTextTrim("testOnReturn");
            String testWhileIdle = poolConfigTag.elementTextTrim("testWhileIdle");
            String timeBetweenEvictionRunsMillis = poolConfigTag.elementTextTrim("timeBetweenEvictionRunsMillis");
            String numTestsPerEvictionRun = poolConfigTag.elementTextTrim("numTestsPerEvictionRun");
            String minEvictableIdleTimeMillis = poolConfigTag.elementTextTrim("minEvictableIdleTimeMillis");
            String softMinEvictableIdleTimeMillis = poolConfigTag.elementTextTrim("softMinEvictableIdleTimeMillis");
            String lifo = poolConfigTag.elementTextTrim("lifo");
            if (!StringUtils.isBlank(maxIdle)) {
                config.setMaxIdle(Integer.parseInt(maxIdle));
            }
            if (!StringUtils.isBlank(minIdle)) {
                config.setMinIdle(Integer.parseInt(minIdle));
            }
            if (!StringUtils.isBlank(maxActive)) {
                
            }
            if (!StringUtils.isBlank(maxWait)) {
                config.setMaxWaitMillis( Long.valueOf(maxWait));
            }
            if (!StringUtils.isBlank(lifo)) {
                config.setLifo(Boolean.valueOf(lifo));
            }
            setTestParameters(config, whenExhaustedAction, testOnBorrow, testOnReturn, testWhileIdle,
                    timeBetweenEvictionRunsMillis, numTestsPerEvictionRun, minEvictableIdleTimeMillis,
                    softMinEvictableIdleTimeMillis);
        }
        return config;
    }

    /**
     * 功能描述：验证连接可用性的参数设置
     * 
     * @param config 参数说明 返回值: 类型 <说明>
     * @param whenExhaustedAction 参数说明 返回值: 类型 <说明>
     * @param testOnBorrow 参数说明 返回值: 类型 <说明>
     * @param testOnReturn 参数说明 返回值: 类型 <说明>
     * @param testWhileIdle 参数说明 返回值: 类型 <说明>
     * @param timeBetweenEvictionRunsMillis 参数说明 返回值: 类型 <说明>
     * @param numTestsPerEvictionRun 参数说明 返回值: 类型 <说明>
     * @param minEvictableIdleTimeMillis 参数说明 返回值: 类型 <说明>
     * @param softMinEvictableIdleTimeMillis 参数说明 返回值: 类型 <说明>
     */
    private void setTestParameters(JedisPoolConfig  config, String whenExhaustedAction, String testOnBorrow, String testOnReturn,
            String testWhileIdle, String timeBetweenEvictionRunsMillis, String numTestsPerEvictionRun,
            String minEvictableIdleTimeMillis, String softMinEvictableIdleTimeMillis) {

        if (!StringUtils.isBlank(testOnBorrow)) {
            // 获取连接池是否检测可用性
            config.setTestOnBorrow(Boolean.valueOf(testOnBorrow));
        }

        if (!StringUtils.isBlank(testOnReturn)) {
            // 归还时是否检测可用性
            config.setTestOnReturn(Boolean.valueOf(testOnReturn));
        }
        if (!StringUtils.isBlank(testWhileIdle)) {
            // 空闲时是否检测可用性
            config.setTestWhileIdle(Boolean.valueOf(testWhileIdle));
        } else {
        	 config.setTestWhileIdle(true);
        }
        if (!StringUtils.isBlank(whenExhaustedAction)) {
            config.setBlockWhenExhausted(Boolean.valueOf(whenExhaustedAction));
        }
        if (!StringUtils.isBlank(timeBetweenEvictionRunsMillis)) {
 
            config.setTimeBetweenEvictionRunsMillis(Long.valueOf(timeBetweenEvictionRunsMillis));
        } else {
            config.setTimeBetweenEvictionRunsMillis(30000L);
        }
        if (!StringUtils.isBlank(numTestsPerEvictionRun)) {
        
            config.setNumTestsPerEvictionRun(Integer.valueOf(numTestsPerEvictionRun));
        } else {
        	 config.setNumTestsPerEvictionRun(-1);
        }
        if (!StringUtils.isBlank(minEvictableIdleTimeMillis)) {
            
            config.setMinEvictableIdleTimeMillis(Integer.valueOf(minEvictableIdleTimeMillis));
        } else {
        	  config.setMinEvictableIdleTimeMillis(60000);
        }
        if (!StringUtils.isBlank(softMinEvictableIdleTimeMillis)) {
            config.setSoftMinEvictableIdleTimeMillis( Integer.valueOf(softMinEvictableIdleTimeMillis));
        }
    }
}
