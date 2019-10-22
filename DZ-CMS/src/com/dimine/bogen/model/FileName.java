package com.dimine.bogen.model;
/**
 * 
 * <p> FileName </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
public class FileName {
	private String action;//Action文件名
	private String entity;//Entity文件名
	private String service;//service文件名
	private String dao;//dao文件名
	private String actionConf;//Action配置文件名
	private String ibatisConf;//Ibatis配置文件名
	private String springConf;//Spring配置文件名
	private String serviceConf;//Service配置文件名
	
	public String getAction(String actionName) {
		StringBuffer sb = new StringBuffer("");
		action = sb.append(actionName.substring(0,1).toUpperCase()).append(actionName.substring(1)).append("Action.java").toString();
		return action;
	}
	public String getEntity(String actionName) {
		StringBuffer sb = new StringBuffer("");
		entity = sb.append(actionName.substring(0,1).toUpperCase()).append(actionName.substring(1)).append("Entity.java").toString();
		return entity;
	}
	public String getService(String actionName) {
		StringBuffer sb = new StringBuffer("");
		service = sb.append(actionName.substring(0,1).toUpperCase()).append(actionName.substring(1)).append("Service.java").toString();
		return service;
	}
	public String getDao(String actionName) {
		StringBuffer sb = new StringBuffer("");
		dao = sb.append(actionName.substring(0,1).toUpperCase()).append(actionName.substring(1)).append("Dao.java").toString();
		return dao;
	}
	public String getActionConf(String actionName) {
		StringBuffer sb = new StringBuffer("");
		actionConf = sb.append(actionName).append(".xml").toString();
		return actionConf;
	}
	public String getIbatisConf(String actionName) {
		StringBuffer sb = new StringBuffer("");
		ibatisConf = sb.append(actionName).append(".xml").toString();
		return ibatisConf;
	}
	public String getSpringConf(String actionName) {
		StringBuffer sb = new StringBuffer("");
		springConf = sb.append("applicationContext-action-").append(actionName).append(".xml").toString();
		return springConf;
	}
	public String getServiceConf(String actionName) {
		StringBuffer sb = new StringBuffer("");
		serviceConf = sb.append("applicationContext-service-").append(actionName).append(".xml").toString();
		return serviceConf;
	}
}
