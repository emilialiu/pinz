
package com.dimine.taglib;

import com.dimine.base.taglib.AbstractTag;
import com.dimine.base.util.WebUtil;
import com.dimine.sys.data.ResourceManager;
import com.dimine.taglib.entity.ResourceBean;

import java.io.IOException;
import java.util.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.jsp.*;

// Referenced classes of package com.dimine.framework.taglib:
//			AbstractDimineTag

public class ResourceManagerTag extends AbstractTag
{

	private static final long serialVersionUID = 0x30ece61b204913bbL;
	public static final String SELECT = "select";
	public static final String CHECKBOX = "checkbox";
	public static final String RADIO = "radio";
	private String sourcename;
	private String fixValue;
	private String showStyle;
	private String colWidths;
	private String isnull;
	private String parentID;
	private String language;
	private String deptid;
	public ResourceManagerTag()
	{
		sourcename = "";
		fixValue = "";
		showStyle = "select";
		colWidths = "";
		isnull = "true";
		parentID = "";
		language = "";
	}

	public int doStartTag()
		throws JspException
	{
		String as[];
		try
		{
			Object value = getCurrentValue();
			if (fixValue != null && !fixValue.equals(""))
				value = fixValue;
			StringBuffer results = new StringBuffer("");
			List datalist = null;
			String lan = WebUtil.getCurrentLanguage((HttpServletRequest)pageContext.getRequest());
			as = new String[3];
			as[0] = lan;
			as[1] = parentID;
			datalist = ResourceManager.getInstance().getData(sourcename, as,deptid);
			String style = "width:30px";
			String check = "onkeydown=\"return isNumber(this, event, 16, 2)\"";
			if ("checkbox".equals(showStyle))
			{
				String Flag = "";
				if (!"".equals(disabled))
					Flag = "readonly";
				value = getCurrentObject();
				if (datalist != null)
				{
					int length = datalist.size();
					String ww[] = colWidths.split(",");
					Map wwMap = new HashMap();
					int collength = ww.length;
					if (collength == 1 && ww[0].trim().equals(""))
						collength = 0;
					for (int i = 0; i < collength; i++)
						wwMap.put(String.valueOf(i), (new StringBuilder(" width='")).append(ww[i]).append("' ").toString());

					if (collength <= 0)
						collength = length;
					results.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					String strTemp[] = new String[0];
					if (value instanceof String[])
						strTemp = (String[])value;
					else
						strTemp = (new String[] {
							(String)value
						});
					Map dataMap = new HashMap();
					int dataLength = strTemp.length;
					for (int i = 0; i < dataLength; i++)
					{
						String tmpd = strTemp[i];
						int idx = tmpd.indexOf("=");
						String vcode = tmpd;
						String vvalue = "";
						if (idx > 0)
						{
							vcode = tmpd.substring(0, idx);
							vvalue = tmpd.substring(idx + 1);
						}
						dataMap.put(vcode, vvalue);
					}

					for (int i = 0; i < length; i++)
					{
						ResourceBean rb = (ResourceBean)datalist.get(i);
						Object tmpObj = dataMap.get(rb.getCode());
						String currentCode = "";
						String currentValue = "";
						String checkValue = rb.getCode();
						if (tmpObj != null)
						{
							currentCode = rb.getCode();
							currentValue = (String)tmpObj;
						}
						if (i % collength == 0)
						{
							if (i != 0)
								results.append("</tr>");
							results.append("<tr>");
						}
						Object wwO = wwMap.get(String.valueOf(i % collength));
						String colsWW = "";
						if (wwO != null)
							colsWW = wwO.toString();
						results.append((new StringBuilder("<td ")).append(colsWW).append(">").toString());
						String inputStr = "";
						String ckStr = "";
						String afterStr = "";
						int index = currentValue.lastIndexOf("=");
						String cValue = currentValue;
						String otherValue = "";
						if (index >= 0)
						{
							currentValue = cValue.substring(0, index);
							otherValue = cValue.substring(index + 1);
						}
						ckStr = "onclick=\"";
						if (getOnchange() != null && !getOnchange().trim().equals(""))
							ckStr = (new StringBuilder(String.valueOf(ckStr))).append(getOnchange()).append(";").toString();
						else
						if (getOnclick() != null && !getOnclick().trim().equals(""))
							ckStr = (new StringBuilder(String.valueOf(ckStr))).append(getOnclick()).append(";").toString();
						ckStr = (new StringBuilder(String.valueOf(ckStr))).append("\"").toString();
						if (currentCode == null || currentCode.equals(""))
							results.append((new StringBuilder("&nbsp;<nobr><input ")).append(disabled).append(" type='checkbox' ").append(" id='").append(rb.getCode()).append("' name='").append(getName()).append("' value='").append(checkValue).append("' baseValue='").append(rb.getCode()).append("'").append("/>&nbsp;").append(rb.getName()).append("</nobr>").toString());
						else
							results.append((new StringBuilder("&nbsp;<nobr><input ")).append(disabled).append(" type='checkbox' ").append(" id='").append(rb.getCode()).append("' name='").append(getName()).append("' value='").append(checkValue).append("' baseValue='").append(rb.getCode()).append("'").append(" checked/>&nbsp;").append(rb.getName()).append("</nobr>").toString());
						results.append("</td>");
					}

					if (collength % collength != 0)
						results.append("</tr>");
					results.append("</table>");
				}
				JspWriter out = pageContext.getOut();
				out.println(results.toString());
			} else
			if ("radio".equals(showStyle))
			{
				if (datalist != null)
				{
					int length = datalist.size();
					String ww[] = colWidths.split(",");
					Map wwMap = new HashMap();
					int collength = ww.length;
					if (collength == 1 && ww[0].trim().equals(""))
						collength = 0;
					for (int i = 0; i < collength; i++)
						wwMap.put(String.valueOf(i), (new StringBuilder(" width='")).append(ww[i]).append("' ").toString());

					if (collength <= 0)
						collength = length;
					results.append("<table width=\"100%\" border=\"0\" cellspacing=\"0\" cellpadding=\"0\">");
					String strTemp[] = new String[0];
					if (value instanceof String[])
						strTemp = (String[])value;
					else
						strTemp = (new String[] {
							(String)value
						});
					Map dataMap = new HashMap();
					int dataLength = strTemp.length;
					for (int i = 0; i < dataLength; i++)
					{
						String tmpd = strTemp[i];
						int idx = tmpd.indexOf("=");
						String vcode = tmpd;
						String vvalue = "";
						if (idx > 0)
						{
							vcode = tmpd.substring(0, idx);
							vvalue = tmpd.substring(idx + 1);
						}
						dataMap.put(vcode, vvalue);
					}

					for (int i = 0; i < length; i++)
					{
						ResourceBean rb = (ResourceBean)datalist.get(i);
						Object tmpObj = dataMap.get(rb.getCode());
						String currentCode = "";
						String checkValue = rb.getCode();
						if (tmpObj != null)
							currentCode = rb.getCode();
						if (i % collength == 0)
						{
							if (i != 0)
								results.append("</tr>");
							results.append("<tr>");
						}
						Object wwO = wwMap.get(String.valueOf(i % collength));
						String colsWW = "";
						if (wwO != null)
							colsWW = wwO.toString();
						results.append((new StringBuilder("<td ")).append(colsWW).append(">").toString());
						String ckStr = "";
						String afterStr = "";
						ckStr = "onclick=\"";
						if (getOnchange() != null && !getOnchange().trim().equals(""))
							ckStr = (new StringBuilder(String.valueOf(ckStr))).append(getOnchange()).append(";").toString();
						else
						if (getOnclick() != null && !getOnclick().trim().equals(""))
							ckStr = (new StringBuilder(String.valueOf(ckStr))).append(getOnclick()).append(";").toString();
						if (!"yesno".equals(getSourcename()))
							ckStr = (new StringBuilder(String.valueOf(ckStr))).append("var name=this.name;var objs=this.form[name];for(i=0;i<objs.length;i++){").append("var vname=objs[i].baseValue+'_value';if(this.form[vname]!=null){if(objs[i].checked) { this.form[vname].disabled=false; document.all[objs[i].baseValue+'_div'].style.display='inline'; objs[i].value=this.baseValue+'='+this.form[vname].value;} ").append("else {this.form[vname].disabled=true;document.all[objs[i].baseValue+'_div'].style.display='none'; objs[i].value=objs[i].baseValue;}}}").toString();
						ckStr = (new StringBuilder(String.valueOf(ckStr))).append("\"").toString();
						if (currentCode == null || currentCode.equals(""))
							results.append((new StringBuilder("&nbsp;<input ")).append(disabled).append(" type='radio' ").append(ckStr).append(" id='").append(rb.getCode()).append("' name='").append(getName()).append("' value='").append(checkValue).append("' baseValue='").append(rb.getCode()).append("'/>&nbsp;").append(rb.getName()).append(afterStr).toString());
						else
							results.append((new StringBuilder("&nbsp;<input ")).append(disabled).append(" type='radio' ").append(ckStr).append(" id='").append(rb.getCode()).append("' name='").append(getName()).append("' value='").append(checkValue).append("' baseValue='").append(rb.getCode()).append("' checked/>&nbsp;").append(rb.getName()).append(afterStr).toString());
						results.append("</td>");
					}

					if (collength % collength != 0)
						results.append("</tr>");
					results.append("</table>");
				}
				JspWriter out = pageContext.getOut();
				out.println(results.toString());
			} else
			{
				results.append((new StringBuilder("<select ")).append(disabled).append(" ").toString());
				results.append(" name=\"");
				results.append(getName());
				results.append("\" ");
				results.append(" id=\"");
				results.append(getName());
				results.append("\" ");
				results.append(getCommonInfo());
				results.append(">");
				results.append("");
				if ("true".equals(isnull))
					results.append("<option value=\"\">--请选择--</option>");
				if (datalist != null)
				{
					int length = datalist.size();
					for (int i = 0; i < length; i++)
					{
						ResourceBean rb = (ResourceBean)datalist.get(i);
						String selected = "";
						String strTemp = (String)value;
						if (strTemp.equalsIgnoreCase(rb.getCode()))
							selected = "selected";
						results.append("<option value=\"");
						results.append(rb.getCode());
						results.append("\"");
						results.append(selected);
						results.append(">");
						results.append(rb.getName());
						results.append("</option>");
						results.append("");
					}

				}
				results.append("</select>");
				JspWriter out = pageContext.getOut();
				out.print(results.toString());
			}
		}
		catch (IOException ioexception) { }
		return super.doStartTag();
	}

	public String getSourcename()
	{
		return sourcename;
	}

	public void setSourcename(String sourcename)
	{
		this.sourcename = sourcename;
	}

	public String getIsnull()
	{
		return isnull;
	}

	public void setIsnull(String isnull)
	{
		this.isnull = isnull;
	}

	public String getShowStyle()
	{
		return showStyle;
	}

	public void setShowStyle(String showStyle)
	{
		this.showStyle = showStyle;
	}

	public String getColWidths()
	{
		return colWidths;
	}

	public void setColWidths(String colWidths)
	{
		this.colWidths = colWidths;
	}

	public String getFixValue()
	{
		return fixValue;
	}

	public void setFixValue(String fixValue)
	{
		this.fixValue = fixValue;
	}

	public String getLanguage()
	{
		return language;
	}

	public void setLanguage(String language)
	{
		this.language = language;
	}

	public String getParentID()
	{
		return parentID;
	}

	public void setParentID(String parentID)
	{
		this.parentID = parentID;
	}

	public String getDeptid() {
		return deptid;
	}

	public void setDeptid(String deptid) {
		this.deptid = deptid;
	}
	
}
