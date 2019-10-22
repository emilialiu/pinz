package com.dimine.sys.action;

import java.io.File;
import java.io.FileInputStream;
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
import com.dimine.sys.util.DateUtil;
import com.dimine.sys.entity.ColumnEntity;
import com.dimine.sys.entity.DictEntity;
import com.dimine.sys.entity.LogUserEntity;
import com.dimine.sys.entity.Sys_modelentitymappEntity;
import com.dimine.sys.entity.Sys_modelinfoEntity;
import com.dimine.sys.entity.Sys_modelmappEntity;

@Namespace("/manager/sys/modelimport")
@Scope("request")
@Controller("sys_modelimportAction")
public class Sys_modelimportAction extends BaseAction {
	
	@Resource
	private Sys_modelentitymappService<Sys_modelentitymappEntity> sys_modelentitymappService;
	private Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService;
	private Sys_modelmappService<Sys_modelmappEntity> sys_modelmappService;
	private LogUserService<LogUserEntity> logUserService;
	
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
			if ("XLB".equals(this.getSuffix().trim().toUpperCase())
					|| "XLS".equals(this.getSuffix().trim().toUpperCase())
					|| "XLT".equals(this.getSuffix().trim().toUpperCase())
					|| "XLSX".equals(this.getSuffix().trim().toUpperCase())
					|| "XLTX".equals(this.getSuffix().trim().toUpperCase())) {

			} else {
				this.setErrorFlag("true");
				this.setErrorMessage("指定导入的文件为非Excel表文件请重新选择！");
				return SUCCESS;
			}
			String targetFolder = getRequest().getRealPath("/upfile/excelimport");
			String targetName = DateUtil.getYear() + DateUtil.getMonth()
					+ DateUtil.getDay() + DateUtil.getHour()
					+ DateUtil.getMinute() + DateUtil.getSecond()
					+ "." +this.getSuffix();
			File targetFile = new File(targetFolder, targetName);
			FileUtils.copyFile(file, targetFile);
			//开始EXCEL导入解析处理
			String msginfo = importExcel(targetName);
			
