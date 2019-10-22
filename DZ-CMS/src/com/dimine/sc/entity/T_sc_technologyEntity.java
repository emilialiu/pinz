package com.dimine.sc.entity;

import com.dimine.base.entity.BaseEntity;

/**
 * 工艺信息信息类
 * 
 * @author dimine 2016-06-30 11:11:37
 * 
 */
public class T_sc_technologyEntity extends BaseEntity {

	/** 工艺ID */
	private String techid = "";
	/** 所属矿山 */
	private String tdeptid = "";
	/** 工艺名称 */
	private String techname = "";
	/** 工艺英文名称 */
	private String technameen = "";
	/** 工艺编码 */
	private String techcode = "";
	/** 简称 */
	private String shortname = "";
	/** 上级工艺 */
	private String parentid;
	/** 计划阶段使用：0表示不使用，1表示使用 */
	private String isplan = "";
	/** 验收阶段使用：0表示不使用，1表示使用 */
	private String isaccept = "";
	/** 是否删除，0,未删除;1表示删除 */
	private String isdel = "";
	/** 是否叶子节点 */
	private String isleaf = "";
	/** 上级编码拼串 */
	private String parentstr = "";
	/** 序号 */
	private String serialno = "";
	/** 创建人 */
	private String createid = "";
	/** 创建日期 */
	private String createdate = "";
	/** 修改人 */
	private String modifyid = "";
	/** 修改日期 */
	private String modifydate = "";
	/** 备注 */
	private String memo = "";
	
	/** 節點等級*/
	private int level;
	/** 是否展開*/
	private String expanded;
	/** 是否葉子節點*/
	private String leaf;
	/** */
	private String parent;
	/** 儿子数量*/
	private String childCount="";
	/** 父节点名称*/
	private String parentname;
	private String teamid;

	public String getTechid() {
		return techid;
	}
	public void setTechid(String techid) {
		this.techid = techid;
	}
	public String getTdeptid() {
		return tdeptid;
	}
	public void setTdeptid(String tdeptid) {
		this.tdeptid = tdeptid;
	}
	public String getTechname() {
		return techname;
	}
	public void setTechname(String techname) {
		this.techname = techname;
	}
	public String getTechnameen() {
		return technameen;
	}
	public void setTechnameen(String technameen) {
		this.technameen = technameen;
	}
	public String getTechcode() {
		return techcode;
	}
	public void setTechcode(String techcode) {
		this.techcode = techcode;
	}
	public String getShortname() {
		return shortname;
	}
	public void setShortname(String shortname) {
		this.shortname = shortname;
	}
	public String getParentid() {
		return parentid;
	}
	public void setParentid(String parentid) {
		this.parentid = parentid;
	}
	public String getIsplan() {
		return isplan;
	}
	public void setIsplan(String isplan) {
		this.isplan = isplan;
	}
	public String getIsaccept() {
		return isaccept;
	}
	public void setIsaccept(String isaccept) {
		this.isaccept = isaccept;
	}
	public String getIsdel() {
		return isdel;
	}
	public void setIsdel(String isdel) {
		this.isdel = isdel;
	}
	public String getIsleaf() {
		return isleaf;
	}
	public void setIsleaf(String isleaf) {
		this.isleaf = isleaf;
	}
	public String getParentstr() {
		return parentstr;
	}
	public void setParentstr(String parentstr) {
		this.parentstr = parentstr;
	}
	public String getSerialno() {
		return serialno;
	}
	public void setSerialno(String serialno) {
		this.serialno = serialno;
	}
	public String getCreateid() {
		return createid;
	}
	public void setCreateid(String createid) {
		this.createid = createid;
	}
	public String getCreatedate() {
		return createdate;
	}
	public void setCreatedate(String createdate) {
		this.createdate = createdate;
	}
	public String getModifyid() {
		return modifyid;
	}
	public void setModifyid(String modifyid) {
		this.modifyid = modifyid;
	}
	public String getModifydate() {
		return modifydate;
	}
	public void setModifydate(String modifydate) {
		this.modifydate = modifydate;
	}
	public String getMemo() {
		return memo;
	}
	public void setMemo(String memo) {
		this.memo = memo;
	}
	public int getLevel() {
		return level;
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public String getExpanded() {
		return expanded;
	}
	public void setExpanded(String expanded) {
		this.expanded = expanded;
	}
	public String getLeaf() {
		return leaf;
	}
	public void setLeaf(String leaf) {
		this.leaf = leaf;
	}
	public String getParent() {
		return parent;
	}
	public void setParent(String parent) {
		this.parent = parent;
	}
	public String getChildCount() {
		return childCount;
	}
	public void setChildCount(String childCount) {
		this.childCount = childCount;
	}
	public String getParentname() {
		return parentname;
	}
	public void setParentname(String parentname) {
		this.parentname = parentname;
	}
	public String getTeamid() {
		return teamid;
	}
	public void setTeamid(String teamid) {
		this.teamid = teamid;
	}
	
	
}
