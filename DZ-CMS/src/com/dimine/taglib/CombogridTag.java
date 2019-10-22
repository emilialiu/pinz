package com.dimine.taglib;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.JspWriter;

import com.dimine.base.taglib.AbstractTag;

public class CombogridTag extends AbstractTag {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public int doStartTag() throws JspException{
		
		StringBuffer results = new StringBuffer();
		
		results.append("<select id=\""+getName()+"\" style=\""+getCssStyle()+"\"></select>");
		
		try {
			JspWriter out = pageContext.getOut();
			out.print(results.toString());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
}
