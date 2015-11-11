 
package com.newtouch.lion.adpater.util;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.JSONSerializer;
import com.alibaba.fastjson.serializer.PropertyPreFilter;
import com.alibaba.fastjson.serializer.SerializeConfig;
import com.alibaba.fastjson.serializer.SerializeWriter;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.alibaba.fastjson.serializer.SimpleDateFormatSerializer;
import com.alibaba.fastjson.serializer.SimplePropertyPreFilter;

public class JSONParser implements Serializable {

	/**
		 * 
		 */
	private static final long serialVersionUID = -4897117665103949997L;

	private static SerializeConfig mapping = new SerializeConfig();

	static {
		mapping.put(Date.class, new SimpleDateFormatSerializer("yyyy-MM-dd"));
	}
	
	/**
	 * 根据
	 * @param datePattern  日期格式
	 * */
	private static SerializeConfig config(String datePattern) {
		SerializeConfig mapping = new SerializeConfig();
		mapping.put(Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(java.sql.Date.class, new SimpleDateFormatSerializer(datePattern));
		mapping.put(Timestamp.class, new SimpleDateFormatSerializer(datePattern));
		return mapping;
	}
	
		
	
	/**
	 * 根据解析JSON字符
	 * @param object
	 * */
	public static String toJSONString(Object object) {
		return JSON.toJSONString(object);
	}
	
	
	/***
	 * 将对象解析为JSON
	 * @param object 
	 * @param datePattern
	 * @return String
	 * */
	public static String toJSONString(Object object,String datePattern){
		return JSON.toJSONString(object,config(datePattern));
	}
	
	/***
	 * 
	 * @param properties
	 * @param includeProperties
	 * @return PropertyFilter
	 */
	private static  PropertyPreFilter configPropertyFilter(Set<String> properties,boolean includeProperties){
		
		 SimplePropertyPreFilter simplePropertyPreFilter= new SimplePropertyPreFilter();
		 
		 if(includeProperties){
			 simplePropertyPreFilter.getIncludes().addAll(properties);
		 }else{
			 simplePropertyPreFilter.getExcludes().addAll(properties);
		 }
		 return  simplePropertyPreFilter;
	}
	
	/***
	 * 将对象解析为JSON
	 * @param object JSON解析对象
	 * @param datePattern 日期格式
	 * @param properties 字段
	 * @param isIncludeProperties  true 包含字段，False 则排除字段
	 * @return String 返回JSON字符串
	 */
	public static String toJSONString(Object object,String datePattern,Set<String> properties,boolean isIncludeProperties){
		//设置
		SerializerFeature[] features={SerializerFeature.QuoteFieldNames,SerializerFeature.DisableCircularReferenceDetect};
		SerializeWriter out = new SerializeWriter(features);
		JSONSerializer serializer = new JSONSerializer(out,config(datePattern));
		serializer.getPropertyPreFilters().add(configPropertyFilter(properties,isIncludeProperties));
		serializer.write(object);
		return out.toString();
	}
	 
	
	/***
	 * 
	 * 功能描述:将jsonString字符串转换为对象 <br>
	 * 〈功能详细描述〉	 *
	 * @param jsonStr
	 * @param clazz
	 * @return T 
	 */
	public static <T> T  toStringObject(String jsonStr,Class<T> clazz){
	     return JSON.parseObject(jsonStr, clazz);
	}
}
