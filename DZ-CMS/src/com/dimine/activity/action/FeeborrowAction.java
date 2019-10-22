package com.dimine.activity.action;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.activiti.engine.ActivitiException;
import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.activity.entity.FeeborrowEntity;
import com.dimine.activity.service.ActivityService;
import com.dimine.activity.service.FeeborrowService;
import com.dimine.activity.util.Variable;
import com.dimine.base.action.BaseAction;
import com.dimine.base.common.ValidException;
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.sys.action.RoleAction;
import com.dimine.sys.entity.RoleEntity;
/**
 * 借支的action
 * @author Administrator
 *
 */
@Namespace("/manager/activity/feeborrow")
@Scope("request")
@Controller("feeborrowAction")
public class FeeborrowAction extends BaseNodeAction{
	private final static Logger logger = Logger.getLogger(FeeborrowAction.class);
	// 业务处理
	@Resource
	private FeeborrowService<FeeborrowEntity> feeborrowService;
	// 参数实体
	private FeeborrowEntity bean = new FeeborrowEntity();
	//查询条件
	private String filters;
	private String param;
	@Autowired
	private ActivityService activityService;
	/**
	 * 对自己借支列表进行查询
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
	public String list() {
		try {
			System.out.println(filters);
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallback(){

					@Override
					public String executeData(String f, String o, String d) {
						if(o.equals("bw")||o.equals("en"))
							return(new StringBuilder("'")).append(d).append("%'").toString();
						if(o.equals("ew")||o.equals("en"))
							return(new StringBuilder("'%")).append(d).append("'").toString();
						if(o.equals("cn")||o.equals("nc"))
							return(new StringBuilder("'%")).append(d).append("%'").toString();
						else
							return(new StringBuilder("'")).append(d).append("'").toString();
					}

					@Override
					public String executeField(String f) {
						if(f.equals("username")) return "u.username";
						if(f.equals("statename")) return "di.paramname";
						return f;
					}
					
				});
				System.out.println(query);
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setUserid(this.getLoginUser().getUserid());
			List<FeeborrowEntity> dataList = feeborrowService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}

	/**
	 * 新增借支信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			bean.setState("SPZT004");
			bean.setStatename("未审批");
			bean.setUserid(this.getLoginUser().getUserid());
			bean.setCreateid(this.getLoginUser().getUserid());
     		feeborrowService.insert(bean);
     		bean = feeborrowService.selectById(bean.getBorrowid());
			this.setJsonStr(JSONUtil.toJSONString(bean));
		} catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                this.setErrorMessage("没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动请假流程失败：", e);
                this.setJsonStr( "系统内部错误！");
            }
        } catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	/**
	 * 对借支信息进行查看
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doModify() {
		try {
			bean = feeborrowService.selectById(bean.getBorrowid());
			this.setJsonStr("["+JSONUtil.toJSONString(bean)+"]");
			System.out.println(JSONUtil.toJSONString(bean));
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对角色进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModifySave() {
		try {	
			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				bean.setState("LCZT001");
				bean.setUserid(this.getLoginUser().getUserid());
   		        feeborrowService.update(bean);
				this.setJsonStr(bean.getBorrowid());
			}
		} catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		return SUCCESS;
	}
	
	/**
	 * 删除危险源评价信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			feeborrowService.delete(bean);
		} catch (Exception ex) {
			bean.setErrorMessage("failed");
			logger.error(ex);
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	/**
	 * 修改审批状态
	 * @return
	 */
	@Action(value = "doexp", results = { @Result(name = "success", location = "/pub/beanresult.jsp") })
	public String doexp(){
		try{
			Map<String, Object> variables = new HashMap<String, Object>();
			activityService.startWorkflow(this.getLoginUser().getUserid(),"jzlcgl",bean.getBorrowid(), variables);
			bean.setState("SPZT001");
			feeborrowService.modifyflow(bean);
			
		}catch (ActivitiException e) {
            if (e.getMessage().indexOf("no processes deployed with key") != -1) {
                logger.warn("没有部署流程!", e);
                this.setErrorMessage("没有部署流程，请在[工作流]->[流程管理]页面点击<重新部署流程>");
            } else {
                logger.error("启动请假流程失败：", e);
                this.setJsonStr( "系统内部错误！");
            }
        } catch (ValidException ee) {
			this.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		} 
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	public FeeborrowService<FeeborrowEntity> getFeeborrowService() {
		return feeborrowService;
	}
	public void setFeeborrowService(
			FeeborrowService<FeeborrowEntity> feeborrowService) {
		this.feeborrowService = feeborrowService;
	}
	public FeeborrowEntity getBean() {
		return bean;
	}
	public void setBean(FeeborrowEntity bean) {
		this.bean = bean;
	}
	public String getFilters() {
		return filters;
	}
	public void setFilters(String filters) {
		this.filters = filters;
	}
	public String getParam() {
		return param;
	}
	public void setParam(String param) {
		this.param = param;
	}
	
	@Override
	public Map getBizMap(String taskDefinitionKey, String bussinessid) throws Exception {
		// TODO Auto-generated method stub
	    Map<String,Object> obj = new HashMap<String, Object>();
		//如果是总经理审批
		if(taskDefinitionKey.equals("zgssp")){
			FeeborrowEntity fe = feeborrowService.selectById(bussinessid);
			Variable va = new Variable();
			va.setKeys("amount");
			va.setValues(fe.getBorrowamount());
			va.setTypes("F");
			obj =  va.getVariableMap();
		}
		return obj;
	}

	@Override
	public void updatebuiness(String buinnesskey, boolean nodepass)
			throws Exception {
		 FeeborrowEntity fe = feeborrowService.selectById(buinnesskey);
		 if(nodepass){
			 fe.setState("SPZT002");
		 }else{
			 fe.setState("SPZT003");
		 }
	     feeborrowService.modifyflow(fe);
		
	}

	public ActivityService getActivityService() {
		return activityService;
	}

	public void setActivityService(ActivityService activityService) {
		this.activityService = activityService;
	}
	
	
	
}
