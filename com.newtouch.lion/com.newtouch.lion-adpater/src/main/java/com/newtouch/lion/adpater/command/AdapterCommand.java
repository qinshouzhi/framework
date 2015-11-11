/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: AdapterCommand.java 9552 2015年1月27日 下午8:41:17 WangLijun$
*/
package com.newtouch.lion.adpater.command;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <p>
 * Title:连接适配器对象
 * </p>
 * <p>
 * Description: 连接适配器对象
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class AdapterCommand {
    /**参数Map*/
    private Map<String,String>  param;
    /**返回对象*/
    private List<AdCommand>  list;
    
    
    
    /***
     * 默认构造函数
     */
    public AdapterCommand() {
        super();
        this.param = new HashMap<String, String>();
        this.list =new ArrayList<AdCommand>();
    }
    /**
     * @return the param
     */
    public Map<String, String> getParam() {
        return param;
    }
    /**
     * @param param the param to set
     */
    public void setParam(Map<String, String> param) {
        this.param = param;
    }
    /**
     * @return the list
     */
    public List<AdCommand> getList() {
        return list;
    }
    /**
     * @param list the list to set
     */
    public void setList(List<AdCommand> list) {
        this.list = list;
    }
} 
