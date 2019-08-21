/**
 * Created on 2019年5月29日 by liuyipin
 */
package com.lyp.impl;

import org.apache.thrift.TException;

import com.lyp.thrift1.HelloWorldService.Iface;

/**
 * @Title 
 * @Description 
 * @Copyright <p>Copyright (c) 2015</p>
 * @Company <p>迪曼森信息科技有限公司 Co., Ltd.</p>
 * @author liuyipin
 * @version 1.0
 * @修改记录
 * @修改序号，修改日期，修改人，修改内容
 */
public class HelloServiceImpl implements Iface {

	@Override
	public String sayHello(String username) throws TException {
		// TODO Auto-generated method stub
		return "hello " + username;
	}

}

