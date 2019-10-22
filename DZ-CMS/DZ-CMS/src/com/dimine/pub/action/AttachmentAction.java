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
import com.dimine.base.util.SQLCallback;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.StringUtils;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.bogen.util.FileUtil;
import com.dimine.pub.entity.AttachmentEntity;
import com.dimine.pub.service.AttachmentService;
import com.dimine.util.ConstantUtil;

/**
 * 用于对附件信息进行系列的操作的action
 * 
 * @author dimine 2015-03-10 15:52:56
 * 
 */
@Namespace("/biz/pub/attachment")
@Scope("request")
@Controller("attachmentAction")
public class AttachmentAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger
			.getLogger(AttachmentAction.class);
	// 业务处理
	@Resource
	private AttachmentService<AttachmentEntity> attachmentService;

	// 参数实体
	private AttachmentEntity bean = new AttachmentEntity();
	private String fileName;// 文件名
	private InputStream inputStream = null;
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;

	private String somename;

	private String encode;

	private String param;

	private String filters;

	/**
	 * 对附件信息进行列表查询操作
	 * 
	 * @return
	 */
	@Action(value = "list", results = { @Result(name = "success", location = "/webpage/pub/grid/pager.jsp") })
	public String list() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("bzstypename"))
									return "d.PARAMNAME";
								return f;
							}

							@Override
							public String executeData(String f, String o,
									String d) {
								if (o.equals("bw") || o.equals("en"))
									return (new StringBuilder("'")).append(d)
											.append("%'").toString();
								if (o.equals("ew") || o.equals("en"))
									return (new StringBuilder("'%")).append(d)
											.append("'").toString();
								if (o.equals("cn") || o.equals("nc"))
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			List<AttachmentEntity> dataList = attachmentService
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

	@Action(value = "courseWareList", results = { @Result(name = "success", location = "/pub/grid/pager.jsp") })
	public String courseWareList() {
		try {
			if (filters != null) {
				String query = new SQLTool().constructWhere(filters,
						new SQLCallback() {
							@Override
							public String executeField(String f) {
								if (f.equals("trainingtypename"))
									return "d.PARAMNAME";
								if (f.equals("createdate"))
									return "date_format(p.createdate,'%Y-%m-%d')";
								if (f.equals("createid"))
									return "s.staffname";
								return f;
							}

							@Override
							public String executeData(String f, String o,
									String d) {
								if (o.equals("bw") || o.equals("en"))
									return (new StringBuilder("'")).append(d)
											.append("%'").toString();
								if (o.equals("ew") || o.equals("en"))
									return (new StringBuilder("'%")).append(d)
											.append("'").toString();
								if (o.equals("cn") || o.equals("nc"))
									return (new StringBuilder("'%")).append(d)
											.append("%'").toString();
								else
									return (new StringBuilder("'")).append(d)
											.append("'").toString();
							}
						});
				bean.setQuery(query);
			}
			bean.setPager(this.getPager());
			bean.setBzstype("FJYWLX003");
			List<AttachmentEntity> dataList = attachmentService
					.selectCwList(bean);
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
	 * 新增附件信息信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAddSave() {
		try {
			attachmentService.insert(bean);
			this.setJsonStr(bean.getAttachmentid());
			bean=attachmentService.selectById(bean.getAttachmentid());
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
	 * 删除附件信息信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doDelete() {
		try {
			attachmentService.delete(bean);
			this.setJsonStr(bean.getAttachmentid());
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 对附件信息进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String doModify() {
		try {
			bean = attachmentService.selectById(bean);
			this.setJsonStr("[" + JSONUtil.toJSONString(bean) + "]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	
	/**
	 * 对生产检查整改五定表附件进行修改查询操作
	 */
	@Action(value = "doUploadFileModify", results = { @Result(name = "success", location = "/webpage/biz/pd/perectify/uploadfile.jsp") })
	public String doUploadFileModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}			
			bean = attachmentService.selectById(bean);
			this.getRequest().setAttribute("rectifyid", bean.getBusinessid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
			e.printStackTrace();
			
		}
		return SUCCESS;
	}

	/**
	 * 对附件信息进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/result.jsp") })
	public String doModifySave() {
		try {
			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				attachmentService.update(bean);
				this.setJsonStr(bean.getAttachmentid());
				bean=attachmentService.selectById(bean.getAttachmentid());
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
			AttachmentEntity attachmentEntity = new AttachmentEntity();
			if (StringUtils.isNotBlank(bean.getBusinessid())) {
				List<AttachmentEntity> attachmentlist = attachmentService
						.selectByBusinessid(bean);
				if (attachmentlist == null || attachmentlist.isEmpty()) {
					return "failure";
				}
				attachmentEntity = attachmentlist.get(0);
			}
			if (StringUtils.isNotBlank(bean.getAttachmentid())) {
				attachmentEntity = attachmentService.selectById(bean
						.getAttachmentid());
			}
			if (attachmentEntity == null
					|| StringUtils.isBlank(attachmentEntity.getAttachmenturl())) {
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
			AttachmentEntity attachmentEntity = new AttachmentEntity();
			if (StringUtils.isNotBlank(bean.getBusinessid())) {
				List<AttachmentEntity> attachmentlist = attachmentService
						.selectByBusinessid(bean);
				attachmentEntity = attachmentlist.get(0);
			}
			if (StringUtils.isNotBlank(bean.getAttachmentid())) {
				attachmentEntity = attachmentService.selectById(bean
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

	/**
	 * 文件预览跳转
	 * 
	 * @return
	 */
	@Action(value = "toPreview", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String toPreview() {
		try {
			StringBuffer sbf = new StringBuffer();
			AttachmentEntity attachmentEntity = new AttachmentEntity();
			if (StringUtils.isNotBlank(bean.getBusinessid())) {
				List<AttachmentEntity> attachmentlist = attachmentService
						.selectByBusinessid(bean);
				if (attachmentlist == null || attachmentlist.isEmpty()) {
					sbf.append("({\"success\":\"false\",\"errormessage\":\"文件不存在！\"})");
					this.setJsonStr(sbf.toString());
					return SUCCESS;
				}
				attachmentEntity = attachmentlist.get(0);
			}
			if (StringUtils.isNotBlank(bean.getAttachmentid())) {
				attachmentEntity = attachmentService.selectById(bean
						.getAttachmentid());
			}
			if (attachmentEntity == null
					|| StringUtils.isBlank(attachmentEntity.getAttachmenturl())) {
				sbf.append("({\"success\":\"false\",\"errormessage\":\"文件不存在！\"})");
				this.setJsonStr(sbf.toString());
				return SUCCESS;
			}
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("upfile")+attachmentEntity.getAttachmenturl());
			if (!file.exists()) {
				sbf.append("({\"success\":\"false\",\"errormessage\":\"文件不存在！\"})");
			} else {
				String path = attachmentEntity.getAttachmenturl();
				path = path.replace(getRequest().getRealPath("/upfile"), "")
						.replace("\\", "/");
				String extname = path.substring(path.lastIndexOf("."));
				sbf.append("({\"success\":\"true\",\"extname\":\"")
						.append(extname).append("\",\"filepath\":\"")
						.append(path).append("\"})");
			}
			this.setJsonStr(sbf.toString());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 根据文件路径删除文件
	 * 
	 * @return
	 */
	@Action(value = "delFile", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String delFile() {
		try {
			if (StringUtils.checkNotNull(bean.getAttachmenturl())) {
				// File file = new File(bean.getAttachmenturl());
				// if (file.exists()) {
				String[] str = bean.getAttachmenturl().split("\\\\");
				String filename = str[str.length - 1];
				FileUtil.deletefile(ServletActionContext.getServletContext()
						.getRealPath("upfile")+bean.getAttachmenturl());
				FileUtil.deletefile(ServletActionContext.getServletContext()
						.getRealPath("upfile")+bean.getAttachmenturl().substring(0,bean.getAttachmenturl().lastIndexOf("."))+".swf");
				
				//删除swf文件
				
				// }
				bean.setAttachmenturl(filename);
				if ("1".equals(param)) {
					attachmentService.deleteByUrl(bean);
				}
				attachmentService.updateUrl(bean);
			}
			this.setJsonStr(JSONUtil.toJSONString(bean));
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}
	
	/**
	 * 文件下载
	 * 
	 * @return
	 */
	@Action(value = "downLoadurlFile", results = { @Result(params = {
			"contentType", "application/octet-stream", "inputName",
			"inputStream", "contentDisposition",
			"attachment;filename=\"${fileName}\"", "bufferSize", "4096" }, name = "success", type = "stream") })
	public String downLoadurlFile() throws Exception {
		try {
			
			fileName = new String(bean.getAttachmentname()
					.getBytes("gb2312"), "ISO8859-1");
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("upfile"+bean.getAttachmenturl()));
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
	
	@Action(value = "attpreview", results = { @Result(name = "pic", location = "/webpage/pub/preview/picpreview.jsp")
	,@Result(name = "swf", location = "/webpage/pub/preview/swfpreview.jsp")
	,@Result(name = "pic", location = "/webpage/pub/preview/picpreview.jsp")
	,@Result(name = "sw", location = "/webpage/pub/preview/model/swpreview.jsp")
	,@Result(name = "error", location = "/webpage/pub/preview/error.jsp")
	,@Result(name = "failure", location = "/webpage/pub/preview_error.jsp") })
	public String attpreview(){
		try{
			bean = attachmentService.selectById(bean);
			if (bean == null || StringUtils.isBlank(bean.getAttachmenturl())) {
				return "failure";//文件不存在！
			}
			File file = new File(ServletActionContext.getServletContext()
					.getRealPath("upfile"+bean.getAttachmenturl()));
			if (!file.exists()) {
				return "failure";//文件不存在！
			} else {
				if (bean.getAttachmenttype() != null
						&& (bean.getAttachmenttype().equals(
								ConstantUtil.FJLX_DOC)
								|| bean.getAttachmenttype().equals(
										ConstantUtil.FJLX_PDF)
								|| bean.getAttachmenttype().equals(
										ConstantUtil.FJLX_SWF) || bean
								.getAttachmenttype().equals(
										ConstantUtil.FJLX_XLS))) {
					String str = bean.getAttachmenturl().substring(0,
							bean.getAttachmenturl().lastIndexOf("."))
							+ ".swf";
					File swffile = new File(ServletActionContext
							.getServletContext().getRealPath("upfile") + str);
					// 如果附件不存在，则需要转换
					if (!swffile.exists()) {
						attachmentService.converswf(bean.getAttachmenturl());
					}
					str = str.replaceAll("\\\\", "/");
					getRequest().setAttribute("fileurl", str);
					return "swf";
				}else if(bean.getAttachmenttype()!=null && (bean.getAttachmenttype().equals("FJLX008"))){
					getRequest().setAttribute("fileurl",bean.getAttachmenturl());
					return "pic";
				}else if(bean.getAttachmenttype()!=null && (bean.getAttachmenttype().equals("FJLX006"))){
					getRequest().setAttribute("fileurl",bean.getAttachmenturl());
					return "sw";
				}else{
					return "error";
				}
			}
		}catch (Exception e) {
			bean.setErrorMessage(e);
			e.printStackTrace();
		}
		return "failure";
		
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "附件信息信息管理";
	}

	public AttachmentEntity getBean() {
		return bean;
	}

	public void setBean(AttachmentEntity bean) {
		this.bean = bean;
	}

	public AttachmentService<AttachmentEntity> getAttachmentService() {
		return attachmentService;
	}

	public void setAttachmentService(
			AttachmentService<AttachmentEntity> attachmentService) {
		this.attachmentService = attachmentService;
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
