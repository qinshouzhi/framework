package com.newtouch.lion.session.encoder;

/**
 * 
 * 〈DdsDefaultEncoder〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsDefaultEncoder implements DdsEncoder {

    @Override
    public String encodeName(String name) {
        return name;
    }

    @Override
    public String decodeName(String name) {
        return name;
    }

    @Override
    public Object encodeValue(Object value) {
        return value;
    }

    @Override
    public Object decodeValue(Object value) {
        return value;
    }

}
