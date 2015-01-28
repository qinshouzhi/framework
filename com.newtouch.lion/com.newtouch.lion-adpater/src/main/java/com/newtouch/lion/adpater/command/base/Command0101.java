 
package com.newtouch.lion.adpater.command.base;

import com.thoughtworks.xstream.annotations.XStreamAlias;

 
@XStreamAlias("Command0101")
public class Command0101 extends BaseCommand {

    /**
     */
    private static final long serialVersionUID = 769020147538658849L;
    /**用户名*/
    @XStreamAlias("partyName")
    private String partyName;
    
    /**密码*/
    @XStreamAlias("passWord")
    private String password;
    /**KEY*/
    private String useKey;
    
    
    
}
