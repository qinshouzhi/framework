package com.newtouch.lion.session.config;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSessionListener;

import com.newtouch.lion.session.store.DdsSessionStore;
import com.newtouch.lion.session.store.impl.DdsDefaultSessionStore;

public class DdsSessionConfig {

    private List<HttpSessionListener> listerners = new ArrayList<HttpSessionListener>();

    private List<DdsSessionStore> sessionStores = new ArrayList<DdsSessionStore>();

    /*
     * 会话保持时间，单位。秒
     */
    private int maxInactiveInterval;

    public DdsSessionStore getSessionStore(String key) {
        if (sessionStores.size() > 0) {
            for (DdsSessionStore store : sessionStores) {
                SessionAttributeConfig cookieAttr = store.getSessionAttributeConfig();
                if (cookieAttr.isMatch(key)) {
                    return store;
                }
            }
        }
        return new DdsDefaultSessionStore();
    }

    public List<HttpSessionListener> getListerners() {
        return listerners;
    }

    public void setListerners(List<HttpSessionListener> listerners) {
        this.listerners = listerners;
    }

    public List<DdsSessionStore> getSessionStores() {
        return sessionStores;
    }

    public void setSessionStores(List<DdsSessionStore> sessionStores) {
        this.sessionStores = sessionStores;
        SessionAttributeConfig sessionAttrConfig = new SessionAttributeConfig();
        sessionAttrConfig.setKeyGroup(".*");
        sessionAttrConfig.setKeyPattern(null);
//        DdsDefaultSessionStore defaultStore = new DdsDefaultSessionStore();
//        defaultStore.setSessionAttributeConfig(sessionAttrConfig);
        // 自动填充DdsDefaultSessionStore(),到store集合中
        // 当xml中配置的store都匹配不上的是后会匹配该store
//        this.sessionStores.add(defaultStore);
        // for(DdsSessionStore store : this.sessionStores){
        // store.setMaxInactiveInterval(maxInactiveInterval);
        // }
    }

    public int getMaxInactiveInterval() {
        return maxInactiveInterval;
    }

    public void setMaxInactiveInterval(int maxInactiveInterval) {
        this.maxInactiveInterval = maxInactiveInterval;
    }

}
