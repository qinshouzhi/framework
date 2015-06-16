/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: RedisCacheableMethodAdvice.java 9552 2015年4月11日 下午2:30:25 WangLijun$
*/
package com.newtouch.lion.redis.cache.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javassist.ClassClassPath;
import javassist.ClassPool;
import javassist.CtClass;
import javassist.CtMethod;
import javassist.NotFoundException;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.SpelEvaluationException;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.redis.cache.Constant;
import com.newtouch.lion.redis.cache.api.CacheKeyGenerator;
import com.newtouch.lion.redis.cache.api.MethodParameter;
import com.newtouch.lion.redis.exception.CacheErrorCode;
import com.newtouch.lion.redis.exception.CacheException;

/**
 * 缓存KEY生成 实现类<br> 
 * 〈功能详细描述〉KEY生成
 *
 * @author wanglijun
 */
@Service
public class CacheKeyGeneratorImpl implements CacheKeyGenerator {
    /**日志*/
    protected final Logger logger=LoggerFactory.getLogger(super.getClass());
    /**获取缓存key中的参数值*/
    protected final Pattern pattern=Pattern.compile(Constant.KEY_PARESE_PARAM_PATTERN);
   

    /**
     * {@inheritDoc}
     */
    @Override
    public String keyGenerator(String key, Object target, Method method, Object[] args) {
        String resultKey=null;
        List<String> keyParams=this.parseKey(key);
        //KEY是否需要填充参数，如为空，则直接返回key做为参数对象
        if(CollectionUtils.isEmpty(keyParams)){
            return key;
        }
        //解析方法参数信息 （参数类型、参数名称、参数值）
        List<MethodParameter> methodParameters = this.parseParameter(target, method, args);
        resultKey=this.getKey(key,keyParams,methodParameters);
        return resultKey;
    }
    
    
    /***
     * 根据KEY、KEY的参数和方法参数信息填充KEY的参数值
     * 
     * @param key
     * @param keys
     * @param methodParameters
     * @return
     */
    protected String getKey(String key, List<String> keys,List<MethodParameter> methodParameters){
        ExpressionParser parser = new SpelExpressionParser();
        StandardEvaluationContext context=this.getContext(methodParameters);
        StringBuilder sbKey=new StringBuilder();
        sbKey.append(key);
        for (String paramKey : keys) {
            try{
                String value=this.getValue(paramKey,parser,context);
                int indexKey=sbKey.indexOf(paramKey);
                if(indexKey>1){
                    int start=indexKey-1;
                    int end=indexKey+paramKey.length()+1;
                    sbKey.replace(start,end,value);
                }
            }catch(CacheException e){
                logger.error(e.getMessage(),e);
            }
        }
        return sbKey.toString();
    }
    
    /**
     * 
     * @param key 缓存键
     * @param context  缓存对象
     * @return
     */
    protected  String getValue(String key,ExpressionParser parser,StandardEvaluationContext context){
        Expression expression = parser.parseExpression(key);
        String  value=null;
        try{
            value=expression.getValue(context,String.class);
        }catch (SpelEvaluationException e) {
            throw new CacheException(CacheErrorCode.PARAMETER_NOTFOND,e);
        }
        return value==null?StringUtils.EMPTY:value;
    }
    
    
    /***
     *  将值存放在 表达式语言 
     * @param methodParameters 
     * @return
     */
    protected  StandardEvaluationContext getContext(List<MethodParameter> methodParameters){
        StandardEvaluationContext context = new StandardEvaluationContext();
        for (MethodParameter methodParameter : methodParameters) {
            context.setVariable(methodParameter.getName(),methodParameter.getValue());
        }
        return context;
    }
    
    /**
     * 解析参数名称、参数类型、参数值
     * 
     * @param target            对象
     * @param method          方法
     * @param args       方法参数值
     * @return List<MethodParameter> 参数模型对象
     */
    protected List<MethodParameter> parseParameter(Object target,Method method, Object[] args) {
        List<Class<?>> list = Arrays.asList(method.getParameterTypes());
        List<Object> listArgs = Arrays.asList(args);
        List<String> methodParamNames = this.getMethodParameterName(target,
                method);
        List<MethodParameter> methodParameters = new ArrayList<MethodParameter>();
        for (int i = 0; i < list.size(); i++) {
            Class<?> type = list.get(i);
            Object value = listArgs.get(i);
            String name = methodParamNames.get(i);
            MethodParameter parameter = new MethodParameter(name, value, type);
            logger.info("MethodParameter.tString():{}", parameter.toString());
            methodParameters.add(parameter);
        }
        return methodParameters;
    }
    
    /***
     * 解析方法参数名称
     * @param target  代理对象
     * @param method
     * @return
     */
    protected List<String> getMethodParameterName(Object target, Method method) {
        List<String> methodParamNames = new ArrayList<String>();
        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(this.getClass());
        pool.insertClassPath(classPath);
        CtMethod ctMethod = null;
        try {
            logger.info("target.getClass().getName():{}",target.getClass().getName());
            CtClass ctClass = pool.get(target.getClass().getName());
            ctMethod = ctClass.getDeclaredMethod(method.getName());
        } catch (NotFoundException e) {
            throw new CacheException(CacheErrorCode.PARAMETER_NOTFOND, e);
        }
        if (ctMethod != null) {
            // 当前方法的信息
            javassist.bytecode.MethodInfo methodInfo = ctMethod.getMethodInfo();
            CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
            LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute
                    .getAttribute(LocalVariableAttribute.tag);
            for (int i = 0; i < method.getParameterTypes().length; i++) {
                methodParamNames.add(attr.variableName(i + 1));
            }
        }
        return methodParamNames;
    }
    
    /***
     * 
     * 功能描述: 解析KEY中的参数值信息
     * 〈功能详细描述〉
     *
     * @param key
     * @return 参数列表信息
     */
    protected List<String> parseKey(String key) {
        List<String> keys = new ArrayList<String>();
        Matcher matcher = pattern.matcher(key);
        while (matcher.find()) {
            keys.add(matcher.group());
        }
        return keys;
    }
    
}
