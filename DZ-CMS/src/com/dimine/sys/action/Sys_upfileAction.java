package com.dimine.sys.action;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.io.FileUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.apache.struts2.interceptor.SessionAware;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.base.action.BaseAction;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.sys.service.LogUserService;
import com.dimine.sys.service.Sys_modelentitymappService;
import com.dimine.sys.service.Sys_modelinfoService;
import com.dimine.sys.service.Sys_modelmappService;
import com.dimine.sys.service.Sys_paramService;
import com.dimine.sys.service.Sys_upfileService;
import com.dimine.sys.util.DateUtil;
import com.dimine.sys.entity.ColumnEntity;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.LogUserEntity;
import com.dimine.sys.entity.Sys_modelentitymappEntity;
import com.dimine.sys.entity.Sys_modelinfoEntity;
import com.dimine.sys.entity.Sys_modelmappEntity;
import com.dimine.sys.entity.Sys_paramEntity;
import com.dimine.sys.entity.Sys_upfileEntity;

@Namespace("/manager/sys/upfile")
@Scope("request")
@Controller("sys_upfileAction")
public class Sys_upfileAction extends BaseAction {
	
	@Resource
	private Sys_upfileService<Sys_upfileEntity> sys_upfileService;
	
	private Class<?> classType;
	Object obj = null;
	private String modelid;
	private String suffix;
	private File file = null;
	
//	Map session;
//	// 接收从页面传过来本地文件名称
//	private String localfilenname;
//	// 用于保存action返回不同路径
//	String topath = "success";
//	String attendanceerrmsg = "";
//	String attendanceerrflag = "false";
//	private static final long serialVersionUID = 1L;
//	private String filen = "";

	/** 导入Excel方法
	 * 
	 * @return
	 */
	@Action(value = "doImport", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doImport() {
		try {
			if ("TXT".equals(this.getSuffix().trim().toUpperCase())) {
				
			} else {
				this.setErrorFlag("true");
				this.setErrorMessage("指定导入的文件为非Excel表文件请重新选择！");
				return SUCCESS;
			}
			BufferedReader br = new BufferedReader(new FileReader(file));//构造一个BufferedReader类来读取文件
			String targetFolder = getRequest().getRealPath("/upfile/excelimport");
			String targetName = DateUtil.getYear() + DateUtil.getMonth()
					+ DateUtil.getDay() + DateUtil.getHour()
					+ DateUtil.getMinute() + DateUtil.getSecond()
					+ "." +this.getSuffix();
			File targetFile = new File(targetFolder, targetName);
			FileUtils.copyFile(file, targetFile);
			
			Sys_upfileEntity upfile = new Sys_upfileEntity();
			upfile.setUpfiletype(this.getSuffix());
			upfile.setUpfilename(DateUtil.getYear() + DateUtil.getMonth()
					+ DateUtil.getDay() + DateUtil.getHour()
					+ DateUtil.getMinute() + DateUtil.getSecond());
			upfile.setMemo(br.readLine());
			
			sys_upfileService.insert(upfile);
			
		} catch (IOException e) {
			e.printStackTrace();
			this.setErrorMessage("导入失败！");
		}
		return SUCCESS;
	}

	public Class<?> getClassType() {
		return classType;
	}

	public void setClassType(Class<?> classType) {
		this.classType = classType;
	}

	public Object getObj() {
		return obj;
	}

	public void setObj(Object obj) {
		this.obj = obj;
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

	@Override
	public String getBiztypename() {

		return "excelimport";
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public Sys_upfileService<Sys_upfileEntity> getSys_upfileService() {
		return sys_upfileService;
	}

	public void setSys_upfileService(
			Sys_upfileService<Sys_upfileEntity> sys_upfileService) {
		this.sys_upfileService = sys_upfileService;
	}

}
