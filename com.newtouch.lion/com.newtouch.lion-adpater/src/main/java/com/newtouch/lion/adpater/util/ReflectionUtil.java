 
package com.newtouch.lion.adpater.util;

import java.lang.reflect.Constructor;

public class ReflectionUtil {
    /**
     * 新建实例 
     * @param className 类名
     * @param args      构造函数的参数
     * @return 新建的实例
     * @throws Exception
     */
    public static final Object newInstance(String className, Object[] args)
            throws Exception {
        Class<?> newoneClass = Class.forName(className);
        Class<?>[] argsClass = new Class[args.length];
        for (int i = 0, j = args.length; i < j; i++) {
            argsClass[i] = (args[i]==null?"":args[i]).getClass();
        }
        Constructor<?> cons = newoneClass.getConstructor(argsClass);
        return cons.newInstance(args);
    }
}   
