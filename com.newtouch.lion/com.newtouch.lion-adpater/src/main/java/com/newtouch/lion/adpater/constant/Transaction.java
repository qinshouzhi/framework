package com.newtouch.lion.adpater.constant;

/**
 * 交易常量<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun

 * @since [产品/模块版本] （可选）
 */
public enum Transaction  implements   Code<String>{
    SANSNO("SANSNO"),
    SALLPAGE("SALLPAGE")
    ;
    /**交易代码*/
    private String code;
    
    private Transaction(String code){
        this.code=code;
    }
    
    /**
     * {@inheritDoc}
     */
    @Override
    public String code() {
        return this.code;
    }
    
}
