
package com.newtouch.lion.adpater.formatter.base;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.constant.RegexParseXML;
import com.newtouch.lion.adpater.constant.TransResult;
import com.newtouch.lion.adpater.constant.Transaction;
import com.newtouch.lion.adpater.formatter.Formatter;
import com.newtouch.lion.adpater.util.XMLUtil;

/**
 * 解析类 <br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public abstract class FormatterBaseXML extends Formatter {
    /**
     * 用于解析报文中的值
     * */
    protected final Pattern PATTERN_RETURN_XML = Pattern.compile(RegexParseXML.REGEX_RETURN_XML);
    /**
     * 用于解析<systemReturn><return>value<return></systemReturn>
     * */
    protected final Pattern PATTERN_SYS_RETRUN_VALUE = Pattern.compile(RegexParseXML.REGEX_RETURN_XML);

    /**
     * 用于解析<result>中间的XML</result> 解析结果为：<result>中间的结果XML</result>
     * */
    protected final Pattern PATTERN_RESULT_XML = Pattern.compile(RegexParseXML.REGEX_RESULT_XML);
    
   

    //组织完整报文
    protected String loadXml(String requestXML) {
        StringBuffer sb = new StringBuffer();
        sb.append("<soapenv:Envelope xmlns:soapenv=\"http://schemas.xmlsoap.org/soap/envelope/\" xmlns:ser=\"http://server.cmps.newtouch.com/\">");
        sb.append("<soapenv:Header/>");
        sb.append("<soapenv:Body>");
        sb.append(requestXML);
        sb.append(" </soapenv:Body>");
        sb.append("</soapenv:Envelope>");
        return sb.toString();
    }
    
    /***
     * 
     * 功能描述: 生成Command相类似于相同的XML，用于XStream转换成对象<br>
     * 〈功能详细描述〉
     *
     * @param classSimpleName
     * @param strXML
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected String loadXMLForClass(String classSimpleName,String strXML){
        StringBuilder xmlStringBuilder = new StringBuilder();
        xmlStringBuilder.append("<");
        xmlStringBuilder.append(classSimpleName);
        xmlStringBuilder.append(">");
        xmlStringBuilder.append(strXML);
        xmlStringBuilder.append("</");
        xmlStringBuilder.append(classSimpleName);
        xmlStringBuilder.append(">");
        return xmlStringBuilder.toString();
    }
    
    //将返回报文转化为结果对象
    public AdapterCommand parseXml(String responseXML,Class<?> clazz) {
        
        String returnXMLContent=this.parseReturnContent(responseXML);
        String returnValue = this.parseReturnValue(returnXMLContent);
		String resultValue = this.parseResultValue(returnXMLContent);
		
		Map<String, String> param =new HashMap<String, String>();
		List<AdCommand> list = new LinkedList<AdCommand>();
		
		//如果报错
		if(!StringUtils.isEmpty(returnValue)&&returnValue.equalsIgnoreCase(TransResult.ERROR)
			&&!StringUtils.isEmpty(resultValue)&&resultValue.equalsIgnoreCase(TransResult.ERROR_NO)){
			
			/**失败*/
			param.put(Transaction.SANSNO.code(),TransResult.FAIL_NO);
			this.adapterCommand.setParam(param);
			this.adapterCommand.setList(list);
			
		}else{
			String classXML=this.loadXMLForClass(clazz.getSimpleName(), returnXMLContent);
	        param.put(Transaction.SANSNO.code(),TransResult.SUCCESS_NO);
	        AdCommand command=(AdCommand) XMLUtil.toBean(classXML,clazz);
	        list.add(command);
	        this.adapterCommand.setParam(param);
	        this.adapterCommand.setList(list);
		}
        return adapterCommand;
    }

    /***
     * 功能描述: 获取接口返回中,<return>节点中的内容<br>
     * 〈功能详细描述〉
     * 
     * @param strXML
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected String parseReturnContent(String strXML) {
        Matcher matcher = PATTERN_RETURN_XML.matcher(strXML);
        String returnXML = null;
        if (matcher.find()) {
            returnXML = matcher.group();
        }
        StringBuilder sb=new StringBuilder();
        sb.append("");
        return returnXML;
    }

    /***
     * 
     * 功能描述:用于解析报文返回成功或失败的标识值 〈功能详细描述〉
     * 
     * @param strXML
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected String parseReturnValue(String strXML) {
        Matcher matcher = PATTERN_SYS_RETRUN_VALUE.matcher(strXML);
        String returnXML = null;
        if (matcher.find()) {
            returnXML = matcher.group(1);
        }
        return returnXML;
    }
    
    /***
     * 
     * 功能描述: 用于解析<result>的XML文件</result><br>
     * 〈功能详细描述〉
     *
     * @param xml
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    protected String parseResultValue(String strXML) {
        Matcher matcher = PATTERN_RESULT_XML.matcher(strXML);
        String returnXML = null;
        if (matcher.find()) {
            returnXML = matcher.group(1);
        }
        return returnXML;
    }
    
    
}
