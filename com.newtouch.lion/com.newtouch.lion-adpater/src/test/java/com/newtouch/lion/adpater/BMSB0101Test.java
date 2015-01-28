 
package com.newtouch.lion.adpater;

import java.util.Iterator;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

import com.newtouch.lion.adpater.command.AdCommand;
import com.newtouch.lion.adpater.command.AdapterCommand;
import com.newtouch.lion.adpater.command.base.Command0101;
import com.newtouch.lion.adpater.command.base.CommandResponse0101;
import com.newtouch.lion.adpater.constant.TransNo;
import com.newtouch.lion.adpater.service.AdapterService;

/**
 *  BMS测试类<br> 
 * 〈功能详细描述〉
 *
 * @author wanglijun
 * @see [相关类/方法]（可选）
 * @since [产品/模块版本] （可选）
 */
public class BMSB0101Test extends AppTest {
    
  
    @Autowired
    @Qualifier("adapterServiceBase")
    private AdapterService adapterServiceBase;
    
    @Test
    public void sendTest(){
        params.put("partyName","bms");
        params.put("regType","bms");
        params.put("regNo","bms");
        params.put("pageSize","bms");
        AdapterCommand baseCommand=adapterServiceBase.sendCommand(TransNo.BASE0101, params);
        Iterator<AdCommand> it = baseCommand.getList().iterator();
        while (it.hasNext()) {
        	CommandResponse0101 basicBean = (CommandResponse0101) it.next();
            logger.info("BMSB0101Test:"+basicBean.toString());
        }
    }
    
}
