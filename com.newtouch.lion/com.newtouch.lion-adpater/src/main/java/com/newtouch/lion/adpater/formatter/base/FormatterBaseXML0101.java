
package com.newtouch.lion.adpater.formatter.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import org.springframework.util.StringUtils;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.command.base.CommandResponse0101;
import com.newtouch.lion.adpater.constant.TransResult;
import com.newtouch.lion.adpater.constant.Transaction;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.util.XMLUtil;

/**
 * 用户注册请求KEY<br> 
 * 用户注册请求KEY
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class FormatterBaseXML0101  extends  FormatterBaseXML{
	
 

    @Override
    public Object formateRequest(AdCommand command) throws AdapterException {
		String requestXML=XMLUtil.toXml(command);;
		return this.loadXml(requestXML);
    }
    
    @Override
    public AdapterCommand formateResponse(Object receiveObj) throws AdapterException {
        return this.parseXml(receiveObj.toString(),CommandResponse0101.class);
    }
    
    @Override
	public AdapterCommand parseXml(String responseXML, Class<?> clazz) {
    	
    	logger.info("responseXML:{}",responseXML);
		// 获取return根节点中的内容，不包含return节点本身
		String returnXMLContent = this.parseReturnContent(responseXML);
		List<AdCommand> list = new LinkedList<AdCommand>();
		Map<String, String> param = new HashMap<String, String>();
		if (!StringUtils.isEmpty(returnXMLContent)) {
			param.put(Transaction.SANSNO.code(), TransResult.SUCCESS_NO);
			AdCommand command = (AdCommand) XMLUtil.toBean(returnXMLContent, clazz);
			list.add(command);
			this.adapterCommand.setParam(param);
			this.adapterCommand.setList(list);
		} else {
			/** 失败 */
			param.put(Transaction.SANSNO.code(), TransResult.FAIL_NO);
			this.adapterCommand.setParam(param);
			this.adapterCommand.setList(list);
		}
		return adapterCommand;
	}
    
}   
