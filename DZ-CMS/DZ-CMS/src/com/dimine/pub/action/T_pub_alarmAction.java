package com.dimine.pub.action;

import java.util.Date;
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
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.pub.entity.T_pub_alarmEntity;
import com.dimine.pub.service.T_pub_alarmService;
import com.dimine.sys.util.DateUtil;
import com.dimine.util.ConstantUtil;

/**
 * 用于对告警信息管理进行系列的操作的action
 * 
 * @author dimine 2017-10-26 14:27:39
 * 
 */
@Namespace("/webpage/biz/pub/alarm")
@Scope("request")
@Controller("t_pub_alarmAction")
public class T_pub_alarmAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(T_pub_alarmAction.class);
	// 业务处理
	@Resource
	private T_pub_alarmService<T_pub_alarmEntity> t_pub_alarmService;

	// 参数实体
	private T_pub_alarmEntity bean = new T_pub_alarmEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对告警信息管理进行列表查询操作
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
			List<T_pub_alarmEntity> dataList = t_pub_alarmService.selectByList(bean);
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
	 * 新增告警信息管理信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/pub/alarm/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增告警信息管理信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			t_pub_alarmService.insert(bean);
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
	 * 删除告警信息管理信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			t_pub_alarmService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对告警信息管理进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/pub/alarm/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = t_pub_alarmService.selectById(bean.getAlarmid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对告警信息管理进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setHandletype("CLZT002");
			bean.setHandlename(this.getLoginUser().getUserid());
			bean.setHandletime(DateUtil.getDateWithTime());
   			t_pub_alarmService.update(bean, getActiontype());
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
	 * 配矿定时调度 每天7点和16点自动产生
	 */
	public void generatePk() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180116 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：调度管理员、调度主管、采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.DDY_ROLE + "','"
					+ ConstantUtil.DDZG_ROLE + "','" + ConstantUtil.CKGCS_ROLE
					+ "','" + ConstantUtil.CKZR_ROLE + "','"
					+ ConstantUtil.KSFCCZ_ROLE + "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("配矿");
			tpa.setMemo("配矿定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * 地表更新定时调度 每周一7点自动产生
	 */
	public void generateDbgx() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180116 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：调度管理员、调度主管、采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.DDY_ROLE + "','"
					+ ConstantUtil.DDZG_ROLE + "','" + ConstantUtil.CKGCS_ROLE
					+ "','" + ConstantUtil.CKZR_ROLE + "','"
					+ ConstantUtil.KSFCCZ_ROLE + "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("地表更新");
			tpa.setMemo("地表更新定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * 属性更新定时调度 每周一7点自动产生
	 */
	public void generateSxgx() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180116 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：调度管理员、调度主管、采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.DDY_ROLE + "','"
					+ ConstantUtil.DDZG_ROLE + "','" + ConstantUtil.CKGCS_ROLE
					+ "','" + ConstantUtil.CKZR_ROLE + "','"
					+ ConstantUtil.KSFCCZ_ROLE + "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("属性更新");
			tpa.setMemo("属性更新定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * 月计划 每月20号自动产生待办提醒 编制下个月计划
	 */
	public void generateMonthPlan() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180124 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.CKGCS_ROLE + "','"
					+ ConstantUtil.CKZR_ROLE + "','" + ConstantUtil.KSFCCZ_ROLE
					+ "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("月计划编制");
			tpa.setMemo(DateUtil.addMonth(new Date(), 1).substring(0,7)+"月计划编制定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * 季计划 3/6/9/12月的10号自动产生待办提醒 编制下个季度计划
	 */
	public void generateSeasonPlan() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180124 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.CKGCS_ROLE + "','"
					+ ConstantUtil.CKZR_ROLE + "','" + ConstantUtil.KSFCCZ_ROLE
					+ "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("季度计划");
			tpa.setMemo((DateUtil.getSeason(new Date())+1)+"季度计划编制定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}
	
	/**
	 * 年计划、中长期计划  每年的9月的20号自动产生待办提醒 编制下个年计划、中长期计划
	 */
	public void generateYearPlan() {
		try {
			T_pub_alarmEntity tpa = new T_pub_alarmEntity();
			/*****************************20180124 刘传飞修改****************************/
			//要给以下这些角色发送预警告警信息：采矿工程师、采矿主任、矿山分厂厂长
			tpa.setDdzg("'" + ConstantUtil.CKGCS_ROLE + "','"
					+ ConstantUtil.CKZR_ROLE + "','" + ConstantUtil.KSFCCZ_ROLE
					+ "'");
			List<T_pub_alarmEntity> aList = t_pub_alarmService.findUser(tpa);
			String keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("年计划编制");
			tpa.setMemo((Integer.parseInt(DateUtil.getYear())+1)+"年计划编制定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
			
			keyIDD = KeyUtils.createKeyID();// 代办id
			tpa.setMatterid(keyIDD);
			tpa.setHandletype("CLZT001");
			tpa.setAlarmtitle("中长期计划编制");
			tpa.setMemo((Integer.parseInt(DateUtil.getYear())+1)+"年中长期计划编制定时事务");
			t_pub_alarmService.insetTomatter(tpa);// 代办插入
			for (int i = 0; i < aList.size(); i++) {
				tpa.setUserid(aList.get(i).getUserid());
				tpa.setBizid(keyIDD);
				t_pub_alarmService.insetAlarmBiz(tpa);// 插入业务表
			}
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "告警信息管理信息管理";
	}

	public T_pub_alarmEntity getBean() {
		return bean;
	}

	public void setBean(T_pub_alarmEntity bean) {
		this.bean = bean;
	}

	public T_pub_alarmService<T_pub_alarmEntity> getT_pub_alarmService() {
		return t_pub_alarmService;
	}

	public void setT_pub_alarmService(T_pub_alarmService<T_pub_alarmEntity> t_pub_alarmService) {
		this.t_pub_alarmService = t_pub_alarmService;
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
