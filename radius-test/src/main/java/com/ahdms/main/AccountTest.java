/**
 * Created on 2019年10月23日 by liuyipin
 */
package com.ahdms.main;

import com.ahdms.form.FreeradiusAcctRequest;
import com.ahdms.util.HttpClient;
import com.ahdms.util.UUIDGenerator;
import com.ahdms.util.httpReq;

/**
 * @Title 
 * @Description API调用方式
 * @Copyright <p>Copyright (c) 2015</p>
 * @Company <p>迪曼森信息科技有限公司 Co., Ltd.</p>
 * @author liuyipin
 * @version 1.0
 * @修改记录
 * @修改序号，修改日期，修改人，修改内容
 */
public class AccountTest {

	public static void main(String[] args) {

		HttpClient httpclient = new HttpClient();
		httpReq http = new httpReq();
		FreeradiusAcctRequest free = new FreeradiusAcctRequest();
		free.setNasid("test001");
		free.setNasip("");
		free.setUsername("test03");  
		free.setAcctStatusType("Start"); // Alive/Interim-Update  Stop
//		free.setAcctStatusType("Interim-Update");
//		free.setAcctStatusType("Stop");
		free.setSessionTimeout(0);
		free.setFramedIPAddress("192.19.45.161"); //用户ip
		free.setFramedIPNetmask("");
		free.setFramedIPNetmask("52:54:00:29:8f:f8");
		free.setNasPortId("");
//		free.setAcctSessionId(UUIDGenerator.getUUID());0a2c55c50b854a25959e7d1c9c0e5083
		free.setAcctSessionId("0a2c55c50b854a25959e7d1c9c0e5083");
		free.setAcctSessionTime(120);
		free.setAcctInputOctets((long) 11227287);
		free.setAcctOutputOctets((long) 11222576);
		free.setAcctInputPackets(221);
		free.setAcctOutputPackets(21);  
 

//		httpclient.doPostTestTwo("http://192.168.4.24:1816/api/freeradius/accounting",free);
		httpclient.doPostTestTwo("http://172.16.1.105:1816/api/freeradius/accounting",free);
		
		
	}
}

