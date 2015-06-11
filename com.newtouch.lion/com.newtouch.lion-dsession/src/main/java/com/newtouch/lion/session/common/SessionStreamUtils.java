package com.newtouch.lion.session.common;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 * 
 * 〈SessionStreamUtils〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SessionStreamUtils {

    /**
     * 5000 constant
     */
    private static final int FIVE =5000;
    
    /**
     * constructor
     */
    private SessionStreamUtils() {
    }


    
    /**
     * 
     * 功能描述:对象转换成自己数组 <br>
     * 〈功能详细描述〉
     * 
     * @param obj obj
     * @return byte
     * @throws IOException io exception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static byte[] objectToByteArray(Object obj) throws IOException {
        if (obj == null) {
            return null;
        }
        ObjectOutputStream os = null;
        ByteArrayOutputStream byteStream = new ByteArrayOutputStream(FIVE);
        os = new ObjectOutputStream(new BufferedOutputStream(byteStream));
        os.flush();
        os.writeObject(obj);
        os.flush();
        byte[] sendBuf = byteStream.toByteArray();
        os.close();
        return sendBuf;
    }

    /**
     * 
     * 
     * @param bytes
     * @return
     * @throws IOException
     */
    /**
     * 
     * 功能描述:自己数组转换成对象 <br>
     * 〈功能详细描述〉
     *
     * @param bytes bytes
     * @return object
     * @throws IOException ioexception
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public static Object byteArrayToObject(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length <= 0) {
            return null;
        }
        Object obj = null;
        try {
            ByteArrayInputStream bis = new ByteArrayInputStream(bytes);
            ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(bis));
            obj = ois.readObject();
            bis.close();
            ois.close();
        } catch (ClassNotFoundException e) {
            
            e.printStackTrace();
        }
        return obj;
    }
}
