// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ResourceManager.java

package com.dimine.sys.data;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;

import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.service.DictService;
import com.dimine.sys.service.Sys_paramService;
import com.dimine.sys.util.SpringUtil;
import com.dimine.taglib.entity.ResourceBean;
import com.dimine.taglib.service.ResourceService;
import com.dimine.util.StringUtils;

// Referenced classes of package com.dimine.framework.data:
//			StaticDataManager, IResource, ResourceBean

public final class ResourceManager {

	private static ResourceManager instance = new ResourceManager();
	private ServletContext servletContext;
	private Map sourceMap;
	@Resource
	private ResourceService<DictEntity> resourceService;

	public ResourceService<DictEntity> getResourceService() {
		return resourceService;
	}

	public void setResourceService(ResourceService<DictEntity> resourceService) {
		this.resourceService = resourceService;
	}

	public void setServletContext(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	public ServletContext getServletContext() {
		return servletContext;
	}

	private ResourceManager() {
		WebApplicationContext webApplicationContext = ContextLoader
				.getCurrentWebApplicationContext();
		servletContext = webApplicationContext.getServletContext();
	}

	public static ResourceManager getInstance() {
		return instance;
	}

	public String getNameByCode(String code, String type) {
		if (code == null || "".equals(code.trim()) || type == null
				|| type.trim().equals(""))
			return "";
		DictService dictService = (DictService) SpringUtil.getBean(
				servletContext, "dictService");
		String dictName = (String) dictService.dictNameByid(code);
		return dictName;
	}

	public String getCodeByName(String name) {
		if (name == null || "".equals(name.trim()))
			return "";
		DictService dictService = (DictService) SpringUtil.getBean(
				servletContext, "dictService");
		String dictCode = (String) dictService.dictCodeByName(name);
		return dictCode;
	}

	public String getSystemParamValue(String paramCode) {
		Sys_paramService systemParamService = (Sys_paramService) SpringUtil
				.getBean(servletContext, "sys_paramService");
		String paramValue = (String) systemParamService
				.getValueByCode(paramCode);
		return paramValue;
	}

	// 获取一级下拉列表的值
	@SuppressWarnings("rawtypes")
	public List getData(String type, Object params,String deptid) {
		resourceService = (ResourceService<DictEntity>) SpringUtil.getBean(
				ResourceManager.getInstance().getServletContext(),
				"resourceService");
		List retList = null;
		Object obj;
		retList = new ArrayList();
		DictEntity entity = new DictEntity();
		List<ResourceBean> list = null;
		if(StringUtils.isEmpty(deptid)){
			entity.setTypeid(type);
			list = resourceService.selectdictlist(entity);
		}else{
			entity.setDeptid(deptid);
			list = resourceService.findElement(entity);
		}
		
		if (list.size() <= 0) {
			ResourceBean rb = new ResourceBean("", "");
			retList.add(rb);
		} else {
			ResourceBean rb;
			for (Iterator it = list.iterator(); it.hasNext(); retList.add(rb)) {
				DictEntity de = (DictEntity) it.next();
				rb = null;
				if (params instanceof String[]) {
					String ps[] = (String[]) params;
					if (ps.length > 0)
						rb = new ResourceBean(de.getParamname(), de.getCode());
				}
				if (rb == null)
					rb = new ResourceBean(de.getParamname(), de.getCode());
				rb.setParentId(de.getParentid());
			}

		}
		return retList;
	}

	// 获取二级下拉列表支护子类型的值
	public List getChildData(String type, Object params) {
		resourceService = (ResourceService<DictEntity>) SpringUtil.getBean(
				ResourceManager.getInstance().getServletContext(),
				"resourceService");
		List retList = null;
		Object obj;
		retList = new ArrayList();
		DictEntity entity = new DictEntity();
		entity.setParentid(type);
		List<ResourceBean> list = resourceService.selectdictsecondlist(entity);
		if (list.size() <= 0) {
			ResourceBean rb = new ResourceBean("", "");
			retList.add(rb);
		} else {
			ResourceBean rb;
			for (Iterator it = list.iterator(); it.hasNext(); retList.add(rb)) {
				DictEntity de = (DictEntity) it.next();
				rb = null;
				if (params instanceof String[]) {
					String ps[] = (String[]) params;
					if (ps.length > 0)
						rb = new ResourceBean(de.getParamname(), de.getCode());
				}
				if (rb == null)
					rb = new ResourceBean(de.getParamname(), de.getCode());
				rb.setParentId(de.getParentid());
			}

		}
		return retList;
	}

}
