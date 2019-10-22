package com.dimine.bogen.util;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;
/**
 * 
 * <p> StaticFreemarker </p>
 * 
 * @author  Aaron
 * @version 2014-12-12
 */
public class StaticFreemarker {
	/**
	 * 
	 * @param ftl 模板文件
	 * @param outName 输出文件
	 * @param map 数据对象
	 * @param fileName 文件路径（文件夹名称）
	 * @param outPath 文件输出路径（文件夹名称）
	 * @throws IOException
	 * @throws TemplateException
	 */
	public static void init(String ftl,String outName,Map map,String filepath,String outPath,HttpServletRequest request) throws IOException, TemplateException
	{
		Configuration freemarkerCfg = new Configuration();
		//从什么地方加载freemarker模板文件
		freemarkerCfg.setServletContextForTemplateLoading(request.getSession().getServletContext(), "/webpage/manager/bogen/"+filepath);
		freemarkerCfg.setEncoding(Locale.getDefault(), "UTF-8");
		Template template;
			template = freemarkerCfg.getTemplate(ftl);//定义Template对象
			template.setEncoding("UTF-8");
			String path= request.getRealPath("/")+outPath+"\\";//取得项目路径
			File f = new File(path);//输出文件路径，如果不存在则创建
			path= request.getRealPath("/")+outPath+"\\";
			if(!f.exists()){
				f.mkdirs();
			}
			File outFile = new File(path+outName);
		 
			Writer out = new BufferedWriter(new OutputStreamWriter(
					new FileOutputStream(outFile), "UTF-8"));		
			template.process(map,out);
			out.flush();
			out.close();
	}
}
