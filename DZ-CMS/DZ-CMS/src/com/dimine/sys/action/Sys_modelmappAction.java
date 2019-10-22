package com.dimine.sys.action;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.log4j.Logger;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
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
import com.dimine.sys.util.PublicUtil;
import com.dimine.base.common.ValidException;
import com.dimine.sys.entity.ColumnEntity;
import com.dimine.sys.entity.Sys_modelinfoEntity;
import com.dimine.sys.entity.Sys_modelmappEntity;
import com.dimine.sys.service.Sys_modelinfoService;
import com.dimine.sys.service.Sys_modelmappService;

/**
 * 用于对EXCEL导入属性对应关系表进行系列的操作的action
 * 
 * @author dimine 2014-12-18 15:53:05
 * 
 */
@Namespace("/manager/sys/modelmapp")
@Scope("request")
@Controller("sys_modelmappAction")
public class Sys_modelmappAction extends BaseAction {
	private static final long serialVersionUID = 1L;
	private final static Logger logger = Logger.getLogger(Sys_modelmappAction.class);
	// 业务处理
	@Resource
	private Sys_modelmappService<Sys_modelmappEntity> sys_modelmappService;
	private Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService;

	// 参数实体
	private Sys_modelmappEntity bean = new Sys_modelmappEntity();
	/**
	 * 添加类型 addmore为新增保存
	 */
	private String addtype;
	
	private String somename;
	
	private String encode;
	
	private String param;
	
	private String filters;
	
	private String modelid;//模版ID
	 

