
package com.newtouch.lion.adpater.connector.aczweb;

import com.newtouch.lion.adpater.connector.AbstractConnector;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.http.AdapterHttpHandler;
import com.newtouch.lion.adpater.http.AdapterHttpResponse;
import com.newtouch.lion.adpater.http.AdatperHttpRequest;
import com.newtouch.lion.adpater.http.ContentType;

/**
 * A车站网站-服务接口连接类 <br> 
 *
 * @author yzq

 * @since [产品/模块版本] （可选）
 */
public class ACZWebConnector extends AbstractConnector {
	
	/**HTTP 连接器*/
    private AdapterHttpHandler adapterHttpHandler;

	/**
     * {@inheritDoc}
     */
    @Override
    public Object sendRequestResponse(Object object) throws AdapterException {
        logger.info("request:{}",this.addresss);
        AdatperHttpRequest request=new AdatperHttpRequest();
        request.setAddress(this.addresss);
        request.setQueryString("");
        request.setContentType(ContentType.SOAP_XML_UTF8);
        AdapterHttpResponse response=adapterHttpHandler.execute(request);
        return response.getStringResult();
    }

    /**
     * @return the adapterHttpHandler
     */
    public AdapterHttpHandler getAdapterHttpHandler() {
        return adapterHttpHandler;
    }

    /**
     * @param adapterHttpHandler the adapterHttpHandler to set
     */
    public void setAdapterHttpHandler(AdapterHttpHandler adapterHttpHandler) {
        this.adapterHttpHandler = adapterHttpHandler;
    }
	
}
