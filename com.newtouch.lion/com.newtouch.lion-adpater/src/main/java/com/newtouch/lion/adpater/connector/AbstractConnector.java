package com.newtouch.lion.adpater.connector;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * 接口抽像连接类<br>
 * 接口抽像连接类
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class AbstractConnector implements Connector {
    /** 日志类 */
    protected final Logger logger = LoggerFactory.getLogger(super.getClass());
    /**连接地址*/
    protected  String  addresss;
    /**
     * @return 连接地址
     */
    public String getAddresss() {
        return addresss;
    }
    /**
     * @param addresss 连接地址
     */
    public void setAddresss(String addresss) {
        this.addresss = addresss;
    }
}