	/**
	 * 对EXCEL导入属性对应关系表进行列表查询操作
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
			if(modelid!=null){
				String query = " and modelid='"+modelid+"'";
				if(bean.getQuery() != null&&!"".equals(bean.getQuery())){
					bean.setQuery(bean.getQuery() + query);					
				}else{
					bean.setQuery(query);
				}
			}
			bean.setPager(this.getPager());
			List<Sys_modelmappEntity> dataList = sys_modelmappService.selectByList(bean);
			// 设置页面数据
			Map<String, Object> jsonMap = new HashMap<String, Object>();
			jsonMap.put("page", bean.getPager().getPageId());
			jsonMap.put("total", bean.getPager().getPageCount());
			jsonMap.put("records", bean.getPager().getRowCount());
			jsonMap.put("rows", dataList);
			this.setJsonStr(JSONUtil.toJSONString(jsonMap));
			System.out.println("===============>"+jsonMap);
		} catch (Exception e) {
			this.setErrorMessage(e);
		}
		return SUCCESS;
	}
	
	/**
	 * 新增EXCEL导入模板信息表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAdd", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doAdd() {
		System.out.println("---------this.getModelid()----------->"+this.getModelid());
		//根据模版ID获取服务器保存的模版文件名称
		Sys_modelinfoEntity modelinfo = new Sys_modelinfoEntity();
		try {
			modelinfo = sys_modelinfoService.selectById(this.getModelid());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String excelcolname = this.getExcelcolname(modelinfo.getTargetname());
		String fieldsList = this.getFieldsList(modelinfo.getMtable());
		String dicttypeList = this.getDicttypeList();
		String dtypeList = this.getDTypeList();
		
		StringBuffer data = new StringBuffer("({");
		data.append("success:true,errormessage:\"\",excelcolname:\"");
		data.append(excelcolname);
		data.append("\",fieldsList:\"");
		data.append(fieldsList);
		data.append("\",dicttypeList:\"");
		data.append(dicttypeList);
		data.append("\",dtypeList:\"");
		data.append(dtypeList);
		data.append("\"");
		data.append("})");
		
		this.setJsonStr(data.toString());
		return SUCCESS;
	}

	/**
	 * 新增EXCEL导入属性对应关系表信息保存 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "doAddSave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doAddSave() {
		try {
			sys_modelmappService.insert(bean);
			this.setJsonStr(bean.getMid());
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
	 * 删除EXCEL导入属性对应关系表信息 <功能详细描述>
	 * 
	 * @return
	 * @see [类、类#方法、类#成员]
	 */
	@Action(value = "delete", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doDelete() {
		try {
			System.out.println("========bean.getMid()======>"+bean.getMid());
			sys_modelmappService.delete(bean);
			this.setJsonStr(bean.getMid());
		} catch (Exception e) {
			this.setErrorMessage("failed");
			e.printStackTrace();
		}
		return SUCCESS;
	}

	/**
	 * 对EXCEL导入属性对应关系表进行修改查询操作
	 */
	@Action(value = "doModify", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String doModify() {
		try {			
			bean = sys_modelmappService.selectById(bean);
			this.setJsonStr("["+JSONUtil.toJSONString(bean)+"]");
			this.setActiontype(ACTIONTYPE_MODIFYSAVE);
		} catch (Exception e) {
			this.setErrorMessage(this.getText("modifyfailure"));
		}
		return SUCCESS;
	}

	/**
	 * 对EXCEL导入属性对应关系表进行存储和修改操作
	 */
	@Action(value = "doModifySave", results = { @Result(name = "success", location = "/pub/result.jsp") })
	public String doModifySave() {
		try {
   			if (ACTIONTYPE_MODIFYSAVE.equals(getActiontype())) {// 修改保存
				sys_modelmappService.update(bean, getActiontype());				
			this.setJsonStr(bean.getMid());
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
	 *  获取Excel文件表头列名
	 * @param filename
	 * @return
	 */
	private String getExcelcolname(String filename) {
		StringBuffer colNameString = new StringBuffer();
		boolean verifyflag = false; // 数据校验成功标识
		FileInputStream finput = null;
		
		Workbook book = null;
		Sheet sheet = null; // 获取第一个工作区
		Row row = null;
		try {//兼容office2007及以上版本处理
			book = null;
			finput = new FileInputStream(this.getRequest().getRealPath(
					"/upfile/excelmodel")
					+ "/" + filename);
			book = new XSSFWorkbook(finput);
		} catch (Exception e) {//兼容office2003版本处理
			try {
				book = null;
				finput = new FileInputStream(this.getRequest().getRealPath(
						"/upfile/excelmodel")
						+ "/" + filename);
				book = new HSSFWorkbook(finput); // 获取Excel区域
			} catch (Exception e1) {
				e1.printStackTrace();
			}
		}
		
		try {
			finput.close();
		} catch (IOException e) {
			e.printStackTrace();
		}

		int cellnum = 0;
		sheet = book.getSheetAt(0);
		row = sheet.getRow(0); // 读取第一行标题
		cellnum = row.getLastCellNum();
		DateFormat format1 = new SimpleDateFormat("yyyy-MM-dd");
		colNameString.append("<select id=\'ecolname\' class=\'chosen-select form-control\' data-placeholder=\'Choose a State...\'>");
		for (int c = 0; c < cellnum; c++) {
			ColumnEntity ce = new ColumnEntity();
			ce.setCodekey(String.valueOf(c));
			if (row.getCell((short) c).getCellType() == 0) {// 如果列的值类型为日期型则强制类型转换为字符串类型
				ce.setCodevalue(format1.format(row.getCell((short) c)
						.getDateCellValue()));
			} else {
				ce.setCodevalue(row.getCell((short) c).getStringCellValue());
			}
			colNameString.append("<option value=\'");
			colNameString.append(ce.getCodekey());
			colNameString.append("\'>");
			colNameString.append(ce.getCodevalue());
			colNameString.append("</option>");
		}
		colNameString.append("</select>");
		System.out.println("--EXCEL列名组装完毕=>"+colNameString.toString());
		return colNameString.toString();
	}
	
	// ****获取某表中的所有字段**********
	public String getFieldsList(String mtable) {
		StringBuffer fieldString = new StringBuffer();
		fieldString.append("<select id=\'mfield\'  class=\'chosen-select form-control\' data-placeholder=\'Choose a State...\'>");
		List<ColumnEntity> collist;
		collist = (List<ColumnEntity>) this.getSys_modelmappService().getFieldObjects(mtable);
		System.out.println("-----数据库表字段集合长度--->"+collist.size());
		for(ColumnEntity field : collist){
			fieldString.append("<option value=\'");
			fieldString.append(field.getCodekey());
			fieldString.append("\'>");
			fieldString.append(field.getCodevalue());
			fieldString.append("</option>");
		}
		fieldString.append("</select>");
		System.out.println("--数据库表字段组装完毕=>"+fieldString.toString());
		return fieldString.toString();
	}

	// **********获得码表类型列表**********
	public String getDicttypeList() {
		StringBuffer dicttypeString = new StringBuffer();
		dicttypeString.append("<select id=\'dicttypeid\' class=\'chosen-select form-control\' data-placeholder=\'Choose a State...\'>");
		List<ColumnEntity> dicttypelist;
		dicttypelist = (List<ColumnEntity>) this.getSys_modelmappService().getDicttypeObjects();
		System.out.println("-----字典资源类别集合长度--->"+dicttypelist.size());
		for(ColumnEntity field : dicttypelist){
			dicttypeString.append("<option value=\'");
			dicttypeString.append(field.getCodekey());
			dicttypeString.append("\'>");
			dicttypeString.append(field.getCodevalue());
			dicttypeString.append("</option>");
		}
		dicttypeString.append("</select>");
		System.out.println("--字典资源类别组装完毕=>"+dicttypeString.toString());
		return dicttypeString.toString();
	}

	// **********创建excel数据类型列表
	public String getDTypeList() {
		StringBuffer dbtypeString = new StringBuffer();
		dbtypeString.append("<select id=\'dtype\' class=\'chosen-select form-control\' data-placeholder=\'Choose a State...\'>");
		String exceldtype = "String,Int,Date,Double";
		String[] dtarr = exceldtype.split(",");
		for (int y = 0; y < dtarr.length; y++) {
			dbtypeString.append("<option value=\'");
			dbtypeString.append(dtarr[y]);
			dbtypeString.append("\'>");
			dbtypeString.append(dtarr[y]);
			dbtypeString.append("</option>");
		}
		dbtypeString.append("</select>");
		System.out.println("--excel数据类型组装完毕=>"+dbtypeString.toString());
		return dbtypeString.toString();
	}

	// 日志文件用
	@Override
	public String getBiztypename() {
		return "EXCEL导入属性对应关系表信息管理";
	}

	public String getModelid() {
		return modelid;
	}

	public void setModelid(String modelid) {
		this.modelid = modelid;
	}

	public Sys_modelmappEntity getBean() {
		return bean;
	}

	public void setBean(Sys_modelmappEntity bean) {
		this.bean = bean;
	}

	public Sys_modelmappService<Sys_modelmappEntity> getSys_modelmappService() {
		return sys_modelmappService;
	}

	public void setSys_modelmappService(Sys_modelmappService<Sys_modelmappEntity> sys_modelmappService) {
		this.sys_modelmappService = sys_modelmappService;
	}

	public Sys_modelinfoService<Sys_modelinfoEntity> getSys_modelinfoService() {
		return sys_modelinfoService;
	}

	public void setSys_modelinfoService(
			Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService) {
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
