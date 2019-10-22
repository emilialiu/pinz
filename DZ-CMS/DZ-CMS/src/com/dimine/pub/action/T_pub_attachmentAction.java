package com.dimine.pub.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.common.ValidException;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bogen.util.FileUtil;
import com.dimine.pub.entity.T_pub_attachmentEntity;
import com.dimine.pub.service.T_pub_attachmentService;
import com.dimine.util.FileUploadUntil;
import com.dimine.util.StringUtils;

/**
 * 用于对附件表(T_PUB_Attachment)进行系列的操作的action
 * 
 * @author dimine 2017-01-13 15:25:40
 * 
 */
@Namespace("/webpage/biz/pub/attachment")
@Scope("request")
@Controller("t_pub_attachmentAction")
public class T_pub_attachmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(T_pub_attachmentAction.class);
	// 业务处理
	@Resource
	private T_pub_attachmentService<T_pub_attachmentEntity> t_pub_attachmentService;

	// 参数实体
	private T_pub_attachmentEntity bean = new T_pub_attachmentEntity();
	private T_pub_attachmentEntity attachment = new T_pub_attachmentEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;
	private String fileName;// 文件名
	private InputStream inputStream = null;

	/**
	 * 对附件表(T_PUB_Attachment)进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<T_pub_attachmentEntity> dataList = t_pub_attachmentService
					.selectByList(bean);
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
	
	@Action(value = "listm", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String listm() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallbackImpl());
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<T_pub_attachmentEntity> dataList = t_pub_attachmentService
					.selectByListm(bean);
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
	 * 新增附件表(T_PUB_Attachment)信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/biz/pub/attachment/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}
	
	@Action(value = "doAddm", results = { @Result(name = "success", location = "/webpage/biz/bi/attachmentmanage/info.jsp") })
	public String doAddm() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增附件表(T_PUB_Attachment)信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			//bean.setBusinessid(bean.getInfoid());
			bean.setAttachmenttype(FileUploadUntil.getAttchmenttype(bean.getFilename()));
			bean.setAttachmenturl(bean.getFilepath());
			bean.setAttachmentname(bean.getFilename());
			bean.setCreateid(this.getLoginUser().getUserid());
			t_pub_attachmentService.insert(bean);
			bean = t_pub_attachmentService.selectById(bean.getAttachmentid());
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
	 * 删除附件表(T_PUB_Attachment)信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			bean = t_pub_attachmentService.selectById(bean.getAttachmentid());
			FileUtil.deletefile(getRequest().getRealPath("/upfile")+bean.getAttachmenturl());
			t_pub_attachmentService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除附件表(T_PUB_Attachment)信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "deleteBybizid", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String deleteBybizid() {
		try {
			t_pub_attachmentService.deleteBybizid(bean.getBusinessid());
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对附件表(T_PUB_Attachment)进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/biz/pub/attachment/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = t_pub_attachmentService.selectById(bean.getAttachmentid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对附件表(T_PUB_Attachment)进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			T_pub_attachmentEntity temp = t_pub_attachmentService.selectById(bean.getAttachmentid());
			List<T_pub_attachmentEntity> list = t_pub_attachmentService.selectByBusinessid(attachment);
			if(StringUtils.isNotEmpty(bean.getAttachmenturl())){
				if(!bean.getAttachmenturl().equals(temp.getAttachmenturl())){//如果修改的时候上传了新的图片，则要先删除旧图片，再更新附件记录
					if (StringUtils.isNotEmpty(temp.getAttachmenturl())) {
						FileUtil.deletefile(temp.getAttachmenturl());
					}
					
				}
				
			}
			// 修改附件信息
			attachment.setAttachmentid(bean.getAttachmentid());
			attachment.setBusinessid(bean.getInfoid());
			attachment.setAttachmentname(bean.getFilename());
			attachment.setAttachmenturl(bean.getFilepath());
			attachment.setModifyid(this.getLoginUser().getUserid());
			t_pub_attachmentService.update(attachment);
			//t_pub_attachmentService.update(bean, getActiontype());
			bean = t_pub_attachmentService.selectById(bean.getAttachmentid());
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
	 * 文件下载
	 * 
	 * @return
	 */
	@Action(value = "toDownload", results = {
			@Result(name = "success", type = "chain", location = "downLoadFile"),
			@Result(name = "failure", location = "/webpage/pub/download_error.jsp") })
	public String toDownload() {
		String returnflag = SUCCESS;
		try {
			T_pub_attachmentEntity attachmentEntity = new T_pub_attachmentEntity();
			if (StringUtils.isNotEmpty(bean.getBusinessid())) {
				List<T_pub_attachmentEntity> attachmentlist = t_pub_attachmentService
						.selectByBusinessid(bean);
				if (attachmentlist == null || attachmentlist.isEmpty()) {
					return "failure";
				}
				attachmentEntity = attachmentlist.get(0);
			}
			if (StringUtils.isNotEmpty(bean.getAttachmentid())) {
				attachmentEntity = t_pub_attachmentService.selectById(bean
						.getAttachmentid());
			}
			if (attachmentEntity == null
					|| StringUtils.isEmpty(attachmentEntity.getAttachmenturl())) {
				return "failure";
			}
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("upfile")+attachmentEntity.getAttachmenturl());
			if (!file.exists()) {
				returnflag = "failure";
			} else {
				returnflag = SUCCESS;
			}
		} catch (Exception e) {
			this.setErrorMessage("下载失败！");
		}
		return returnflag;
	}

	/**
	 * 文件下载
	 * 
	 * @return
	 */
	@Action(value = "downLoadFile", results = { @Result(params = {
			"contentType", "application/octet-stream", "inputName",
			"inputStream", "contentDisposition",
			"attachment;filename=\"${fileName}\"", "bufferSize", "4096" }, name = "success", type = "stream") })
	public String downLoadFile() {
		try {
			T_pub_attachmentEntity attachmentEntity = new T_pub_attachmentEntity();
			if (StringUtils.isNotEmpty(bean.getBusinessid())) {
				List<T_pub_attachmentEntity> attachmentlist = t_pub_attachmentService
						.selectByBusinessid(bean);
				attachmentEntity = attachmentlist.get(0);
			}
			if (StringUtils.isNotEmpty(bean.getAttachmentid())) {
				attachmentEntity = t_pub_attachmentService.selectById(bean
						.getAttachmentid());
			}
			fileName = new String(attachmentEntity.getAttachmentname()
					.getBytes("gb2312"), "ISO8859-1");
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("upfile")+attachmentEntity.getAttachmenturl());
			if (!file.exists()) {
				this.setErrorMessage("文件不存在！");
			} else {
				inputStream = new FileInputStream(file);
				System.out.println(inputStream);
			}
		} catch (Exception e) {
			this.setErrorMessage("下载失败！");
		}
		return SUCCESS;
	}
	
	@Action(value = "saveFile", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String saveFile() {
		try {
			bean.setCreateid(getLoginUser().getUserid());
			t_pub_attachmentService.insert(bean);
			bean = t_pub_attachmentService.selectById(bean.getAttachmentid());
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

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "附件表(T_PUB_Attachment)信息管理";
	}

	public T_pub_attachmentEntity getBean() {
		return bean;
	}

	public void setBean(T_pub_attachmentEntity bean) {
		this.bean = bean;
	}

	public T_pub_attachmentService<T_pub_attachmentEntity> getT_pub_attachmentService() {
		return t_pub_attachmentService;
	}

	public void setT_pub_attachmentService(
			T_pub_attachmentService<T_pub_attachmentEntity> t_pub_attachmentService) {
		this.t_pub_attachmentService = t_pub_attachmentService;
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

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public InputStream getInputStream() {
		return inputStream;
	}

	public void setInputStream(InputStream inputStream) {
		this.inputStream = inputStream;
	}

}
