/*
* Copyright (c)  2015, Newtouch
* All rights reserved. 
*
* $id: CookieConfig.java 9552 2015年5月21日 下午2:39:34 WangLijun$
*/
package com.newtouch.lion.dsession.config; 

import java.util.ArrayList;
import java.util.List;

import com.newtouch.lion.dsession.store.CookieStore;
import com.newtouch.lion.session.store.DdsCookieStore;


/**
 * <p>
 * Title: Cookie Config
 * </p>
 * <p>
 * Description: Cookie Config
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Newtouch
 * </p>
 * 
 * @author WangLijun
 * @version 1.0
 */
public class DistributedCookieConfig {
	 /**
     * cookieStores
     */
    private List<CookieStore> cookieStores = new ArrayList<CookieStore>();

    /**
     * 
     * 功能描述: getCookieStores<br>
     * 〈功能详细描述〉
     * 
     * @return cookieStores
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public List<CookieStore> getCookieStores() {
        return this.cookieStores;
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
    public void setCookieStores(List<CookieStore> cookieStores) {
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
    public List<DistributedCookieAttributeConfig> getCookieAttributes() {
        List<DistributedCookieAttributeConfig> cookieAttributes = new ArrayList<DistributedCookieAttributeConfig>();
        if (cookieStores.size() > 0) {
            for (CookieStore stores : cookieStores) {
                //CookieAttributeConfig cookieAttr = stores.getCookieAttributeConfig();
                //cookieAttributes.add(cookieAttr);
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
            for (CookieStore store : cookieStores) {
                //CookieAttributeConfig cookieAttr = store.getCookieAttributeConfig();
               // if (cookieAttr.isMatch(key)) {
               //     return store;
                //}
            }
        }
        return null;
    }
}

	