package com.dimine.pub.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.ParentPackage;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bi.entity.T_bi_threeoreEntity;
import com.dimine.pub.entity.SelectListEntity;
import com.dimine.pub.service.SelectListService;

@Namespace("/pub/select/selectList")
@Scope("request")
@Controller("SelectListAction")
public class SelectListAction extends BaseAction {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Resource
	private SelectListService<SelectListEntity> selectListService;

	private SelectListEntity bean = new SelectListEntity();
	private String dicttypeid = "";
	private String businesstype = "";
	private String projectlevel = "";
	private String cptype = "";
	private String projectid = "";
	private String projecttype = "";
	private String detailid = "";

	/**
	 * 对字典值列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String list() {
		try {
 			List<SelectListEntity> datalist = selectListService
					.selectList(dicttypeid);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (SelectListEntity data : datalist) {
					sbf.append("'").append(data.getValue()).append("':'")
							.append(data.getText()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
				System.out.println(_jsonData.substring(0, length - 1));
			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		return SUCCESS;
	}

	/**
	 * 对字典值列表查询操作 限制查询
	 * 
	 * @return
	 */
	@Action(value = "limitlist", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String limitlist() {
		try {
			List<SelectListEntity> datalist = selectListService
					.selectlimitList(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (SelectListEntity data : datalist) {
					sbf.append("'").append(data.getValue()).append("':'")
							.append(data.getText()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));

			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		return SUCCESS;
	}
	
	/**
	 * 查询出所有的设备信息列表
	 * @return
	 */
	@Action(value = "listdev", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String listdev(){
		try {
			bean.setDeptid(this.getLoginUser().getDeptid());
			List<SelectListEntity> datalist = selectListService
					.selectdevlist(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (SelectListEntity data : datalist) {
					sbf.append("'").append(data.getValue()).append("':'")
							.append(data.getText()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));

			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		return SUCCESS;
	}
	/**
	 * 根据工序ID和班组ID查询他们都配置了的设备信息列表
	 * @return
	 */
	@Action(value = "listdevs", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String listdevs(){
		try {
			bean.setDeptid(this.getLoginUser().getDeptid());
			List<SelectListEntity> datalist = selectListService
					.selectDevByProTea(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (SelectListEntity data : datalist) {
					sbf.append("'").append(data.getValue()).append("':'")
					.append(data.getText()).append("',");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
				
			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			
		}
		return SUCCESS;
	}

	/**
	 * 查询班组下拉列表值
	 * @return
	 */
	@Action(value = "selectClassList", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectClassList() {	
		try{
			List<SelectListEntity> datalist = selectListService.selectClassList(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (SelectListEntity data : datalist) {
					sbf.append(data.getValue()).append(":")
					.append(data.getText()).append(",");
				}
				String _jsonData = sbf.toString();
				int length = _jsonData.length();
				this.setJsonStr(_jsonData.substring(0, length - 1));
				
			} else {
				this.setJsonStr("");
			}
		} catch (Exception ex) {
			this.setErrorMessage(ex);
			
		}
		return SUCCESS;
	}
	
	
	@Override
	public String getBiztypename() {
		return null;
	}

	public SelectListService<SelectListEntity> getSelectListService() {
		return selectListService;
	}

	public void setSelectListService(
			SelectListService<SelectListEntity> selectListService) {
		this.selectListService = selectListService;
	}

	public String getDicttypeid() {
		return dicttypeid;
	}

	public void setDicttypeid(String dicttypeid) {
		this.dicttypeid = dicttypeid;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public SelectListEntity getBean() {
		return bean;
	}

	public void setBean(SelectListEntity bean) {
		this.bean = bean;
	}

	public String getProjectlevel() {
		return projectlevel;
	}

	public void setProjectlevel(String projectlevel) {
		this.projectlevel = projectlevel;
	}

	public String getCptype() {
		return cptype;
	}

	public void setCptype(String cptype) {
		this.cptype = cptype;
	}

	public String getProjectid() {
		return projectid;
	}

	public void setProjectid(String projectid) {
		this.projectid = projectid;
	}

	public String getProjecttype() {
		return projecttype;
	}

	public void setProjecttype(String projecttype) {
		this.projecttype = projecttype;
	}

	public String getDetailid() {
		return detailid;
	}

	public void setDetailid(String detailid) {
		this.detailid = detailid;
	}

}
