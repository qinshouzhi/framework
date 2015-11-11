package com.newtouch.lion.adpater.constant;

/**
 * 编码类<br> 
 * UFT-8
 * GBK
 * GB2312
 *
 * @author wanglijun

 * @since [产品/模块版本] （可选）
 */
public  enum Charset implements Code<String>{
    /**编码为UTF-8*/
    UFT8("UTF-8"),
    GBK("GBK")
    ;
    /**编码值*/
    private String code;
     /**默认构造编码值*/
    private Charset(String code) {
        this.code = code;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String code() {
       return this.code;
    }
    
    
}
