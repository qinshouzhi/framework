package com.newtouch.lion.session.config;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.newtouch.lion.session.encoder.DdsEncoder;

/**
 * 
 * 〈cookie attribute config〉<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class CookieAttributeConfig {

    /**
     * key group
     */
    private String keyGroup;
    
    /**
     * key pattern
     */
    private Pattern keyPattern;
    
    /**
     * value的转换工具
     */
    private DdsEncoder encoder; 
    
    /**
     * domain
     */
    private String domain;
    
    /**
     * path
     */
    private String path;
    
    /**
     * max age
     */
    private int maxAge;
    
    /**
     * secure
     */
    private boolean secure;

    /**
     * 
     * 功能描述: get key groop<br>
     * 〈功能详细描述〉
     *
     * @return string
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getKeyGroup() {
        return keyGroup;
    }

    /**
     * 
     * 功能描述: set <br>
     * 〈功能详细描述〉
     *
     * @param keyGroup key group
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setKeyGroup(String keyGroup) {
        this.keyGroup = keyGroup;
    }

    /**
     * get
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @return pattern
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
     * 功能描述:set <br>
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
     * 功能描述:get <br>
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
     * 功能描述:set <br>
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
     * 功能描述: getDomain<br>
     * 〈功能详细描述〉
     *
     * @return domain
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getDomain() {
        return domain;
    }

    /**
     * 
     * 功能描述: setDomain<br>
     * 〈功能详细描述〉
     *
     * @param domain domain
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setDomain(String domain) {
        this.domain = domain;
    }

    /**
     * 
     * 功能描述: domain<br>
     * 〈功能详细描述〉
     *
     * @return path
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public String getPath() {
        return path;
    }

    /**
     * 
     * 功能描述:set path <br>
     * 〈功能详细描述〉
     *
     * @param path path
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setPath(String path) {
        this.path = path;
    }

    /**
     * 
     * 功能描述:getMaxAge <br>
     * 〈功能详细描述〉
     *
     * @return maxAge
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public int getMaxAge() {
        return maxAge;
    }

    /**
     * 
     * 功能描述:setMaxAge <br>
     * 〈功能详细描述〉
     *
     * @param maxAge maxAge
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setMaxAge(int maxAge) {
        this.maxAge = maxAge;
    }

    /**
     * 
     * 功能描述: isSecure<br>
     * 〈功能详细描述〉
     *
     * @return boolean
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public boolean isSecure() {
        return secure;
    }

    /**
     * 
     * 功能描述:setSecure <br>
     * 〈功能详细描述〉
     *
     * @param secure secure
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setSecure(boolean secure) {
        this.secure = secure;
    }
    
    /**
     * 
     * 功能描述: isMatch<br>
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
