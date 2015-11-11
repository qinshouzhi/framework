 
package com.newtouch.lion.adpater.service;

import java.util.LinkedHashMap;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.CollectionUtils;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.connector.Connector;
import com.newtouch.lion.adpater.constant.TransResult;
import com.newtouch.lion.adpater.constant.Transaction;
import com.newtouch.lion.adpater.exception.AdapterException;
import com.newtouch.lion.adpater.formatter.Formatter;
import com.newtouch.lion.adpater.util.JSONParser;

 
public abstract class AbstractAdapterService implements  AdapterService{
	
    protected final Logger logger=LoggerFactory.getLogger(super.getClass());
    /**连接器*/
    private Connector connector;
    /**解析器*/
    private Formatter formatter;
    /***
     * 
     * 功能描述: 根据交易号、参数列表构建AdCommand对象<br>
     * 〈功能详细描述〉
     *
     * @param stransno 交易号
     * @param params 参数
     * @return  AdCommand
     * @throws AdapterException
     */
    protected abstract AdCommand normalize(String stransno,LinkedHashMap<Object, Object> params) throws AdapterException;
    /***
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param stransno
     * @return AdCommand
     * @throws AdapterException

     * @since [产品/模块版本](可选)
     */
    protected abstract AdCommand normalize(String stransno) throws AdapterException;
    /***
     * 
     * 功能描述: <br>
     * 〈功能详细描述〉
     *
     * @param command
     * @return AdCommand
     * @throws AdapterException
     */
    protected abstract AdCommand normalize(AdCommand command) throws AdapterException;
    /***
     * 
     * 功能描述:构建解析类 <br>
     * 〈功能详细描述〉
     * @param className 构建类
     * @param args 参数
     * @return AdCommand
     * @throws AdapterException
     */
    protected abstract Formatter getFormatter(String className, Object[] args) throws AdapterException;
    /**
     * {@inheritDoc}
     */
    @Override
    public AdapterCommand sendCommand(String stransno, LinkedHashMap<Object, Object> params)
            throws AdapterException {
        //开始时间
        long startTime=System.currentTimeMillis();
        //初始化
        AdCommand cmd = normalize(stransno, params);
        // Get Formatter
        Formatter formatter = this.getFormatter(stransno,new String[0]);
        // Format Request
        String requestStr = (String) formatter.formateRequest(cmd);
        // Create log object
        long connectorStartTime=System.currentTimeMillis();
        // Connector sending
        Object receive = connector.sendRequestResponse(requestStr);
        // Set end time of log object
        long callCostTime=System.currentTimeMillis()-connectorStartTime;
        logger.info("External system processing time:{}",callCostTime);
        // Format Response
        long parseResponse=System.currentTimeMillis();
        AdapterCommand adapterCommand = formatter.formateResponse(receive);
        logger.info("Format processing time:"+ (System.currentTimeMillis()-parseResponse));
        // set communication result
        String sansno =adapterCommand.getParam().get(Transaction.SANSNO.code());
        logger.info("result sansno:{}",sansno);
        //IAA_SALLPAGE process
        List<AdCommand> list=adapterCommand.getList();
        if(CollectionUtils.isEmpty(list)){
        	adapterCommand.getParam().put(Transaction.SALLPAGE.code(),TransResult.FAIL_NO);
        }
        logger.info("Adapter processing time:{}",(System.currentTimeMillis()-startTime));
        return adapterCommand;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public AdapterCommand sendCommand(String stransno, AdCommand command) throws AdapterException {
      
      return null;
    }
    /***
     * default
     */
    public AbstractAdapterService() {
        super();
    }
    
    /**
     * @return  接口连接器
     */
    public Connector getConnector() {
        return connector;
    }
    /**
     * @param connector 接口连接器
     */
    public void setConnector(Connector connector) {
        this.connector = connector;
    }
    /**
     * @return 解析器
     */
    public Formatter getFormatter() {
        return formatter;
    }
    /**
     * @param formatter 解析器
     */
    public void setFormatter(Formatter formatter) {
        this.formatter = formatter;
    }
    
    /**
     * {@inheritDoc}
     */
    public <T> T getDataFromAczWeb(Class<T> clazz){
    	Connector connector=this.getConnector();
    	long connectorStartTime=System.currentTimeMillis();
    	//调用网站接口并返回json报文
    	String jsonStr = (String)connector.sendRequestResponse(null);
    	long callCostTime=System.currentTimeMillis()-connectorStartTime;
    	jsonStr=jsonStr.substring(jsonStr.indexOf("{"));
//    	logger.info("json:{}",jsonStr);
    	logger.info("External system processing time:{}",callCostTime);
     
    	return  JSONParser.toStringObject(jsonStr,clazz);
    }
    
}
