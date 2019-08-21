/**
 * Created on 2019年5月27日 by liuyipin
 */
package com.lyp.impl;

import java.util.ArrayList;
import java.util.List;

import org.apache.thrift.TException;
import org.springframework.stereotype.Service;

import com.lyp.thrift1.GroupMember;
import com.lyp.thrift1.GroupMemberListResult;
import com.lyp.thrift1.IGroupMemberQueryService.Iface;

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
@Service
public class IGroupMemberQueryServiceImpl implements Iface{

	@Override
	public GroupMemberListResult getAllGroupMembers(long arg0, long arg1) throws TException {
		// TODO Auto-generated method stub
		List<GroupMember> list = new ArrayList<GroupMember>();
		GroupMember g = new GroupMember();
		g.setGroupId(arg0);
		g.setId(arg1);
		list.add(g);
		GroupMemberListResult gml = new GroupMemberListResult();
		gml.setGroupMemeberList(list);
		return gml;
	}  

}

