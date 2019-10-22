package com.dimine.bogen.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;

import org.apache.struts2.ServletActionContext;

import com.dimine.base.action.BaseAction;
/**
 * 
 * <p> DownloadFile </p>
 * 
 * @author  xieming
 * @version V1.0, Sep 25, 2010
 */
public class DownloadFile extends BaseAction {
	private static final long serialVersionUID = 1L;
	private String sessionID;//sessionID
	private InputStream targetFile;
	private String inputPath;

	public InputStream getTargetFile() throws UnsupportedEncodingException {
		try {
			String filePath = ServletActionContext.getServletContext()
					.getRealPath("/tmp/");
			return this.getFile(filePath,"\\"+ getSessionID()+".zip");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
	}

	private String getFileName(String filePath) {
		File f = new File(filePath);
		File[] files = f.listFiles();
		String fileName = "";
		for (int i = 0; i <= files.length; i++) {
			if (!files[i].getName().startsWith(".")){
				fileName = files[i].getName();
				break;
			}
		}
		return fileName;
	}

	/**
	 * 取得指定位置的文件
	 * 
	 * @return
	 * @throws FileNotFoundException
	 */
	private InputStream getFile(String filePath, String fileName)
			throws FileNotFoundException {
		return (new FileInputStream(filePath + fileName));
	}

	/**
	 * 提供转换编码后的供下载用的文件名
	 */
	public String getDownloadFileName() {
		String downFileName = getFileName(ServletActionContext.getServletContext()
				.getRealPath("/tmp/"));
		try {
			downFileName = new String(downFileName.getBytes(), "ISO8859-1");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return downFileName;
	}

	public String execute() {
		try {
			return SUCCESS;
		} catch (Exception e) {
			e.printStackTrace();
			return "input";
		}
	}
	
	public void setTargetFile(InputStream targetFile) {
		this.targetFile = targetFile;
	}

	public String getInputPath() {
		return inputPath;
	}

	public void setInputPath(String inputPath) {
		this.inputPath = inputPath;
	}

	public String getSessionID() {
		return sessionID;
	}

	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}

	@Override
	public String getBiztypename() {
		return "文件下载";
	}
}