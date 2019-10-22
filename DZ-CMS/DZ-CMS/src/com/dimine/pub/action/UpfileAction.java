package com.dimine.pub.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.struts2.ServletActionContext;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.util.KeyUtils;

@Namespace("/pub/upfile")
@Scope("request")
@Controller("upfileAction")
public class UpfileAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	private File file = null;
	private String savePath = "";
	private File avatar = null;
	private String avatarFileName = "";

	@Action(value = "doUpFile", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doUpFile() {
		try {
			// 文件保存
			if (saveUpfile()) {
				// bean.setCreateid(this.getUser().getUserID());
				// t_pub_attachmentService.insert(bean);
				this.setErrorMessage("上传成功");
			}
		} catch (Exception e) {
			e.printStackTrace();
			setErrorMessage(e);
		}
		return SUCCESS;

	}

	/**
	 * 新增文件上传记录信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doImport")
	public void doImport() {
		try {

			String targetFolder = getRequest().getRealPath("/upfile/fangwei");
			String extName = avatarFileName.substring(avatarFileName
					.lastIndexOf("."));

			String keyID = KeyUtils.createKeyID();
			String targetName = keyID + "." + extName;

			File targetFile = new File(targetFolder, targetName);
			FileUtils.copyFile(avatar, targetFile);

			// 返回json数据
			HttpServletResponse response = this.getResponse();
			response.setContentType("text/html;charset=utf-8");
			// response.setCharacterEncoding("UTF-8");
			PrintWriter out = response.getWriter();
			// JSON在传递过程中是普通字符串形式传递的，这里简单拼接一个做测试
			String jsonString = "{\"status\":\"OK\",\"message\":\"ISOK\",\"success\":true}";
			out.println(jsonString);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public boolean saveUpfile() throws IOException {
		File upfile = this.getFile();
		this.savePath = ServletActionContext.getServletContext().getRealPath(
				"/upfile/excelmodel/");

		String fileName = KeyUtils.createKeyID();

		// bean.setAttachmentname(StringUtils.isEmpty(bean.getAttachmentname())
		// ? attFileName
		// : bean.getAttachmentname());
		// bean.setAttachmenturl(fileName);
		System.out.println(upfile.exists());
		if (upfile != null && upfile.exists()) {
			File f = new File(this.savePath);
			if (!f.exists()) {
				f.mkdir();
			}

			FileOutputStream fos = new FileOutputStream(this.savePath + "/"
					+ fileName);
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

	public File getAvatar() {
		return avatar;
	}

	public void setAvatar(File avatar) {
		this.avatar = avatar;
	}

	public String getAvatarFileName() {
		return avatarFileName;
	}

	public void setAvatarFileName(String avatarFileName) {
		this.avatarFileName = avatarFileName;
	}

}
