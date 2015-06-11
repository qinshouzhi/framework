package com.newtouch.lion.session.config;

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.session.store.DdsCookieStore;

/**
 * 
 * 〈DdsCookieConfig〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsCookieConfig {

    /**
     * cookieStores
     */
    private List<DdsCookieStore> cookieStores = new ArrayList<DdsCookieStore>();

    /**
     * 
     * 功能描述: getCookieStores<br>
     * 〈功能详细描述〉
     * 
     * @return cookieStores
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<DdsCookieStore> getCookieStores() {
        return cookieStores;
    }

    /**
     * 
     * 功能描述:setCookieStores <br>
     * 〈功能详细描述〉
     * 
     * @param cookieStores cookieStores
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setCookieStores(List<DdsCookieStore> cookieStores) {
        this.cookieStores = cookieStores;
    }

    /**
     * 
     * 功能描述:getCookieAttributes <br>
     * 〈功能详细描述〉
     * 
     * @return cookieAttributes
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<CookieAttributeConfig> getCookieAttributes() {
        List<CookieAttributeConfig> cookieAttributes = new ArrayList<CookieAttributeConfig>();
        if (cookieStores.size() > 0) {
            for (DdsCookieStore stores : cookieStores) {
                CookieAttributeConfig cookieAttr = stores.getCookieAttributeConfig();
                cookieAttributes.add(cookieAttr);
            }
        }
        return cookieAttributes;
    }

    /**
     * 
     * 功能描述: getCookieStore<br>
     * 〈功能详细描述〉
     * 
     * @param key key
     * @return cookieStores
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public DdsCookieStore getCookieStore(String key) {
        if (cookieStores.size() > 0) {
            for (DdsCookieStore store : cookieStores) {
                CookieAttributeConfig cookieAttr = store.getCookieAttributeConfig();
                if (cookieAttr.isMatch(key)) {
                    return store;
                }
            }
        }
        return null;
    }
}
