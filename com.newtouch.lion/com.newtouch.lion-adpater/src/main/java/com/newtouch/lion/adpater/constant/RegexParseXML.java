
package com.newtouch.lion.adpater.constant;

/**
 *  XML 解析报文表达式<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun

 * @since [产品/模块版本] （可选）
 */
public final class RegexParseXML {
    /***
     * 正则表过式：获取返回报文体的的包括 <return></return> 
     */
    public static final String REGEX_RETURN_XML="<return>([\\s\\S]*)</return>";
    /**
     * 用于解析：响应报文中<systemReturn><return>AAA</return></systemReturn> 解析结果:AAA
     * */
    public static final String REGEX_SYS_RETURN_VAL="<return>([^</return>]*)";
    
    /**用于解析结果：<result>中间的XML</result>  解析结果为：<result>中间的结果XML</result>*/
    public static final String REGEX_RESULT_XML="<result>([\\s\\S]*)</result>";
}