			if ("".equals(msginfo)) {
				this.setErrorFlag("false");
			} else {
				System.out.println(msginfo);
				this.setErrorFlag("true");
				this.setErrorMessage(msginfo);
			}
		} catch (IOException e) {
			e.printStackTrace();
			this.setErrorMessage("导入失败！");
		}
		/*
		 * if(this.getActiontype()!=null&&"change".equals(this.getActiontype())){
		 * topath="recordimport"; }else{ topath="success"; }
		 */
		return SUCCESS;
	}

	// 处理Excel数据
	private String importExcel(String filename) {
		StringBuffer errmsg = new StringBuffer();
		boolean verifyflag = false; // 数据校验成功标识
		FileInputStream finput = null;
		
		Workbook book = null;
		Sheet sheet = null; // 获取第一个工作区
		Row row = null;
		Cell cell = null;
		try {//兼容office2007及以上版本处理
			book = null;
			finput = new FileInputStream(this.getRequest().getRealPath(
					"/upfile/excelimport")
					+ "/" + filename);
			book = new XSSFWorkbook(finput);
		} catch (Exception e) {//兼容office2003版本处理
			try {
				book = null;
				finput = new FileInputStream(this.getRequest().getRealPath(
						"/upfile/excelimport")
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

		int rownum = 0;
		int cellnum = 0;
		sheet = book.getSheetAt(0);//获取EXCEL第一个页签
		row = sheet.getRow(0);
		rownum = sheet.getLastRowNum();//行数
		cellnum = row.getLastCellNum();//列数

		// 获取模板信息
		Sys_modelinfoEntity modelinfo = new Sys_modelinfoEntity();
		modelinfo.setModelid(this.getModelid());
		try {
			modelinfo = (Sys_modelinfoEntity) this.sys_modelinfoService.selectById(modelinfo);
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// 获取表与实体类的映射信息
		Sys_modelentitymappEntity mappinfo = new Sys_modelentitymappEntity();
		mappinfo.setTablename(modelinfo.getMtable());
		try {
			mappinfo = (Sys_modelentitymappEntity) this.sys_modelentitymappService.selectById(modelinfo.getMtable());
		} catch (Exception e2) {
			e2.printStackTrace();
		}
		// 获取映射关系
		Sys_modelmappEntity mbean = new Sys_modelmappEntity();
		mbean.setModelid(modelid);
		List<Sys_modelmappEntity> maplist = (List<Sys_modelmappEntity>) this.getSys_modelmappService().selectByAll(mbean);
		if (maplist.size() < 1) {
			errmsg.append("Excel文件与数据库对应关系不存在，请到”Exel导入模板定制“中进行相关维护");
			// 没有找到对应的对应关系直接返回
			return errmsg.toString();
		}
		
		// 读取第一行的标题，检查模板是否正确		
		// 根据excel表头判断对应模版是否正确
		for (int i = 0; i < maplist.size(); i++) {
			mbean = (Sys_modelmappEntity) maplist.get(i);
			cell = row.getCell((short) Integer.parseInt(mbean.getEcolindex()));
			if (!(mbean.getEcolname().trim()).equals(cell
					.getRichStringCellValue().getString().trim())) {
				errmsg.append("导入文件不符合模板文件,请检查Excel模板“" + mbean.getEcolname()
						+ "”对应列！");
				return errmsg.toString(); // 模板格式不正确，直接返回
			}
		}

		// 对excel文件进行判空及数据合法性判断
		for (int j = 1; j <= rownum; j++) {
			row = sheet.getRow(j); // 读取第一行的标题，检查模板是否正确
			for (int i = 0; i < maplist.size(); i++) {// 处理excel表格中的一行数据
				mbean = (Sys_modelmappEntity) maplist.get(i);

				if (row == null) { // 如果这一行为空，直接跳过
					continue;
				}
				cell = row.getCell((short) Integer.parseInt(mbean
						.getEcolindex())); // 读取对应列
				if ("0".equals(mbean.getIsnull().trim())) {// 不允许为空
					int rownumber = j + 1;
					int colnumber = Integer.parseInt(mbean.getEcolindex()) + 1;
					if ("VARCHAR2".equals(mbean.getDtype().trim())
							|| "CHAR".equals(mbean.getDtype().trim())
							|| "NVARCHAR2".equals(mbean.getDtype().trim())) {
						if (cell == null) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else {
							String dateformaterror = "false";
							try {
								cell.getStringCellValue();

							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为字符类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非字符型数据；");
								dateformaterror = "true";
							}
							if ("false".equals(dateformaterror)) {
								if ("".equals(cell.getRichStringCellValue()
										.getString().trim())) {
									errmsg.append("第" + rownumber + "行第"
											+ colnumber + "列["
											+ mbean.getEcolname() + "]不能为空；");
								}
							}

						}
					} else if ("NUMBER".equals(mbean.getDtype().trim())) {
						if (cell == null) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else {

							try {
								cell.getNumericCellValue();
							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为数值类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非数值型数据；");
							}
						}

					} else if ("DATE".equals(mbean.getDtype().trim())) {
						if (cell == null) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
							errmsg.append("第" + rownumber + "行第" + colnumber
									+ "列[" + mbean.getEcolname() + "]不能为空；");
						} else {
							try {
								cell.getDateCellValue();
							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为日期类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非日期型数据；");
							}
						}

					}

					if ("1".equals(mbean.getIsscode().trim())) {// 转码校验
						if ("VARCHAR2".equals(mbean.getDtype().trim())
								|| "CHAR".equals(mbean.getDtype().trim())
								|| "NVARCHAR2".equals(mbean.getDtype().trim())) {
							if (cell == null) {

							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

							} else if ("".equals(cell.getRichStringCellValue()
									.getString().trim())) {

							} else {
								DictEntity dicbean = new DictEntity();
								dicbean
										.setTypeid(mbean.getDicttypeid()
												.trim());
								dicbean.setParamname(cell.getStringCellValue()
										.trim());
								String changedcode = (String) this.sys_modelmappService.selectDictID(dicbean);
								if (changedcode == null
										|| "".equals(changedcode)) {
									errmsg.append("第" + rownumber + "行第"
											+ colnumber + "列["
											+ mbean.getEcolname()
											+ "]码表中无对应码值，请核实！");
								}
							}
						}

					}// 转码校验 end

				} else {// 允许为空时
					int rownumber = j + 1;
					int colnumber = Integer.parseInt(mbean.getEcolindex()) + 1;
					if ("VARCHAR2".equals(mbean.getDtype().trim())
							|| "CHAR".equals(mbean.getDtype().trim())
							|| "NVARCHAR2".equals(mbean.getDtype().trim())) {
						if (cell == null) {

						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

						} else {

							try {
								cell.getStringCellValue();

							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为字符类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非字符型数据；");

							}

						}
					} else if ("NUMBER".equals(mbean.getDtype().trim())) {
						if (cell == null) {

						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

						} else {
							try {
								cell.getNumericCellValue();
							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为数值类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非数值型数据；");
							}
						}

					} else if ("DATE".equals(mbean.getDtype().trim())) {
						if (cell == null) {

						} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

						} else {
							try {
								cell.getDateCellValue();
							} catch (Exception e1) {
								errmsg.append("[" + mbean.getEcolname()
										+ "]在模板中定制为日期类型，" + "第" + rownumber
										+ "行第" + colnumber + "列["
										+ mbean.getEcolname() + "]为非日期型数据；");
							}
						}

					} // 允许为空时数据类型判断 end
					if ("1".equals(mbean.getIsscode().trim())) {// 转码校验
						if ("VARCHAR2".equals(mbean.getDtype().trim())
								|| "CHAR".equals(mbean.getDtype().trim())
								|| "NVARCHAR2".equals(mbean.getDtype().trim())) {
							if (cell == null) {

							} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {

							} else if ("".equals(cell.getRichStringCellValue()
									.getString().trim())) {

							} else {
								DictEntity dicbean = new DictEntity();
								dicbean
										.setTypeid(mbean.getDicttypeid()
												.trim());
								dicbean.setParamname(cell.getStringCellValue()
										.trim());
								String changedcode = (String) this.sys_modelmappService.selectDictID(dicbean);
								if (changedcode == null
										|| "".equals(changedcode)) {
									errmsg.append("第" + rownumber + "行第"
											+ colnumber + "列["
											+ mbean.getEcolname()
											+ "]码表中无对应码值，请核实！");
								}
							}
						}
						/*
						 * else{ errmsg.append("第" + rownumber + "行第" +
						 * colnumber + "列" + mbean.getEcolname() +
						 * "，为非字符类型，请核实！"); }
						 */
					}// 转码校验 end
				}
			}// 处理excel表格中的一行数据 end

		}// 对excel文件进行判空及数据合法性判断end

		System.out.println(errmsg.toString());
		// ********************************cy*******
		if (errmsg != null && !"".equals(errmsg.toString())) {
			return errmsg.toString();
		}
		/*
		 * if (errmsg != null && !"".equals(errmsg.toString())) {
		 * this.setErrorMessage(errmsg.toString());
		 * if(this.getActiontype()!=null&&"change".equals(this.getActiontype())){
		 * topath="recordimport"; System.out.println("herek
		 * 8888888888888888888"+this.getErrorMessage());
		 * 
		 * }else{ topath=errmsg.toString(); } return topath; }
		 */
		// 针对于考勤导入时出错importerror
		/*
		 * if
		 * (this.getActiontype()!=null&&"change".equals(this.getActiontype())&&errmsg !=
		 * null && !"".equals(errmsg.toString())) {
		 * attendanceerrmsg=errmsg.toString(); attendanceerrflag="true"; return
		 * "importerror"; }
		 */
		// 将excel表格中的数据插入数据库中
		try {
			classType = Class.forName(mappinfo.getEntityclass());
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			obj = classType.newInstance();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		for (int j = 1; j <= rownum; j++) {// Excel导入文件行做为外循环
			row = sheet.getRow(j);

			for (int i = 0; i < maplist.size(); i++) {// 对应关系列数做为内循环
				mbean = (Sys_modelmappEntity) maplist.get(i);

				if (row == null) { // 如果这一行为空，直接跳过
					continue;
				}

				cell = row.getCell((short) Integer.parseInt(mbean
						.getEcolindex())); // 读取第i列

				try {

					try {

						Field fields[] = classType.getDeclaredFields();

						for (int k = 0; k < fields.length; k++) {
							Field field = fields[k];
							String fieldName = field.getName();
							if (fieldName.toUpperCase().equals(
									mbean.getMfield().trim().toUpperCase())) {
								String firstLetter = fieldName.substring(0, 1)
										.toUpperCase();
								String setMethodName = "set" + firstLetter
										+ fieldName.substring(1);
								Method setMethod = classType.getMethod(
										setMethodName, field.getType());

								if ("0".equals(mbean.getIsscode().trim())) {// 无需转码
									if ("VARCHAR2".equals(mbean.getDtype()
											.trim())
											|| "CHAR".equals(mbean.getDtype()
													.trim())
											|| "NVARCHAR2".equals(mbean
													.getDtype().trim())) {
										if (cell == null) {
											setMethod.invoke(obj, "");
										} else {
											setMethod.invoke(obj, cell
													.getStringCellValue()
													.trim());
										}
									} else if ("NUMBER".equals(mbean.getDtype()
											.trim())) {
										if ("int".equals(field.getType()
												.toString().trim())) {
											if (cell == null) {
												setMethod.invoke(obj, 0);
											} else {
												setMethod
														.invoke(
																obj,
																(int) cell
																		.getNumericCellValue());
											}
										} else {
											if ("double".equals(field.getType()
													.toString().trim())) {

												if (cell == null) {
													setMethod.invoke(obj, 0.0);
												} else {
													setMethod
															.invoke(
																	obj,
																	(double) cell
																			.getNumericCellValue());
												}
											} else if ("float".equals(field
													.getType().toString()
													.trim())) {
												if (cell == null) {
													setMethod.invoke(obj, 0.0);
												} else {
													setMethod
															.invoke(
																	obj,
																	(float) cell
																			.getNumericCellValue());
												}
											}
										}
									} else if ("DATE".equals(mbean.getDtype()
											.trim())) {
										if (cell == null) {
											setMethod.invoke(obj, "");
										} else {
											SimpleDateFormat sdf = new SimpleDateFormat(
													"yyyy-MM-dd HH:mm:ss");
											String dateStr = sdf.format(cell
													.getDateCellValue());
											setMethod.invoke(obj, dateStr);
										}
									}
								} else if ("1"
										.equals(mbean.getIsscode().trim())) {// 转码操作
									if (cell == null) {
										setMethod.invoke(obj, "");
									} else if (cell.getCellType() == HSSFCell.CELL_TYPE_BLANK) {
										setMethod.invoke(obj, "");
									} else {
										DictEntity dicbean = new DictEntity();
										dicbean.setTypeid(mbean
												.getDicttypeid().trim());
										dicbean.setParamname(cell
												.getStringCellValue().trim());
										String changedcode = (String) this.sys_modelmappService.selectDictID(dicbean);

										setMethod = classType
												.getMethod(
														setMethodName,
														new Class[] { java.lang.String.class });
										if (changedcode == null
												|| "".equals(changedcode)) {
											setMethod.invoke(obj, "");
										} else {
											setMethod.invoke(obj, changedcode
													.trim());

										}
									}

								}// end
							}

						}

					} catch (IllegalArgumentException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (IllegalAccessException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (InvocationTargetException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}

				} catch (SecurityException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}// row completed

			/*开始是否有指定列值自动生成处理
			 * 1、判断是否有
			 * 2-1、如果无则布考虑次步骤，直接插入数据到数据库
			 * 2-2、如果有则按指定规则","来截分有几个列需要自动生成
			 * 3、开始生成列的值插入数据库
			 */
			//开始判断是否有指定列需要自动生成值
			if("1".equals(mappinfo.getIsauto())){//指定了，需要自动自动生成列的值
				//开始截分有几个列需要自动生成
				int index = mappinfo.getDbkey().indexOf(",");
				if(index>-1){//表示指定了多个列
					String cols[] = mappinfo.getDbkey().split(",");
					for(int i=0;i<cols.length;i++){
						System.out.println("====>该表对应EXCEL导入定义了"+cols.length+"个列自动生成键值.");
						// 指定列自动生成键值
						String setMethodName = "set"
								+ cols[i].trim().substring(0, 1).toUpperCase()
								+ cols[i].trim().substring(1).toLowerCase();
						Method setMethod;
						try {
							setMethod = classType.getMethod(setMethodName,
									new Class[] { java.lang.String.class });
							try {
								setMethod.invoke(obj, KeyUtils.createKeyID());
							} catch (IllegalArgumentException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (IllegalAccessException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							} catch (InvocationTargetException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
						} catch (SecurityException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (NoSuchMethodException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}else{//否则只有一个列
					System.out.println("====>该表对应EXCEL导入定义了1个列自动生成键值.");
					String dbkey = mappinfo.getDbkey();
					// 指定列自动生成键值
					String setMethodName = "set"
							+ dbkey.trim().substring(0, 1).toUpperCase()
							+ dbkey.trim().substring(1).toLowerCase();
					Method setMethod;
					try {
						setMethod = classType.getMethod(setMethodName,
								new Class[] { java.lang.String.class });
						try {
							setMethod.invoke(obj, KeyUtils.createKeyID());
						} catch (IllegalArgumentException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (IllegalAccessException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} catch (InvocationTargetException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					} catch (SecurityException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					} catch (NoSuchMethodException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			System.out.println("====>该表对应EXCEL导入没有定义列自动生成键值.");
			try {
				this.getLogUserService().insert((LogUserEntity)obj); // 将读取好的内容存储到数据库(使用时根据导入的实体自行改变使用对应的Service)
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}// 将excel表格中的数据插入数据库中

		return errmsg.toString();

	}

	/**
	 * 跳转页面
	 * 
	 * @return
	 */
	@Action(value = "main", results = { @Result(name = "success", location = "/pub/jsondata.jsp") })
	public String main() {
		List<Sys_modelinfoEntity> modellist = new ArrayList<Sys_modelinfoEntity>();
		try {
			Sys_modelinfoEntity me = new Sys_modelinfoEntity();
			modellist = (List<Sys_modelinfoEntity>) this.getSys_modelinfoService().selectModelinfoList(me);

			StringBuffer modelinfoList = new StringBuffer();
			modelinfoList.append("<select id=\'modelid\'  class=\'chosen-select form-control\' data-placeholder=\'Choose a State...\'>");
			System.out.println("-----获取导入模版定义集合长度--->"+modellist.size());
			for(Sys_modelinfoEntity modelinfo : modellist){
				modelinfoList.append("<option value=\'");
				modelinfoList.append(modelinfo.getModelid());
				modelinfoList.append("\'>");
				modelinfoList.append(modelinfo.getModelname());
				modelinfoList.append("</option>");
			}
			modelinfoList.append("</select>");
			StringBuffer data = new StringBuffer("({");
			data.append("success:true,errormessage:\"\",modelinfoList:\"");
			data.append(modelinfoList);
			data.append("\"");
			data.append("})");
			this.setJsonStr(data.toString());
		} catch (Exception ee) {
			ee.printStackTrace();
		}
		return SUCCESS;
	}


	public LogUserService<LogUserEntity> getLogUserService() {
		return logUserService;
	}

	public void setLogUserService(LogUserService<LogUserEntity> logUserService) {
		this.logUserService = logUserService;
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

	public Sys_modelentitymappService<Sys_modelentitymappEntity> getSys_modelentitymappService() {
		return sys_modelentitymappService;
	}

	public void setSys_modelentitymappService(
			Sys_modelentitymappService<Sys_modelentitymappEntity> sys_modelentitymappService) {
		this.sys_modelentitymappService = sys_modelentitymappService;
	}

	public Sys_modelmappService<Sys_modelmappEntity> getSys_modelmappService() {
		return sys_modelmappService;
	}

	public void setSys_modelmappService(
			Sys_modelmappService<Sys_modelmappEntity> sys_modelmappService) {
		this.sys_modelmappService = sys_modelmappService;
	}

	public String getSuffix() {
		return suffix;
	}

	public void setSuffix(String suffix) {
		this.suffix = suffix;
	}

	public Sys_modelinfoService<Sys_modelinfoEntity> getSys_modelinfoService() {
		return sys_modelinfoService;
	}

	public void setSys_modelinfoService(
			Sys_modelinfoService<Sys_modelinfoEntity> sys_modelinfoService) {
		this.sys_modelinfoService = sys_modelinfoService;
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

}
