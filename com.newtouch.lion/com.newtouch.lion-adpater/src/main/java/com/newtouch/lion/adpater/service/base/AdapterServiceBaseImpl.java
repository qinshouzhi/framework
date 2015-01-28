 
package com.newtouch.lion.adpater.service.base;

import java.util.LinkedHashMap;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.exception.ErrorCode;
import com.newtouch.lion.adpater.formatter.Formatter;
import com.newtouch.lion.adpater.formatter.base.FormatterBaseXML;
import com.newtouch.lion.adpater.service.AbstractAdapterService;
import com.newtouch.lion.adpater.util.ReflectionUtil;

/**
 *  BMS Service 基础服务类<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AdapterServiceBaseImpl extends AbstractAdapterService{
    /**请求体报文参数*/
    private static final String _BEAN_REF_STR = "com.newtouch.lion.adpater.command.base.CommandRequest";
    /**解析类类初始化路径*/
    private static final String _XMLOPRATE_REF_STR = "com.newtouch.lion.adpater.formatter.base.FormatterBaseXML";
    
    /**
     * {@inheritDoc}
     */
    @Override
    protected AdCommand normalize(String stransno, LinkedHashMap<Object, Object> params) throws AdapterException {
        Object list[] = new Object[params.size()];
        int i = 0;
        for (Object value : params.values()) {
            list[i] = value;
            i += 1;
        }
        AdCommand cmd = null;
        try {
            cmd = (AdCommand) ReflectionUtil.newInstance(_BEAN_REF_STR+ stransno, list);
        } catch (Exception e) {
            throw new AdapterException(ErrorCode.CLASS_NOTFOUND,e);
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AdCommand normalize(String stransno) throws AdapterException {
        AdCommand cmd = null;
        try {
            cmd = (AdCommand) ReflectionUtil.newInstance(_BEAN_REF_STR
                    + stransno, new String[0]);
        } catch (Exception e) {
            throw new AdapterException(ErrorCode.CLASS_NOTFOUND,e);
        }
        return cmd;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected AdCommand normalize(AdCommand command) throws AdapterException {
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    protected Formatter getFormatter(String className, Object[] args) throws AdapterException {
        Formatter formatter = null;
        try {
            formatter = (FormatterBaseXML) ReflectionUtil.newInstance(_XMLOPRATE_REF_STR + className, new String[0]);
        } catch (Exception e) {
            throw new AdapterException(ErrorCode.CLASS_NOTFOUND_FORAMTTER,e);
        }
        return formatter;
    }
    
}   
