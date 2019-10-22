/**
 * Created on 2019年9月6日 by liuyipin
 */
package com.lyp.main;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

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
public class Study {

	public static void main(String[] args) {
		List<String> l = new ArrayList<String>();
		
		l.add("111");
		l.add("222");
		l.add("333");
		
		for (Iterator it = l.iterator(); it.hasNext();  ) {
			System.out.println(it.next().toString());
			it.remove();
		}
		System.out.println(l.size());
	}
}

