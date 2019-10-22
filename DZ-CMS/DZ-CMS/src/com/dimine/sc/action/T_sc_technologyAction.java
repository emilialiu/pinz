package com.dimine.sc.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.common.ValidException;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sc.entity.T_sc_technologyEntity;
import com.dimine.sc.service.T_sc_technologyService;

/**
 * 用于对工艺信息进行系列的操作的action
 * 
 * @author dimine 2016-06-30 11:11:37
 * 
 */
@Namespace("/webpage/biz/sc/technology")
@Scope("request")
@Controller("t_sc_technologyAction")
public class T_sc_technologyAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_technologyAction.class);
	// 业务处理
	@Resource
	private T_sc_technologyService<T_sc_technologyEntity> t_sc_technologyService;

	// 参数实体
	private T_sc_technologyEntity bean = new T_sc_technologyEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	@Action(value = "doEnter", results = { @Result(name = "success", location = "/webpage/biz/sc/technology/main.jsp") })
	public String doEnter(){
		return SUCCESS;
	}
	/**
	 * 加载工艺单元树
	 * @return
	 */
	@Action(value = "tree", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String tree() {
		try {
			if (StringUtils.isNotBlank(bean.getTechid())) {				
				bean.setTdeptid(this.getLoginUser().getOrgid());
			}
			List<T_sc_technologyEntity> processList = t_sc_technologyService
					.getGyList(bean);
			String jsonData = createJsonData(processList);
			System.out.println(jsonData);
			getRequest().setAttribute("jsonString", jsonData);
		} catch (Exception ee) {
			setErrorMessage(ee);
			ee.printStackTrace();
		}
		return "success";
	}
	
	/**
	 * 拼接json字符串
	 * @param projectList
	 * @return
	 */
	private String createJsonData(List<T_sc_technologyEntity> projectList) {
		StringBuffer jsonData = new StringBuffer();
		int length = projectList.size();
		if (length <= 0)
			return "";
		for (int i = 0; i < length; i++) {
			T_sc_technologyEntity technology = projectList.get(i);
			jsonData.append("{\"id\":\"");
			jsonData.append(technology.getTechid());
			jsonData.append("\", \"pid\":\"");
			if (technology.getParentid() == null
					|| technology.getParentid() == "")
				jsonData.append("");
			else
				jsonData.append(technology.getParentid());
			jsonData.append("\", \"name\":\"");
			jsonData.append(technology.getTechname());
			if (Integer.valueOf(technology.getChildCount()).intValue() >= 1) {
				jsonData.append("\", \"isParent\":\"");
				jsonData.append("true");
				jsonData.append("\", \"close\":\"");
				jsonData.append("true");
				jsonData.append("\"},");
			} else {
				jsonData.append("\", \"isChild\":\"");
				jsonData.append("true");
				jsonData.append("\", \"open\":\"");
				jsonData.append("false");
				jsonData.append("\"},");
			}
		}

		String _jsonData = jsonData.toString();
		length = _jsonData.length();
		return _jsonData.substring(0, length - 1);
	}
	/**
	 * 对工艺信息进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.getPager().setOrderField("techcode,serialno");
			if (StringUtils.isNotBlank(bean.getTechid())) {				
				bean.setTdeptid(this.getLoginUser().getOrgid());
			}
			List<T_sc_technologyEntity> dataList = t_sc_technologyService.selectByList(bean);
			for(int i=0;i<dataList.size();i++){
				if("0".equals(dataList.get(i).getIsleaf())){
					dataList.get(i).setLeaf("false");
				}else if("1".equals(dataList.get(i).getIsleaf())){
					dataList.get(i).setLeaf("true");
				}
				
//				if(dataList.get(i).getParentstr()==null||"".equals(dataList.get(i).getParentstr())){
//					dataList.get(i).setLevel(0);
//				}else{
//					String[] parent=dataList.get(i).getParentstr().split(",");
//					dataList.get(i).setLevel(parent.length);
//				}
//				dataList.get(i).setParent(dataList.get(i).getParentid());
//				if("0".equals(dataList.get(i).getIsleaf())){
//					dataList.get(i).setLeaf(false);
//				}else if("1".equals(dataList.get(i).getIsleaf())){
//					dataList.get(i).setLeaf(true);
//				}
				dataList.get(i).setExpanded("true");
			}
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
			System.out.println(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 批量更新工序信息是否计划和验收
	 * @return
	 */
	@Action(value = "updateTech", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String updateTech() {
		try {
			String[] techid=bean.getTechid().split(",");
			String[] isplan=bean.getIsplan().split(",");
			String[] isaccept=bean.getIsaccept().split(",");
			T_sc_technologyEntity entity=new T_sc_technologyEntity();
			for(int i=0;i<techid.length;i++){
				entity.setTechid(techid[i]);
				entity.setIsplan(isplan[i]);
				entity.setIsaccept(isaccept[i]);
				entity.setModifyid(this.getLoginUser().getUserid());
				t_sc_technologyService.updateTech(entity);
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	/**
	 * 新增工艺信息信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/technology/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增工艺信息信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String doAddSave() {
		try {
			bean.setCreateid(getLoginUser().getUserid());
			bean.setTdeptid(getLoginUser().getOrgid());
			bean.setIsdel("0");
			bean.setIsleaf("1");
			bean.setLevel(bean.getLevel()+1);
			bean.setParentstr(t_sc_technologyService.getParentStr(bean));
			t_sc_technologyService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除工艺信息信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			//逻辑删除自己和本身下面所有的下级节点
			t_sc_technologyService.delete(bean);
			//查询该节点的父节点是否有孩子节点，如果没有孩子节点，则更新它为叶子节点
			if(t_sc_technologyService.selectChildCount(bean)==0){
				t_sc_technologyService.updateParentLeafInfo(bean);
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对工艺信息进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/technology/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_technologyService.selectById(bean.getTechid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对工艺信息进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setModifyid(this.getLoginUser().getUserid());
   			t_sc_technologyService.update(bean, getActiontype());				
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	/**
	 * 通过工艺id返回工艺信息
	 */
	@Action(value = "modify", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String modify() {
		try {
			bean = t_sc_technologyService.selectById(bean.getTechid());				
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	
	/**
	 * 对生产工艺查询操作
	 * 
	 * @return
	 */
	@Action(value = "selectlist", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectlist() {
		try {
			List<T_sc_technologyEntity> datalist = t_sc_technologyService
					.selectList();
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (T_sc_technologyEntity data : datalist) {
					sbf.append("'").append(data.getTechid()).append("':'")
							.append(data.getTechname()).append("',");
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
	 * 对生产工艺查询操作(日，周报表查询工艺)
	 * 
	 * @return
	 */
	@Action(value = "selectByReport", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectByReport() {
		try {
			bean.setTdeptid(this.getLoginUser().getOrgid());
			List<T_sc_technologyEntity> datalist = t_sc_technologyService
					.selectByReport(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (T_sc_technologyEntity data : datalist) {
					sbf.append("'").append(data.getTechid()).append("':'")
					.append(data.getTechname()).append("',");
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
	 * 首页查询工艺
	 * 
	 * @return
	 */
	@Action(value = "selectlistForSY", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String selectlistForSY() {
		try {
			List<T_sc_technologyEntity> datalist = t_sc_technologyService.selectList();
			// 设置页面数据
			this.setJsonStr(JSONUtil.toJSONString(datalist));
		} catch (Exception ex) {
			this.setErrorMessage(ex);

		}
		
		return SUCCESS;
	}
	
	
	/**
	 * 根据班组对生产工艺查询操作
	 * 
	 * @return
	 */
	@Action(value = "selectListByTeamid", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String selectListByTeamid() {
		try {
			List<T_sc_technologyEntity> datalist = t_sc_technologyService
					.selectListByTeamid(bean);
			if (datalist != null && datalist.size() > 0) {
				StringBuffer sbf = new StringBuffer();
				for (T_sc_technologyEntity data : datalist) {
					sbf.append("'").append(data.getTechid()).append("':'")
					.append(data.getTechname()).append("',");
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工艺信息信息管理";
	}

	public T_sc_technologyEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_technologyEntity bean) {
		this.bean = bean;
	}

	public T_sc_technologyService<T_sc_technologyEntity> getT_sc_technologyService() {
		return t_sc_technologyService;
	}

	public void setT_sc_technologyService(T_sc_technologyService<T_sc_technologyEntity> t_sc_technologyService) {
		this.t_sc_technologyService = t_sc_technologyService;
	}

	public String getAddtype() {
		return addtype;
	}

	public void setAddtype(String addtype) {
		this.addtype = addtype;
	}

	public String getSomename() {
		return somename;
	}

	public void setSomename(String somename) {
		this.somename = somename;
	}

	public String getEncode() {
		return encode;
	}

	public void setEncode(String encode) {
		this.encode = encode;
	}

	public String getParam() {
		return param;
	}

	public void setParam(String param) {
		this.param = param;
	}

	public String getFilters() {
		return filters;
	}

	public void setFilters(String filters) {
		this.filters = filters;
	}
	
}
