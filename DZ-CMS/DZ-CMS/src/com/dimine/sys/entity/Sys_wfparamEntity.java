package com.dimine.sys.entity;
import com.dimine.base.entity.BaseEntity;
/**
 * 流程实例
 * @author Administrator
 *
 */
public class Sys_wfparamEntity extends BaseEntity{
	private String wfkey;
	private String infouri;
	private String nodeaction;
	public String getWfkey() {
		return wfkey;
	}
	public void setWfkey(String wfkey) {
		this.wfkey = wfkey;
	}
	public String getInfouri() {
		return infouri;
	}
	public void setInfouri(String infouri) {
		this.infouri = infouri;
	}
	public String getNodeaction() {
		return nodeaction;
	}
	public void setNodeaction(String nodeaction) {
		this.nodeaction = nodeaction;
	}
	
}
