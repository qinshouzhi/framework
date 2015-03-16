package com.newtouch.lion.adpater.exception;

/**
 * 接口异常处理<br> 
 *
 * @author wanglijun

 * @since [产品/模块版本] （可选）
 */
public class AdapterException extends RuntimeException{

    /**
     */
    private static final long serialVersionUID = 6892794888177533754L;
    
    /**错误代码*/
    private  ErrorCode errorCode;
    /**默认构造函数*/
    public AdapterException() {
         super();
    }
    /**
     * @param errorCode 错误代码
     */
    public AdapterException(ErrorCode errorCode) {
        super(errorCode.code());
        this.errorCode = errorCode;
    }
    
    public  AdapterException(ErrorCode errorCode,Throwable e){
        super(errorCode.code(),e);
        this.errorCode=errorCode;
    }
    
    
    
    /* (non-Javadoc)
     * @see java.lang.Throwable#getMessage()
     */
    @Override
    public String getMessage() {
        StringBuilder sb=new StringBuilder();
        sb.append(super.getMessage());
        sb.append(",");
        sb.append(this.errorCode.message());
        return sb.toString();
    }
    /**
     * @return the errorCode
     */
    public ErrorCode getErrorCode() {
        return errorCode;
    }
     
}
