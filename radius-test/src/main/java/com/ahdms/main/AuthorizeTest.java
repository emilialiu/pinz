/**
 * Created on 2019年10月23日 by liuyipin
 */
package com.ahdms.main;

import com.ahdms.form.FreeradiusAuthRequest;
import com.ahdms.util.HttpClient;
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
public class AuthorizeTest {

	public static void main(String[] args) {
		
		httpReq http = new httpReq();
		FreeradiusAuthRequest free = new FreeradiusAuthRequest();
		free.setNasid("test001");
		free.setNasip("");
		free.setUsername("test02"); 
		HttpClient httpclient = new HttpClient();
		httpclient.doPostTestTwo("http://192.168.4.24:1816/api/freeradius/authorize",free);
		
		
	}
}

