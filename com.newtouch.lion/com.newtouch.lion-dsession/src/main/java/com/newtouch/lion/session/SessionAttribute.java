package com.newtouch.lion.session;

import com.newtouch.lion.session.encoder.DdsEncoder;
import com.newtouch.lion.session.store.DdsSessionStore;

/**
 * 
 * 〈代表session中的一个属性。〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SessionAttribute {

    /**
     * store
     */
    private DdsSessionStore store;

    /**
     * encoder
     */
    private DdsEncoder encoder;

    /**
     * session impl
     */
    private DdsSessionImpl session;

    /**
     * key
     */
    private String key;

    /**
     * value
     */
    private Object value;

    /**
     * loaded
     */
    private boolean loaded = false;

    /**
     * 创建一个attribute。
     * 
     * @param key key
     * @param session session
     * @param store store
     * @param encoder encoder
     */
    public SessionAttribute(String key, DdsSessionImpl session, DdsSessionStore store, DdsEncoder encoder) {
        this.key = key;
        this.session = session;
        this.store = store;
        this.encoder = encoder;
    }

    /**
     * 取得attribute的名字。
     * 
     * @return attribute的名字
     */
    public String getKey() {
        return key;
    }

    /**
     * 取得attribute的值。
     * 
     * @return attribute的值
     */
    public Object getValue() {
        Object value = store.getAttribute(session.getRequestContext(), getKey());
        if (encoder != null) {
            value = encoder.decodeValue(value);
        }
        return value;
    }

    /**
     * 设置attribute的值。
     * <p>
     * 当值为<code>null</code>时，表示该属性将被删除。
     * </p>
     * 
     * @param value attribute的值
     */
    public void setValue(Object value) {
        if (value == null) {
            store.removeAttribute(session.getRequestContext(), key);
        } else {
            store.setAttribute(session.getRequestContext(), key, value);
        }
    }

}
