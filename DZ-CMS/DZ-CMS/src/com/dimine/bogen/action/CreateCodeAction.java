package com.dimine.bogen.action;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;

import com.dimine.bogen.model.CodeEntity;
import com.dimine.bogen.model.FileName;
import com.dimine.bogen.model.FilePath;
import com.dimine.bogen.util.FormControl;
import com.dimine.bogen.util.IFormControl;
import com.dimine.bogen.util.StaticFreemarker;
import com.dimine.base.action.BaseAction;
import com.dimine.base.service.BaseService;
import com.dimine.base.util.KeyUtils;
import com.dimine.bogen.util.FileUtil;
import freemarker.template.TemplateException;

/**
 * 
 * <p> CreateCodeAction </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
@Namespace("/webpage/manager/bogen/freemarker")
@Scope("request")
@Controller("createCodeAction")
public class CreateCodeAction extends BaseAction {
	
	protected final Log log = LogFactory.getLog(getClass());
	private BaseService baseService = null;//业务处理接口
	String tempArr[][] = null;//页面接收参数
	private List<CodeEntity> columnsList = null;//列详细信息列表
	private String thisweek;//页面选择列参数信息
	private String tablename;//表名
	private String tablecomments;//表中文注释
	private String classFileName;//文件中的类名
	private String titleName;//title名称
	private String srcRoot;//源码配置文件模块名
	private String webRoot;//功能页面路径
	private String isShowPK;//页面上主键是否显示
	private String previewPath;//预览文件路径
	private String sessionID;
	private IFormControl formcontrol;
	private String templatepath="template_mysql_dimine";//缺省是非国际化模板路径

	/**
	 * 生成源代码方法
	 */
	@Action(value = "createCode", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String createCode(){		
		/** 页面参数传递 */
		System.out.println("===页面参数表字段详细信息===>"+thisweek);
		tempArr = Decoder.decode(thisweek);
		columnsList = new ArrayList<CodeEntity>();
		if (tempArr != null) {
			int pkCount = 0;
			for (int i = 0; i < tempArr.length; i++) {
				CodeEntity entity = new CodeEntity();
								
				if (tempArr[i][2] == null || tempArr[i][2].trim().length() == 0||tempArr[i][2].trim().equals("choose")) {
					this.setErrorMessage("请输入列描述！");
					return SUCCESS;
				}
				if(tempArr[i][5].trim().equals("true")){
					pkCount+=1;
				}
				//详细信息
				entity.setFiledName(tempArr[i][1].toLowerCase());
				entity.setDescription(tempArr[i][2]);
				entity.setPageShow(tempArr[i][3]);
				entity.setFuncShow(tempArr[i][4]);
				entity.setPrimaryKey(tempArr[i][5].trim());
				entity.setIsRequirement(tempArr[i][6]);
				entity.setData_type(tempArr[i][7]);
				entity.setType(tempArr[i][8]);
				entity.setValidator(tempArr[i][9]);
				entity.setTable_name(this.getTablename().toLowerCase());
				entity.setClassName(this.getClassFileName().toLowerCase());
				entity.setTitle(this.getTitleName());
				entity.setModelName(this.getSrcRoot().toLowerCase());
				entity.setFilePath(this.getWebRoot().toLowerCase());
				entity.setIsShowPK(this.getIsShowPK());
				columnsList.add(entity);				
			}
			if(pkCount<=0){
				this.setErrorMessage("请选择主键列！");
				return SUCCESS;
			}
		}
		
		/** 变量定义 */
		Map map = new HashMap();
		List list = new ArrayList();//add.jsp modify.jsp view.jsp
		String queryName = "";//查询列表信息
		List li = new ArrayList();//循环字段属性
		List isRequirement = new ArrayList();//是否查询条件
		List primaryKey = new ArrayList();//主键列
		String modelPath ="" ;//模块路径
		String filePath ="";//jsp文件路径
		String title ="";//中文表名
		String tableName ="";//表名
		String actionName ="";//action
		String sysdate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date()).toString();//当前系统日期
		
		for(CodeEntity bean:columnsList){
			//主键标识
			if("true".equals(bean.getPrimaryKey().trim())){
				primaryKey.add(bean.getFiledName());
			}
			//是否为查询条件
			if("true".equals(bean.getIsRequirement())){
				isRequirement.add(bean);
			}
			//取得html表单参数
			String htmStr = new FormControl().getFormControlType(bean.getType(), bean.getFiledName(), bean.getFilePath(),bean.getDescription(), bean.getValidator(), bean.getPrimaryKey(),bean.getPageShow(),bean.getIsShowPK());
			String queryStr = new FormControl().getQueryType(bean.getIsRequirement(),bean.getDescription());
			if(!"".equals(htmStr)){
				list.add(htmStr);
			}
			if(!"".equals(queryStr)){
				queryName = queryName + queryStr;
			}
			li.add(bean);
			modelPath = bean.getModelName();//模块路径
			filePath = bean.getFilePath();//jsp文件路径
			title = bean.getTitle();//列表标题
			tableName = bean.getTable_name();//表名
			actionName = bean.getClassName();//action文件名称
		}
		if(primaryKey.size() == 0){
			primaryKey = null;
		}
		if(queryName.length()>1){
			queryName = queryName.substring(0, queryName.length()-1);
		}else{
			queryName = "";
		}
		String seq = KeyUtils.createKeyID();//预览页面路径
		FileName fileName = new FileName();//产生文件名称
		FilePath outPath = new FilePath();//文件输出路径
		String str = this.getRequest().getSession().getId();//取得sessionID
		StringBuffer sb = new StringBuffer("");
		previewPath = sb.append("tmp/preview/").append(str+"/").append(seq+"/").append(filePath).toString();//预览页面路径
		
		/** 变量赋值 */
		map.put("code", list);//jsp 表单控件信息
		map.put("filed", li);//操作表中字段信息
		map.put("queryName", queryName);//查询条件信息
		map.put("modelPath", modelPath.replaceAll("/", "."));//模块名称
		map.put("filePath", filePath);//jsp 文件路径
		map.put("title", title);//中文表名
		map.put("tableName", tableName);//表名
		map.put("actionName", actionName);//action
		map.put("isRequirement", isRequirement);//是否查询条件
		map.put("previewPath", previewPath);//预览文件路径
		map.put("primaryKey", primaryKey);//主键
		map.put("sysdate", sysdate);//当前系统日期
		
		try {
			//生成所需文件
			StaticFreemarker.init("main.ftl", "main.jsp", map, getTemplatepath(),outPath.getPagePath(str,modelPath,filePath),this.getRequest());
			StaticFreemarker.init("bizgrid.ftl", "bizgrid.js", map, getTemplatepath(),outPath.getPagePath(str,modelPath, filePath),this.getRequest());
			StaticFreemarker.init("info_jsp.ftl", "info.jsp", map, getTemplatepath(),outPath.getPagePath(str,modelPath, filePath),this.getRequest());
			StaticFreemarker.init("info_js.ftl", "info.js", map, getTemplatepath(),outPath.getPagePath(str,modelPath, filePath),this.getRequest());
			StaticFreemarker.init("action.ftl", fileName.getAction(actionName), map, getTemplatepath(),outPath.getActionPath(str, modelPath),this.getRequest());
			StaticFreemarker.init("entity.ftl", fileName.getEntity(actionName), map, getTemplatepath(),outPath.getEntityPath(str, modelPath),this.getRequest());
			StaticFreemarker.init("service.ftl", fileName.getService(actionName), map, getTemplatepath(),outPath.getServicePath(str, modelPath),this.getRequest());
			StaticFreemarker.init("dao.ftl", fileName.getDao(actionName), map, getTemplatepath(),outPath.getDaoPath(str, modelPath),this.getRequest());
			StaticFreemarker.init("ibatis.ftl", fileName.getIbatisConf(actionName), map, getTemplatepath(),outPath.getIbatisPath(str, modelPath),this.getRequest());
			//生成预览页面
			//StaticFreemarker.init("preview_main.ftl", "main.jsp", map, getTemplatepath(),outPath.getPreviewPath(str,seq, filePath),this.getRequest());
			//StaticFreemarker.init("preview_bizgrid.ftl", "bizgrid.js", map, getTemplatepath(),outPath.getPreviewPath(str,seq, filePath),this.getRequest());
			//StaticFreemarker.init("preview_info_jsp.ftl", "info.jsp", map, getTemplatepath(),outPath.getPreviewPath(str,seq, filePath),this.getRequest());
			//StaticFreemarker.init("preview_info_js.ftl", "info.js", map, getTemplatepath(),outPath.getPreviewPath(str,seq, filePath),this.getRequest());
			
		} catch (IOException e) {
			log.error(e);
			e.printStackTrace();
		} catch (TemplateException e) {
			log.error(e);
			e.printStackTrace();
		}
		StringBuffer jsonString = new StringBuffer();
		jsonString.append("{tablename:\'");
		jsonString.append(tableName);
		jsonString.append("\',");
		jsonString.append("previewpath:\'");
		jsonString.append(previewPath);
		jsonString.append("\'}");
		System.out.println(jsonString.toString());
		this.getRequest().setAttribute("jsonString", jsonString.toString());
		return SUCCESS;
	}
	
	/**
	 * 对生成的文件进行文件打包
	 * @param str
	 * @return
	 */
	@Action(value = "doFileZip", results = { @Result(name = "success", location = "/webpage/manager/bogen/main.jsp") })
	public String doFileZip(){
		String path= this.getRequest().getRealPath("/");//取得服务器路径
		StringBuffer sbPath = new StringBuffer("");
		path = sbPath.append(path).append("/tmp/").append(this.getRequest().getSession().getId()).toString();//打包文件所在位置
		StringBuffer sbName = new StringBuffer("");
		String name = sbName.append(path).append(".zip").toString();//打包目标文件
		File f = new File(path);
		if(f.exists()){//判读文件是否生成，如果文件存在则进行打包操作
			try {
				FileUtil.zip(path, name);//对文件进行打包操作
				FileUtil.deletefile(path);//删除产生文件
			} catch (FileNotFoundException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return null;
	}
	
	public boolean file(){
		return false;
	}

	public String[][] getTempArr() {
		return tempArr;
	}
	
	public void setTempArr(String[][] tempArr) {
		this.tempArr = tempArr;
	}
	
	public List<CodeEntity> getColumnsList() {
		return columnsList;
	}
	
	public void setColumnsList(List<CodeEntity> columnsList) {
		this.columnsList = columnsList;
	}
	
	public String getThisweek() {
		return thisweek;
	}
	
	public void setThisweek(String thisweek) {
		this.thisweek = thisweek;
	}
	
	public String getTablecomments() {
		return tablecomments;
	}
	
	public void setTablecomments(String tablecomments) {
		this.tablecomments = tablecomments;
	}
	
	public String getSrcRoot() {
		return srcRoot;
	}
	
	public void setSrcRoot(String srcRoot) {
		this.srcRoot = srcRoot;
	}
	
	public String getWebRoot() {
		return webRoot;
	}
	
	public void setWebRoot(String webRoot) {
		this.webRoot = webRoot;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public BaseService getBaseService() {
		return baseService;
	}

	public void setBaseService(BaseService baseService) {
		this.baseService = baseService;
	}

	public String getPreviewPath() {
		return previewPath;
	}

	public void setPreviewPath(String previewPath) {
		this.previewPath = previewPath;
	}

	public String getClassFileName() {
		return classFileName;
	}

	public void setClassFileName(String classFileName) {
		this.classFileName = classFileName;
	}

	public String getTitleName() {
		return titleName;
	}

	public void setTitleName(String titleName) {
		this.titleName = titleName;
	}
	public String getSessionID() {
		return sessionID;
	}
	
	public void setSessionID(String sessionID) {
		this.sessionID = sessionID;
	}
	public String getIsShowPK() {
		return isShowPK;
	}

	public void setIsShowPK(String isShowPK) {
		this.isShowPK = isShowPK;
	}

	@Override
	public String getBiztypename() {
		// TODO Auto-generated method stub
		return "根据模板创建文件";
	}

	public String getTemplatepath() {
		return templatepath;
	}

	public void setTemplatepath(String templatepath) {
		this.templatepath = templatepath;
	}

	public IFormControl getFormcontrol() {
		return formcontrol;
	}

	public void setFormcontrol(IFormControl formcontrol) {
		this.formcontrol = formcontrol;
	}
}
