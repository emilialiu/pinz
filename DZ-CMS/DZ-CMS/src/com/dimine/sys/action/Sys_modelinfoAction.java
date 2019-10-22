package com.dimine.sys.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
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
import com.dimine.base.util.json.JSONUtil;
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.common.ValidException;
import com.dimine.sys.entity.Sys_modelinfoEntity;
import com.dimine.sys.service.Sys_modelinfoService;

/**
 * 用于对EXCEL导入模板信息表进行系列的操作的action
 * 
 * @author dimine 2014-12-18 15:45:29
 * 
 */
@Namespace("/manager/sys/modelinfo")
@Scope("request")
@Controller("sys_modelinfoAction")
public class Sys_modelinfoAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Sys_modelinfoAction.class);
	// 业务处理
	@Resource
	private Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService;

	// 参数实体
	private Sys_modelinfoEntity bean = new Sys_modelinfoEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	
	private File file = null;//上传文件
	private String modelid = "";//ID
	private String suffix = "";//上传文件后缀
	private String fileName = "";//保存文件名(modelid+"."+suffix)
	private String savePath = "";//保存文件路径
	 

	/**
	 * 对EXCEL导入模板信息表进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
	public String list() {
		try {
			if(filters!=null){
				String query = new SQLTool().constructWhere(filters, new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<Sys_modelinfoEntity> dataList = sys_modelinfoService.selectByList(bean);
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
	 * 新增EXCEL导入模板信息表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAdd() {
		String modelid = KeyUtils.createKeyID();
		System.out.println("--modelid-->"+modelid);
		this.setJsonStr(modelid);
		return SUCCESS;
	}

	/**
	 * 新增EXCEL导入模板信息表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAddSave() {
		try {
			sys_modelinfoService.insert(bean);
			this.setJsonStr(bean.getModelid());
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
	 * 删除EXCEL导入模板信息表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doDelete() {
		try {
			sys_modelinfoService.delete(bean);
			this.setJsonStr(bean.getModelid());
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 对EXCEL导入模板信息表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doModify() {
		try {			
			bean = sys_modelinfoService.selectById(bean);
			this.setJsonStr("["+JSONUtil.toJSONString(bean)+"]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对EXCEL导入模板信息表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModifySave() {
		try {
   			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				sys_modelinfoService.update(bean, getActiontype());				
			this.setJsonStr(bean.getModelid());
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
	
	@Action(value = "doUpFile", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doUpFile(){
		try {
			// 文件保存
			saveUpfile();
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage(e);
		}
		return SUCCESS;
		
	}

	public boolean saveUpfile() throws IOException{
		File upfile = this.getFile();
		this.savePath = this.getRequest().getRealPath("/upfile/excelmodel/");
		this.setFileName(this.getModelid() + "." + this.getSuffix());//重新命名文件名称(文件后缀保留)
		if (upfile != null && upfile.exists()) {
			File f = new File(this.savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			FileOutputStream fos = new FileOutputStream(this.savePath + "/" + fileName);
			FileInputStream fis = new FileInputStream(upfile);
			// 判断文件大小
			if (fis.available() > 1024 * 1024 * 50) {
				upfile.delete();
				this.setErrorMessage("上传文件不能超过50M");
				return false;

			}
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = fis.read(buffer)) > 0) {
				fos.write(buffer, 0, len);
			}
			fos.close();
			fis.close();
		} else {
			this.setErrorFlag("false");
			return false;
		}		
		return true;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "EXCEL导入模板信息表信息管理";
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public File getFile() {
		return file;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public String getSavePath() {
		return savePath;
	}

	public void setSavePath(String savePath) {
		this.savePath = savePath;
	}

	public Sys_modelinfoEntity getBean() {
		return bean;
	}

	public void setBean(Sys_modelinfoEntity bean) {
		this.bean = bean;
	}

	public Sys_modelinfoService<Sys_modelinfoEntity> getSys_modelinfoService() {
		return sys_modelinfoService;
	}

	public void setSys_modelinfoService(Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService) {
		this.sys_modelinfoService = sys_modelinfoService;
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
