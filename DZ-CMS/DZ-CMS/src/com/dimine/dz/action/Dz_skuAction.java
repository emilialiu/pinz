package com.dimine.dz.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.annotation.Resource;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.log4j.Logger;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.base.action.BaseAction;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.sys.util.PublicUtil;
import com.dimine.util.StringUtils;
import com.dimine.base.common.ValidException;
import com.dimine.dz.entity.Dz_skuEntity;
import com.dimine.dz.service.Dz_skuService;

/**
 * 用于对规格配置表进行系列的操作的action
 * 
 * @author dimine 2019-09-05 18:58:10
 * 
 */
@Namespace("/webpage/biz/dz/sku")
@Scope("request")
@Controller("dz_skuAction")
public class Dz_skuAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Dz_skuAction.class);
	// 业务处理
	@Resource
	private Dz_skuService<Dz_skuEntity> dz_skuService;

	// 参数实体
	private Dz_skuEntity bean = new Dz_skuEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	 

	/**
	 * 对规格配置表进行列表查询操作
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
			List<Dz_skuEntity> dataList = dz_skuService.selectByList(bean);
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
	 * 对规格配置表进行列表查询操作
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
			List<Dz_skuEntity> dataList = dz_skuService.selectByList2(bean);
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
	 * 新增规格配置表信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/dz/sku/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}
	@Action(value = "doAdd2", results = { @Result(name = "success", location = "/webpage/biz/dz/sku/skusum/info.jsp") })
	public String doAdd2() {
		// TODO 其他业务处理
		System.out.println(bean.getParent_id());
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增规格配置表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			dz_skuService.insert(bean);
		} catch (ValidException ee) {
			bean.setErrorMessage(ee.getMessage());
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}/**
	 * 新增规格配置表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave2", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave2() {
		try {
			dz_skuService.insert(bean);
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
	 * 删除规格配置表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			dz_skuService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对规格配置表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/dz/sku/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = dz_skuService.selectById(bean.getId());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}
	/**
	 * 对规格配置表进行修改查询操作
	 */
	@Action(value = "doModify2", results = { @Result(name = "success", location = "/webpage/biz/dz/sku/skusum/info.jsp") })
	public String doModify2() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = dz_skuService.selectById(bean.getId());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}
	/**
	 * 对规格配置表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
   			dz_skuService.update(bean, getActiontype());				
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
	@Action(value = "addGGPZ", results = { @Result(name = "success", location = "/webpage/biz/commodity/base/infopz.jsp") })
	public String addGGPZ(){
		List<Dz_skuEntity> datalist = dz_skuService.findsku(bean);
		List<Dz_skuEntity> datasumlist = new ArrayList<Dz_skuEntity>();
		StringBuffer htmlstr = new StringBuffer("");
		int i =0;
		for(Dz_skuEntity databean:datalist){
			htmlstr.append("<div class='form-group'>");
			htmlstr.append("<label class='control-label col-xs-12 col-sm-2 no-padding-right'for='"+databean.getLimit_ids()+"'>" +
					databean.getSku_name()+":</label><div class='col-xs-12 col-sm-10'><div class='checkbox col-sm-6'>");
			/*htmlstr.append("<input type='hidden' id='product_category_id' name='product_category_id' value='"+databean.getProduct_category_id()+"'/>");*/
			htmlstr.append("<input type='hidden' id='sku_code' name='sku_code' value='"+databean.getSku_code()+"'/>");
			datasumlist = dz_skuService.findskusum(databean);
			databean.setList1(datasumlist);
			for(Dz_skuEntity sumbean:datasumlist){
				i++;
				htmlstr.append("<label><input type='checkbox' name='"+sumbean.getLimit_ids()+"' id='"+sumbean.getLimit_ids()+"' " +
						"value='"+sumbean.getSku_code()+"' class='ace col-xs-12' /><span class='lbl'>"+sumbean.getSku_name()+"</span></label>");
			}
			htmlstr.append("</div><div class='col-sm-3'></div></div>");
			htmlstr.append("</div>");
		}
		if(dz_skuService.countjson(bean.getId())>=1){
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			String json = dz_skuService.findjson(bean.getId());
			List<String> codelist = new ArrayList<String>();
			if(StringUtils.isNotEmpty(json)){
				System.out.println(json.replaceAll("\"","'"));
				JSONObject data = JSONObject.fromObject(json.replaceAll("\"","'"));
				JSONArray data1 = data.getJSONArray("data");
				for(int s =0;s<data1.size();s++){
					JSONObject data2 = JSONObject.fromObject(data1.getString(s));
					JSONArray data3 = data2.getJSONArray("site");
					for(int j = 0;j<data3.size();j++){
						data3.getString(j);
						JSONObject data4 = JSONObject.fromObject(data3.getString(j));
						codelist.add(data4.getString("code"));
					}
				}
			}
			bean.setCodelist(codelist);
		}else{
			this.setActiontype(ACTIONTYPE_ADDSAVE);
		}
		this.getRequest().setAttribute("actiontype",this.getActiontype());
		this.getRequest().setAttribute("jsonStr",htmlstr.toString());
		bean.setList1(datalist);
		this.getRequest().setAttribute("beanStr",JSONUtil.toJSONString(bean));
		return SUCCESS;
	}
	// 日志文件用
	@Override
	public String getBiztypename() {
		return "规格配置表信息管理";
	}

	public Dz_skuEntity getBean() {
		return bean;
	}

	public void setBean(Dz_skuEntity bean) {
		this.bean = bean;
	}

	public Dz_skuService<Dz_skuEntity> getDz_skuService() {
		return dz_skuService;
	}

	public void setDz_skuService(Dz_skuService<Dz_skuEntity> dz_skuService) {
		this.dz_skuService = dz_skuService;
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
