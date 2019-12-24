/**
 * Created on 2019年10月24日 by liuyipin
 */
package com.ahdms.controller;

import java.io.IOException;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ahdms.common.CoderUtil;
import com.ahdms.config.RadiusConfig;
import com.ahdms.tinyradius.packet.AccessRequest;
import com.ahdms.tinyradius.packet.AccountingRequest;
import com.ahdms.tinyradius.packet.RadiusPacket;
import com.ahdms.tinyradius.util.RadiusClient;
import com.ahdms.tinyradius.util.RadiusException;
import com.ahdms.tinyradius.util.RadiusUtil;

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
@RestController
@RequestMapping("/server")
public class RadiusClientTestController {
 
	@Autowired
	private RadiusConfig radiusConfig;
	
	private final static Map<String,String> ipmap = new HashMap<>();
	private final static Map<String,String> nasportidmap = new HashMap<>();
	private final static Map<String,String> macmap = new HashMap<>();


	@RequestMapping(value = "/auth", method = RequestMethod.POST) 
	public void auth(  @RequestParam String user,@RequestParam String Password) { 
		long start = System.currentTimeMillis();
		RadiusClient cli;
		try {
			cli = radiusConfig.getClient();
			AccessRequest request = new AccessRequest();
			request.setUserName(user);
			request.setUserPassword(Password);
			request.setAuthProtocol(radiusConfig.getAuthProtocol());
			request.addAttribute("Service-Type","Framed-User");//用户请求的服务类型
			request.addAttribute("Framed-Protocol","PPP");
			request.addAttribute("NAS-IP-Address",radiusConfig.getNasip());
			request.addAttribute("Calling-Station-Id",getMacaddr(user));
			request.addAttribute("NAS-Identifier",radiusConfig.getNasid());
			request.addAttribute("NAS-Port-Id", getNasPortId(user));
			System.out.println(request.toString());
			RadiusPacket dmrep = cli.communicate(request,radiusConfig.getAuthport());
			System.out.println(dmrep.toString());
			System.out.println(String.format("cast time: %s ms", System.currentTimeMillis()-start));
		} catch (SocketException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (RadiusException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
	
	
	
	@RequestMapping(value = "/sendAcct", method = RequestMethod.POST) 
	public String sendAcct(  @RequestParam String user,@RequestParam int type) { 
		// type(start:1 stop:2 update:3)
		try {
            long start = System.currentTimeMillis();
            RadiusClient cli = radiusConfig.getClient();
            AccountingRequest request = new AccountingRequest(user,type);
            request.setUserName(user);
            request.addAttribute("Service-Type","Framed-User");
            request.addAttribute("Framed-Protocol","PPP");
            request.addAttribute("Acct-Session-Id", CoderUtil.md5Encoder(user));
            request.addAttribute("NAS-IP-Address",radiusConfig.getNasip());
            request.addAttribute("Calling-Station-Id",getMacaddr(user));
            request.addAttribute("Called-Station-Id","00-00-00-00-00-00");
            request.addAttribute("NAS-Identifier",radiusConfig.getNasid());
            request.addAttribute("NAS-Port-Id",getNasPortId(user));
            request.addAttribute("Framed-IP-Address",getIpaddr(user));
            request.addAttribute("NAS-Port","0");
            if(type == AccountingRequest.ACCT_STATUS_TYPE_START){
                request.addAttribute("Acct-Input-Octets","0");
                request.addAttribute("Acct-Output-Octets","0");
                request.addAttribute("Acct-Input-Packets","0");
                request.addAttribute("Acct-Output-Packets","0");
                request.addAttribute("Acct-Session-Time","0");
                request.addAttribute("Acct-time-Octets","0");
            }else if(type == AccountingRequest.ACCT_STATUS_TYPE_INTERIM_UPDATE){
                request.addAttribute("Acct-Input-Octets","1048576");
                request.addAttribute("Acct-Output-Octets","8388608");
                request.addAttribute("Acct-Input-Packets","1048576");
                request.addAttribute("Acct-Output-Packets","8388608");
                request.addAttribute("Acct-Session-Time","60");
                request.addAttribute("Acct-time-Octets","1");
            }else if(type == AccountingRequest.ACCT_STATUS_TYPE_STOP){
                request.addAttribute("Acct-Input-Octets","2097152");
                request.addAttribute("Acct-Output-Octets","16777216");
                request.addAttribute("Acct-Input-Packets","2097152");
                request.addAttribute("Acct-Output-Packets","16777216");
                request.addAttribute("Acct-Session-Time","120");
            }
            System.out.println(request.toString());
            RadiusPacket dmrep = cli.communicate(request,radiusConfig.getAcctport());
            System.out.println(dmrep.toString());
            System.out.println(String.format("cast time: %s ms", System.currentTimeMillis()-start));
            return "done";
        } catch (Exception e) {
            return String.format("send radius accounting failure %s", e.getMessage());
        }

	}
	
	
	




	private String getIpaddr(String user) {
		if(!ipmap.containsKey(user)){
			ipmap.put(user, RadiusUtil.randIpaddr());
		}
		return ipmap.get(user);
	}


	private  String getNasPortId(String user) {
		if(!nasportidmap.containsKey(user)){
			nasportidmap.put(user, RadiusUtil.randIpaddr());
		}
		return nasportidmap.get(user);
	}


	private  String getMacaddr(String user) {
		if(!macmap.containsKey(user)){
			macmap.put(user, RadiusUtil.randIpaddr());
		}
		return macmap.get(user);
	}

}

