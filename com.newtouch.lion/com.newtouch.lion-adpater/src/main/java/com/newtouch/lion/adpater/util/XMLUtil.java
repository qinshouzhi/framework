
package com.newtouch.lion.adpater.util;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;

import com.newtouch.lion.adpater.command.base.CommandRequest0101;
import com.newtouch.lion.adpater.command.base.command0101.Response0101;
import com.newtouch.lion.adpater.constant.Charset;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.exception.ErrorCode;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;

/**
 * XML工具 <br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public final class XMLUtil {
    /***
     * 
     * 功能描述: 构建文档<br>
     * 〈功能详细描述〉
     *
     * @param obj
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Document getDocument(Object obj){
        String str = obj != null ? obj.toString() : null;
        Document document = null;
        try {
            document =DocumentHelper.parseText(str);
        } catch (DocumentException e) {
            throw new  AdapterException(ErrorCode.XML_REDER,e);
        }
        return document;
    }
    
    
    /**
     *  将传入xml文本转换成Java对象
     * @Title: toBean 
     * @Description: TODO 
     * @param xmlStr
     * @param cls  xml对应的class类
     * @return T   xml对应的class类的实例对象
     * 
     * 调用的方法实例：PersonBean person=XmlUtil.toBean(xmlStr, PersonBean.class);
     */
    @SuppressWarnings("unchecked")
    public static <T> T  toBean(String xmlStr,Class<T> cls){
        
        XStream xstream=new XStream(new DomDriver(Charset.UFT8.code()));
        xstream.processAnnotations(cls);
        T obj=(T) xstream.fromXML(xmlStr);
        return obj;         
    } 
    
    /***
     * 
     * 功能描述: 把对象解析成XML文件
     * 〈功能详细描述〉
     * @param obj
     * @return
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static String toXml(Object obj){
        XStream xstream=new XStream(new DomDriver(Charset.UFT8.code()));
        xstream.processAnnotations(obj.getClass()); //通过注解方式的，一定要有这句话
        return xstream.toXML(obj);
    }
    
    
    public static void main(String[] args) {
        
        CommandRequest0101 command=new CommandRequest0101("wanglijun", "123123aa","wwww","dddd");
        String resultXML=XMLUtil.toXml(command);
        System.out.println(resultXML);
        
        
        String xml="<return><branchId>newtouch</branchId>"
        		+ "<companyAddress>上海浦东新区东方路峨山路</companyAddress><companyName>新致软件</companyName>"
        		+ "<custBirth>1980-05-03</custBirth><custGender>男</custGender><customerId>20140910</customerId>"
        		+ "<familyAddLatitude>东经80度</familyAddLatitude><familyAddLongitude>南纬40度</familyAddLongitude>"
        		+ "<familyAddress>浦东新区张江镇川北公路</familyAddress><partyName>庄翔翔</partyName><regNo>411522198005033576</regNo>"
        		+ "<regType>身份证</regType></return>";
        XMLUtil.toBean(xml,Response0101.class);
        System.out.println("解析成功");
        
    }
}
