// Decompiled by Jad v1.5.8e2. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://kpdus.tripod.com/jad.html
// Decompiler options: packimports(3) fieldsfirst ansi space 
// Source File Name:   ExportExcelService.java

package com.dimine.sys.service;

import com.dimine.base.dao.BaseDao;
import com.dimine.base.service.BaseService;
import com.dimine.sys.dao.ExportExcelDao;
import com.dimine.sys.entity.LogExceptionEntity;
import com.dimine.sys.entity.LogLoginEntity;
import com.dimine.sys.entity.LogUserEntity;
import com.opensymphony.xwork2.ActionContext;
import java.io.*;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("exportExcelService")
public class ExportExcelService<T> extends BaseService<T> {

	@Autowired
	private ExportExcelDao<T> dao;

	public void executeUseFile(List list) throws IOException {
		HttpServletResponse response;
		String filedownload;
		OutputStream outp;
		FileInputStream in;
		BufferedInputStream bis;
		BufferedOutputStream bos;
		ActionContext ct = ActionContext.getContext();
		response = (HttpServletResponse) ct
				.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
		String src = "c:\\text.txt";
		if (list != null) {
			File f = new File(src);
			BufferedWriter bw = null;
			bw = new BufferedWriter(new FileWriter(f));
			bw.write("用户姓名  使用IP    \t操作功能\t\t操作时间\t\t操作信息\r\n");
			for (int j = 0; j < list.size(); j++) {
				LogUserEntity sb = (LogUserEntity) list.get(j);
				bw.write((new StringBuilder(String.valueOf(sb.getUsername())))
						.append("    ").append(sb.getUserip()).append("    ")
						.append(sb.getUsemodule()).append("    ")
						.append(sb.getUsetime()).append("\t\t")
						.append(sb.getUseoperation()).append("\r\n").toString());
			}

			bw.flush();
			bw.close();
		}
		response.reset();
		response.setContentType("application/x-download");
		filedownload = "c:\\text.txt";
		String filedisplay = (new StringBuilder("操作日志信息("))
				.append((new SimpleDateFormat("yyyy-MM-dd")).format(new Date()))
				.append(").txt").toString();
		filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
		response.addHeader("Content-Disposition", (new StringBuilder(
				"attachment;filename=")).append(filedisplay).toString());
		outp = null;
		in = null;
		bis = null;
		bos = null;
		try {
			in = new FileInputStream(filedownload);
			bis = new BufferedInputStream(in);
			byte b[] = new byte[1024];
			int i = 0;
			outp = response.getOutputStream();
			bos = new BufferedOutputStream(outp);
			while ((i = bis.read(b)) > 0)
				bos.write(b, 0, i);
			bos.flush();
		} catch (Exception exception) {
			if (in != null) {
				in.close();
				in = null;
			}
			if (bis != null) {
				bis.close();
				bis = null;
			}
			if (outp != null) {
				outp.close();
				outp = null;
			}
			if (bos != null) {
				bos.close();
				bos = null;
			}
		}

		if (in != null) {
			in.close();
			in = null;
		}
		if (bis != null) {
			bis.close();
			bis = null;
		}
		if (outp != null) {
			outp.close();
			outp = null;
		}
		if (bos != null) {
			bos.close();
			bos = null;
		}
		if (in != null) {
			in.close();
			in = null;
		}
		if (bis != null) {
			bis.close();
			bis = null;
		}
		if (outp != null) {
			outp.close();
			outp = null;
		}
		if (bos != null) {
			bos.close();
			bos = null;
		}
	}

	public void exportuseExcel(String userip, String startdate, String enddate,
			String useName) throws IOException {
		Map paramMap = new HashMap();
		paramMap.put("userip", userip);
		paramMap.put("startdate", startdate);
		paramMap.put("enddate", enddate);
		paramMap.put("username", useName);
		List list = dao.exportuseExcel(paramMap);
		executeUseFile(list);
	}

