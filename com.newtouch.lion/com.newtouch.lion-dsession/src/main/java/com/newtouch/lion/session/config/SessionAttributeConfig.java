package com.newtouch.lion.session.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.newtouch.lion.session.encoder.DdsDefaultEncoder;
import com.newtouch.lion.session.encoder.DdsEncoder;

/**
 * 
 * 〈代表session中的一个属性〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class SessionAttributeConfig {
   
    /**
     * key group
     */
    private String keyGroup;
    
    /**
     * keyPattern
     */
    private Pattern keyPattern;
    
    /**
     * session key和value的转换工具
     */
    private DdsEncoder encoder = new DdsDefaultEncoder();  
    
    /**
     * 
     * 功能描述:getKeyGroup <br>
     * 〈功能详细描述〉
     *
     * @return keyGroup
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getKeyGroup() {
        return keyGroup;
    }

    /**
     * 
     * 功能描述: setKeyGroup<br>
     * 〈功能详细描述〉
     *
     * @param keyGroup keyGroup
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKeyGroup(String keyGroup) {
        this.keyGroup = keyGroup;
    }

    /**
     * 
     * 功能描述:getKeyPattern <br>
     * 〈功能详细描述〉
     *
     * @return keyPattern
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public Pattern getKeyPattern() {
        if(keyPattern == null){
            keyPattern = Pattern.compile(keyGroup);
        }
        return keyPattern;
    }

    /**
     * 
     * 功能描述:setKeyPattern <br>
     * 〈功能详细描述〉
     *
     * @param keyPattern keyPattern
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKeyPattern(Pattern keyPattern) {
        this.keyPattern = keyPattern;
    }

    /**
     * 
     * 功能描述:DdsEncoder <br>
     * 〈功能详细描述〉
     *
     * @return encoder
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DdsEncoder getEncoder() {
        return encoder;
    }

    /**
     * 
     * 功能描述:setEncoder <br>
     * 〈功能详细描述〉
     *
     * @param encoder encoder
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setEncoder(DdsEncoder encoder) {
        this.encoder = encoder;
    }

    /**
     * 
     * 功能描述:isMatch <br>
     * 〈功能详细描述〉
     *
     * @param key key
     * @return boolean
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean isMatch(String key){
        if(!StringUtils.hasLength(key)){
            return false;
        }
        Matcher matcher = getKeyPattern().matcher(key);
        return matcher.matches();
    }
    
}
