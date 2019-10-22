package com.dimine.pub.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
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
import com.dimine.base.util.KeyUtils;
import com.dimine.base.util.SQLCallbackImpl;
import com.dimine.base.util.SQLTool;
import com.dimine.base.util.json.JSONUtil;
import com.dimine.pub.entity.MessageEntity;
import com.dimine.pub.service.MessageService;

/**
 * 用于对通知公告进行系列的操作的action
 * 
 * @author lijun
 * @date 2014-12-10
 * 
 */
@Namespace("/manager/sys/message")
@Scope("request")
@Controller("messageAction")
public class MessageAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(MessageAction.class);

	private File mesfile = null;
	private String mesfileName;
	private String mesfilePath = "";
	private String mestime = "";
	// 业务处理
	@Resource
	private MessageService<MessageEntity> messageService;
	// 参数实体
	private MessageEntity bean = new MessageEntity();

	public MessageEntity getBean() {
		return bean;
	}

	public void setBean(MessageEntity bean) {
		this.bean = bean;
	}

	public String getMestime() {
		return mestime;
	}

	public void setMestime(String mestime) {
		this.mestime = mestime;
	}

	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	private String somename;
	private String encode;
	private String param;
	private String filters;

	/**
	 * 对通知公告信息进行列表查询操作
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
			List<MessageEntity> dataList = messageService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
		} catch (Exception e) {
			this.setErrorMessage(e);
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 新增通知公告信息查询 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/webpage/manager/sys/message/info.jsp") })
	public String doAdd() {
		// TODO 其他业务处理
		this.setActiontype(ACTIONTYPE_ADDSAVE);
		return SUCCESS;
	}

	/**
	 * 新增通知公告信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doAddSave() {
		try {
			if (saveLogoimg() == true) {
				bean.setAddname(getLoginUser().getUserid());
				messageService.insert(bean);
				bean = messageService.selectById(bean.getMessageid());
			}
		} catch (ValidException ee) {
			bean.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 删除通知公告信息信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doDelete() {
		try {
			messageService.delete(bean);
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	/**
	 * 对通知公告信息进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/webpage/manager/sys/message/info.jsp") })
	public String doModify() {
		try {
			if (getActiontype().equals(ACTIONTYPE_MODIFY)) {
				this.setActiontype(ACTIONTYPE_MODIFYSAVE);
			} else if (getActiontype().equals(ACTIONTYPE_VIEW)) {
				this.setActiontype(ACTIONTYPE_VIEW);
			} else if (getActiontype().equals(ACTIONTYPE_DELETE)) {
				this.setActiontype(ACTIONTYPE_DELETESAVE);
			}
			bean = messageService.selectById(bean.getMessageid());
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对通知公告信息进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/webpage/pub/beanresult.jsp") })
	public String doModifySave() {
		try {
			bean.setUpdatename(getLoginUser().getUserid());
			messageService.update(bean, ACTIONTYPE_MODIFYSAVE);
			bean = messageService.selectById(bean.getMessageid());
			this.setMestime(bean.getMestime());
		} catch (ValidException ee) {
			bean.setErrorMessage(ee);
			ee.printStackTrace();
		} catch (Exception e) {
			bean.setErrorMessage("failed");
			e.printStackTrace();
		}
		this.setJsonStr(JSONUtil.toJSONString(bean));
		return SUCCESS;
	}

	@Action(value = "getMessageListByUserid", results = { @Result(name = "success", location = "/webpage/pub/jsondata.jsp") })
	public String getMessageListByUserid() {
		List<MessageEntity> messageList = messageService
				.getMessageListByUserid(getLoginUser().getUserid());
		StringBuffer sbf = new StringBuffer();
		sbf.append("({\"count\":\"").append(messageList.size());
		sbf.append("\",\"list\":[");

		for (int i = 0; i < messageList.size(); i++) {
			MessageEntity message = messageList.get(i);
			sbf.append("{\"id\":\"").append(message.getMessageid())
					.append("\",\"title\":\"")
					.append(message.getMessagetitle()).append("\"}");

			if (i != messageList.size() - 1) {
				sbf.append(",");
			}
		}
		sbf.append("]})");
		this.getRequest().setAttribute("jsonStr", sbf.toString());
		return SUCCESS;
	}

	// 上传文件
	public boolean saveLogoimg() throws Exception {
		try {
			File upfile = this.getMesfile();
			System.out
					.println("upfile===============================" + upfile);
			if (upfile != null && upfile.exists()) {
				this.mesfilePath = ServletActionContext.getServletContext()
						.getRealPath("/UploadFolder/logo/");
				this.mesfileName = KeyUtils.createKeyID() + ".jpg";
				bean.setMesfile("/UploadFolder/logo/" + this.mesfileName);
				File f = new File(this.mesfilePath);
				if (!f.exists()) {
					f.mkdir();
				}
				FileOutputStream fos = new FileOutputStream(this.mesfilePath
						+ "/" + this.mesfileName);
				FileInputStream fis = new FileInputStream(upfile);
				// 判断文件大小
				if (fis.available() > 1024 * 1024 * 10) {
					upfile.delete();
					File f1 = new File(this.mesfilePath + "/"
							+ this.mesfileName);
					if (f1.exists()) {
						f1.delete();
					}
					this.setErrorMessage("上传文件不能超过10M");
					return false;

				}
				byte[] buffer = new byte[1024];
				int len = 0;
				while ((len = fis.read(buffer)) > 0) {
					fos.write(buffer, 0, len);
				}
				fos.close();
				fis.close();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return true;
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "通知公告信息管理";
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

	public MessageService<MessageEntity> getMessageService() {
		return messageService;
	}

	public void setMessageService(MessageService<MessageEntity> messageService) {
		this.messageService = messageService;
	}

	public File getMesfile() {
		return mesfile;
	}

	public void setMesfile(File mesfile) {
		this.mesfile = mesfile;
	}

	public String getMesfileName() {
		return mesfileName;
	}

	public void setMesfileName(String mesfileName) {
		this.mesfileName = mesfileName;
	}

	public String getMesfilePath() {
		return mesfilePath;
	}

	public void setMesfilePath(String mesfilePath) {
		this.mesfilePath = mesfilePath;
	}

}