	public void exportLoginExcel(LogLoginEntity tmp) throws IOException {
		List list = dao.exportLoginExcel(tmp);
		executeLoginFile(list);
	}
	public void executeLoginFile(List list)
	throws IOException
{
	HttpServletResponse response;
	String filedownload;
	OutputStream outp;
	FileInputStream in;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	ActionContext ct = ActionContext.getContext();
	response = (HttpServletResponse)ct.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
	String src = "c:\\text.txt";
	if (list != null)
	{
		File f = new File(src);
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("登陆用户\t\t登陆IP\t\t登陆时间\t\t登陆信息\r\n");
		for (int j = 0; j < list.size(); j++)
		{
			LogLoginEntity sb = (LogLoginEntity)list.get(j);
			bw.write((new StringBuilder(String.valueOf(sb.getLoginname()))).append("    ").append(sb.getLoginip()).append("    ").append(sb.getLogintime()).append("\t").append(sb.getLoginmessage()).append("\r\n").toString());
		}

		bw.flush();
		bw.close();
	}
	response.reset();
	response.setContentType("application/x-download");
	filedownload = "c:\\text.txt";
	String filedisplay = (new StringBuilder("登录日志信息(")).append((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())).append(").txt").toString();
	filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
	response.addHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(filedisplay).toString());
	outp = null;
	in = null;
	bis = null;
	bos = null;
	try
	{
		in = new FileInputStream(filedownload);
		bis = new BufferedInputStream(in);
		byte b[] = new byte[0x100000];
		int i = 0;
		outp = response.getOutputStream();
		bos = new BufferedOutputStream(outp);
		while ((i = bis.read(b)) > 0) 
			bos.write(b, 0, i);
		bos.flush();
	}
	catch (Exception exception)
	{
		if (in != null)
		{
			in.close();
			in = null;
		}
		if (bis != null)
		{
			bis.close();
			bis = null;
		}
		if (outp != null)
		{
			outp.close();
			outp = null;
		}
		if (bos != null)
		{
			bos.close();
			bos = null;
		}
	}
	Exception exception1;
	if (in != null)
	{
		in.close();
		in = null;
	}
	if (bis != null)
	{
		bis.close();
		bis = null;
	}
	if (outp != null)
	{
		outp.close();
		outp = null;
	}
	if (bos != null)
	{
		bos.close();
		bos = null;
	}
	if (in != null)
	{
		in.close();
		in = null;
	}
	if (bis != null)
	{
		bis.close();
		bis = null;
	}
	if (outp != null)
	{
		outp.close();
		outp = null;
	}
	if (bos != null)
	{
		bos.close();
		bos = null;
	}
}

	public void exportExcel(LogExceptionEntity tmp) throws IOException {
		List list = dao.exportExceptionExcel(tmp);
		executeExceptionFile(list);
	}
	public void executeExceptionFile(List list)
	throws IOException
{
	HttpServletResponse response;
	String filedownload;
	OutputStream outp;
	FileInputStream in;
	BufferedInputStream bis;
	BufferedOutputStream bos;
	ActionContext ct = ActionContext.getContext();
	response = (HttpServletResponse)ct.get("com.opensymphony.xwork2.dispatcher.HttpServletResponse");
	String src = "c:\\text.txt";
	if (list != null)
	{
		File f = new File(src);
		BufferedWriter bw = null;
		bw = new BufferedWriter(new FileWriter(f));
		bw.write("异常功能\t     使用ip     \t异常时间\t\t异常信息\r\n");
		for (int j = 0; j < list.size(); j++)
		{
			LogExceptionEntity sb = (LogExceptionEntity)list.get(j);
			bw.write((new StringBuilder(String.valueOf(sb.getExceptionmodule()))).append("    ").append(sb.getUseIP()).append("    ").append(sb.getExceptiontime()).append("    ").append(sb.getExceptioncontent()).append("\r\n").toString());
		}

		bw.flush();
		bw.close();
	}
	response.reset();
	response.setContentType("application/x-download");
	filedownload = "c:\\text.txt";
	String filedisplay = (new StringBuilder("错误日志信息(")).append((new SimpleDateFormat("yyyy-MM-dd")).format(new Date())).append(").txt").toString();
	filedisplay = URLEncoder.encode(filedisplay, "UTF-8");
	response.addHeader("Content-Disposition", (new StringBuilder("attachment;filename=")).append(filedisplay).toString());
	outp = null;
	in = null;
	bis = null;
	bos = null;
	try
	{
		in = new FileInputStream(filedownload);
		bis = new BufferedInputStream(in);
		byte b[] = new byte[0x100000];
		int i = 0;
		outp = response.getOutputStream();
		bos = new BufferedOutputStream(outp);
		while ((i = bis.read(b)) > 0) 
			bos.write(b, 0, i);
		bos.flush();
	}
	catch (Exception exception)
	{
		if (in != null)
		{
			in.close();
			in = null;
		}
		if (bis != null)
		{
			bis.close();
			bis = null;
		}
		if (outp != null)
		{
			outp.close();
			outp = null;
		}
		if (bos != null)
		{
			bos.close();
			bos = null;
		}	
	}
	Exception exception1;
	if (in != null)
	{
		in.close();
		in = null;
	}
	if (bis != null)
	{
		bis.close();
		bis = null;
	}
	if (outp != null)
	{
		outp.close();
		outp = null;
	}
	if (bos != null)
	{
		bos.close();
		bos = null;
	}
	if (in != null)
	{
		in.close();
		in = null;
	}
	if (bis != null)
	{
		bis.close();
		bis = null;
	}
	if (outp != null)
	{
		outp.close();
		outp = null;
	}
	if (bos != null)
	{
		bos.close();
		bos = null;
	}
}

	public String getBiztypename() {
		return "导出EXCEL文件";
	}

	@Override
	public BaseDao<T> getDao() {
		// TODO Auto-generated method stub
		return dao;
	}

}
