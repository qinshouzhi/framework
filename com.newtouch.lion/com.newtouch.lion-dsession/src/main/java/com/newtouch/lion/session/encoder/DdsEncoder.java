package com.newtouch.lion.session.encoder;

/**
 * 
 * 对key-value做编码<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public interface DdsEncoder {

    /**
     * 
     * 功能描述: 对key编码<br>
     * 〈功能详细描述〉
     * 
     * @param name name
     * @return encode name
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    String encodeName(String name);

    /**
     * 
     * 功能描述: 对key解码<br>
     * 〈功能详细描述〉
     * 
     * @param name name
     * @return decode name
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    String decodeName(String name);

    /**
     * 
     * 功能描述: 对value编码 <br>
     * 〈功能详细描述〉
     * 
     * @param value value
     * @return encode value
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    Object encodeValue(Object value);

    /**
     * 
     * 功能描述:对value解码 <br>
     * 〈功能详细描述〉
     * 
     * @param value value
     * @return decode value
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    Object decodeValue(Object value);
}
