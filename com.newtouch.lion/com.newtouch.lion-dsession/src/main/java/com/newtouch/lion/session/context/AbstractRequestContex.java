package com.newtouch.lion.session.context;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.util.Assert;

/**
 * 
 * 〈AbstractRequestContex〉<br>
 * 〈功能详细描述〉
 * 
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class AbstractRequestContex implements RequestContext {

    /**
     * wrappedContext
     */
    private final RequestContext wrappedContext;

    /**
     * servletContext
     */
    private final ServletContext servletContext;

    /**
     * HttpServletRequest
     */
    private HttpServletRequest request;

    /**
     * HttpServletResponse
     */
    private HttpServletResponse response;

    /**
     * originalRequest
     */
    private HttpServletRequest originalRequest;

    /**
     * originalResponse
     */
    private HttpServletResponse originalResponse;

    /**
     * Constructor
     * 
     * @param wrappedContext wrappedContext
     */
    public AbstractRequestContex(RequestContext wrappedContext) {
        Assert.isNull(wrappedContext, "wrappedContext is null");
        this.wrappedContext = wrappedContext;
        this.servletContext = wrappedContext.getServletContext();
        this.originalRequest = wrappedContext.getRequest();
        this.originalResponse = wrappedContext.getResponse();
    }

    /**
     * Constructor
     * 
     * @param servletContext servletContext
     * @param request HttpServletRequest
     * @param response HttpServletResponse
     */
    public AbstractRequestContex(ServletContext servletContext, HttpServletRequest request, 
            HttpServletResponse response) {
        this.wrappedContext = null;
        this.servletContext = servletContext;
        this.originalRequest = request;
        this.originalResponse = response;
    }

    @Override
    public RequestContext getRequestContext() {
        return wrappedContext;
    }

    @Override
    public ServletContext getServletContext() {
        return servletContext;
    }

    /**
     * 设置request对象。
     * 
     * @param request <code>HttpServletRequest</code>对象
     */
    protected void setRequest(HttpServletRequest request) {
        this.request = request;
    }

    @Override
    public HttpServletRequest getRequest() {
        return request;
    }

    /**
     * 设置response对象。
     * 
     * @param response <code>HttpServletResponse</code>对象
     */
    protected void setResponse(HttpServletResponse response) {
        this.response = response;
    }

    @Override
    public HttpServletResponse getResponse() {
        return response;
    }

    /**
     * getOriginalRequest
     * @return originalRequest
     */
    public HttpServletRequest getOriginalRequest() {
        return originalRequest;
    }

    /**
     * 
     * 功能描述:set OriginalRequest <br>
     * 〈功能详细描述〉
     * 
     * @param originalRequest originalRequest
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOriginalRequest(HttpServletRequest originalRequest) {
        this.originalRequest = originalRequest;
    }

    /**
     * getOriginalResponse
     * @return originalResponse
     */
    public HttpServletResponse getOriginalResponse() {
        return originalResponse;
    }

    /**
     * 
     * 功能描述: setOriginalResponse<br>
     * 〈功能详细描述〉
     * 
     * @param originalResponse originalResponse
     * @see [相关类/方法](可选)
     * @since [产品/模块版本](可选)
     */
    public void setOriginalResponse(HttpServletResponse originalResponse) {
        this.originalResponse = originalResponse;
    }

}
