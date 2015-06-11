package com.newtouch.lion.session.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import com.newtouch.lion.session.common.RequestContextUtils;
import com.newtouch.lion.session.common.SessionConstant;
import com.newtouch.lion.session.config.DdsCookieConfig;
import com.newtouch.lion.session.config.DdsSessionConfig;
import com.newtouch.lion.session.context.DdsSessionRequestContextImpl;

/**
 * 
 * 〈统一session的入口filter〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class DdsSessionCooikeFilter implements Filter {

    /**
     * logger
     */
    private static final  Logger LOGGER = LoggerFactory.getLogger(DdsSessionCooikeFilter.class);

    /**
     * ServletContext
     */
    private ServletContext servletContext = null;

    /**
     * DdsSessionConfig
     */
    private DdsSessionConfig sessionConfig = null;

    /**
     * DdsCookieConfig
     */
    private DdsCookieConfig cookieConfig = null;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        servletContext = filterConfig.getServletContext();
        WebApplicationContext wac = WebApplicationContextUtils.getWebApplicationContext(servletContext);
        try {
            sessionConfig = wac.getBean(DdsSessionConfig.class);
            cookieConfig = wac.getBean(DdsCookieConfig.class);
        } catch (BeansException e) {
            LOGGER.debug(e.getMessage());
        }
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException,
            ServletException {
        HttpServletRequest req = (HttpServletRequest) request;
        HttpServletResponse res = (HttpServletResponse) response;
        if (sessionConfig == null) {
            chain.doFilter(req, res);
        } else {
            DdsSessionRequestContextImpl snfRequestContext = new DdsSessionRequestContextImpl(servletContext, req, res,
                    sessionConfig, cookieConfig);
            // 跟新session的上次访问时间
            RequestContextUtils.writeKeyValueToCookie(snfRequestContext, SessionConstant.SESSION_LASTACCESS_TIME,
                    String.valueOf(System.currentTimeMillis()));

            chain.doFilter(snfRequestContext.getRequest(), snfRequestContext.getResponse());
        }
    }

    @Override
    public void destroy() {

    }

}
