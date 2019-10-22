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
import com.dimine.base.page.Pager;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sc.entity.T_sc_teamtechprocessEntity;
import com.dimine.sc.service.T_sc_teamtechprocessService;

/**
 * 用于对工序对应班组进行系列的操作的action
 * 
 * @author dimine 2016-08-11 10:54:09
 * 
 */
@Namespace("/webpage/biz/sc/teamtechprocess")
@Scope("request")
@Controller("t_sc_teamtechprocessAction")
public class T_sc_teamtechprocessAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_sc_teamtechprocessAction.class);
	// 业务处理
	@Resource
	private T_sc_teamtechprocessService<T_sc_teamtechprocessEntity> t_sc_teamtechprocessService;

	// 参数实体
	private T_sc_teamtechprocessEntity bean = new T_sc_teamtechprocessEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对工序对应班组进行列表查询操作
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
			bean.setTdeptid(this.getLoginUser().getOrgid());
			List<T_sc_teamtechprocessEntity> dataList = t_sc_teamtechprocessService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	

	/**
	 * 对工序对应班组进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list1", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list1() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setTdeptid(this.getLoginUser().getOrgid());
			List<T_sc_teamtechprocessEntity> dataList = t_sc_teamtechprocessService.selectTeam(bean);
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
	 * 对工序对应班组进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list2", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list2() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setTdeptid(this.getLoginUser().getOrgid());
			List<T_sc_teamtechprocessEntity> dataList = t_sc_teamtechprocessService.selectTeamByProc(bean);
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
	 * 新增工序对应班组信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/sc/teamtechprocess/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增工序对应班组信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_sc_teamtechprocessService.insert(bean);
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
	 * 删除工序对应班组信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_sc_teamtechprocessService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对工序对应班组进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/sc/teamtechprocess/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_sc_teamtechprocessService.selectById(bean.getTeamid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对工序对应班组进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			t_sc_teamtechprocessService.update(bean, getActiontype());				
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
	 * 批量维护工序和班组之间的关系
	 * @return
	 */
	@Action(value = "addProcTeam", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String addTechProc() {
		try {
			//先删除该工序下面的所有对应的班组,再进行信息的插入
			t_sc_teamtechprocessService.delete(bean);
			//接受前台同步的数据
			if("".equals(bean.getTeamid())){
				this.setJsonStr("n");
				return SUCCESS;
			}
			String[] teamid=bean.getTeamid().split(",");
			T_sc_teamtechprocessEntity entity=new T_sc_teamtechprocessEntity();
			entity.setProcid(bean.getProcid());
			for(int i=0;i<teamid.length;i++){
				entity.setTeamid(teamid[i]);
				entity.setTdeptid(this.getLoginUser().getOrgid());
				t_sc_teamtechprocessService.insert(entity);
			}
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr("y");
		return SUCCESS;
	}
	@Action(value = "getPosition", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String getPosition() throws Exception{
		Pager pager = this.getPager();
		pager.setPageSize(100);
		bean.setPager(pager);
		String positions="";
		bean.setTdeptid(this.getLoginUser().getOrgid());
		List<T_sc_teamtechprocessEntity> teamlist = t_sc_teamtechprocessService.selectTeamByGx(bean);
		List<T_sc_teamtechprocessEntity> dataList = t_sc_teamtechprocessService.selectTeam(bean);
		for(int i =0 ;i<dataList.size();i++){
			for(int j = 0 ;j<teamlist.size() ;j++){
				if(teamlist.get(j).getTeamid().equals(dataList.get(i).getTeamid())){
					//System.out.println("yw:"+busiteamEntitylist.get(j).getTeamid()+":all"+dataList.get(i).getTeamid());
					positions=positions+(i+1)+',';
					continue;
				}
			}
		}
		if(positions.length()==0){
			this.setJsonStr(positions);
		}else{
			this.setJsonStr(positions.substring(0, positions.length()-1));
		}
		return SUCCESS;
	}
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "工序对应班组信息管理";
	}

	public T_sc_teamtechprocessEntity getBean() {
		return bean;
	}

	public void setBean(T_sc_teamtechprocessEntity bean) {
		this.bean = bean;
	}

	public T_sc_teamtechprocessService<T_sc_teamtechprocessEntity> getT_sc_teamtechprocessService() {
		return t_sc_teamtechprocessService;
	}

	public void setT_sc_teamtechprocessService(T_sc_teamtechprocessService<T_sc_teamtechprocessEntity> t_sc_teamtechprocessService) {
		this.t_sc_teamtechprocessService = t_sc_teamtechprocessService;
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
