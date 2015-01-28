package com.newtouch.lion.adpater.exception;

/**
 * 〈一句话功能简述〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface Code<T,K> {
    
    /***
     * 
     * 功能描述: 获取CODE代码E
     * 根据CODE代码的信息
     *
     * @return T
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public T code();
    /***
     * 
     * 功能描述:获取CODE对应的描述消息
     *
     * @return K
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public K message();
}

