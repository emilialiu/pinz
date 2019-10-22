package com.dimine.bogen.util;
/**
 * 
 * <p> FormControl </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
public class FormControl implements IFormControl{
	/**
	 * 
	 * @param type 表单类型
	 * @param fieldName 字段名
	 * @param description 字段描述
	 * @param validator 验证规则
	 * @param primaryKey 是否主键
	 * @param pageShow 是否在页面上显示
	 * @param isShowPK 主键是否在页面上显示
	 * @return HTML
	 */
	public String getFormControlType(String type,String fieldName,String filePath,String description,String validator,String primaryKey,String pageShow,String isShowPK){
		StringBuffer str = new StringBuffer("");
		if("true".equals(pageShow)){
			if("text".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<input type=\"text\" name=\"");		
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				str.append("value=\"${bean.");
				str.append(fieldName.toLowerCase());
				str.append("}\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"col-xs-12\" />");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("select".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<select name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"form-control\" >");
				str.append("<option value =\"\">");
				str.append(description);
				str.append("</option>");
				str.append("</select>");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("date".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");			
				str.append("<div class=\"input-group\"><input type=\"text\" name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				str.append("value=\"${bean.");
				str.append(fieldName.toLowerCase());
				str.append("}\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"form-control\" onfocus=\"WdatePicker({dateFmt:\'yyyy-MM-dd\'})\" />");
				str.append("<span class=\"input-group-addon\"><i class=\"ace-icon fa fa-calendar\"></i> </span></div>");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("textarea".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<textarea name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"col-xs-12\" >");
				str.append("</textarea>");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("file".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<input type=\"file\" name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"col-xs-12\" />");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("checkbox".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"checkbox col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<label><input type=\"checkbox\" name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" value=\"\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"ace col-xs-12\" /><span class=\"lbl\">");
				str.append(description);
				str.append("</span></label></div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("radio".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"radio col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<label><input type=\"radio\" name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" value=\"\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"ace col-xs-12\" /><span class=\"lbl\">");
				str.append(description);
				str.append("</span></label></div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("label".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<label for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"col-xs-12\" >");
				str.append(description);
				str.append("</label>");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}else if("hidden".equals(type)){
				str.append("<label class=\"control-label col-xs-12 col-sm-3 no-padding-right\" for=\"");
				str.append(fieldName.toLowerCase());
				str.append("\">");
				str.append(description);
				str.append("</label>");
				str.append("<div class=\"col-xs-12 col-sm-9\">");
				str.append("<div class=\"col-sm-6\"><!-- class=\"clearfix\" -->");				
				str.append("<input type=\"hidden\" name=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" id=\"");
				str.append(fieldName.toLowerCase());
				str.append("\" ");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append(getValidateMsg(description,validator));
				}
				str.append("class=\"col-xs-12\" />");
				str.append("</div>");
				str.append("<div class=\"col-sm-3\">");
				if(!"".equals(validator) && validator != null && !"choose".equals(validator)){
					str.append("<div class=\"info\"><span class=\"Validform_checktip\"></span><span class=\"dec\"><s class=\"dec1\">&#9670;</s><s class=\"dec2\">&#9670;</s></span></div>");
				}
				str.append("</div>");
				str.append("</div>");
				
			}
		}
		return str.toString();
	}
	
	/**
	 * 
	 * @param type 数据字段类型
	 * @param fieldName 字段名称
	 * @param description 字段描述
	 * @return
	 */
	public String getQueryType(String isRequirement,String description){
		StringBuffer str = new StringBuffer("");
		if("true".equals(isRequirement)){
			str.append(description);
			str.append(",");
			return str.toString();
		}else{
			return "";
		}
	}
	
	/**
	 * 
	 * @param description 字段描述信息
	 * @param validator 验证规则
	 * @return
	 */
	public String getValidateMsg(String description,String validator){
		StringBuffer str = new StringBuffer("");
		if("required".equals(validator)){
			str.append("datatype=\"*\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("信息必填!");
			str.append("\" ");
		}else if("*6-16".equals(validator)){
			str.append("datatype=\"*6-16\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("6到16位任意字符!");
			str.append("\" ");
		}else if("isNumber".equals(validator)){
			str.append("datatype=\"n\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("必须是数字!");
			str.append("\" ");
		}else if("n6-16".equals(validator)){
			str.append("datatype=\"n6-16\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("6到16位数字!");
			str.append("\" ");
		}else if("isInvalid".equals(validator)){
			str.append("datatype=\"s\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("不能输入特殊字符!");
			str.append("\" ");
		}else if("s6-16".equals(validator)){
			str.append("datatype=\"s6-16\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("6到18位字符!");
			str.append("\" ");
		}else if("isEmail".equals(validator)){
			str.append("datatype=\"e\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("邮箱地址格式不正确!");
			str.append("\" ");
		}else if("isMobile".equals(validator)){
			str.append("datatype=\"m\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("请填写手机号码!");
			str.append("\" ");
		}else if("isZip".equals(validator)){
			str.append("datatype=\"p\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("请填写邮政编码!");
			str.append("\" ");
		}else if("isUrl".equals(validator)){
			str.append("datatype=\"*\" ");
			str.append("nullmsg=\"");
			str.append(description);
			str.append("请填写正确网址!");
			str.append("\" ");
		}
		
		return str.toString();
	}
}
