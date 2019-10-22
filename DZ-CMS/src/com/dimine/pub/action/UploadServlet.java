package com.dimine.pub.action;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.dimine.util.ExcelReadUtil;

public class UploadServlet extends HttpServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public void service(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String savePath = this.getServletConfig().getServletContext()
				.getRealPath("upfile");
		String path = request.getParameter("path") == null ? "" : request
				.getParameter("path") + "\\";
		savePath = savePath + "\\" + path;
		File f1 = new File(savePath);
		System.out.println(savePath);
		if (!f1.exists()) {
			f1.mkdirs();
		}
		DiskFileItemFactory fac = new DiskFileItemFactory();
		ServletFileUpload upload = new ServletFileUpload(fac);
		upload.setHeaderEncoding("utf-8");
		List<FileItem> fileList = null;
		try {
			fileList = upload.parseRequest(request);
		} catch (FileUploadException ex) {
			return;
		}
		Iterator<FileItem> it = fileList.iterator();
		String name = "";
		String extName = "";
		while (it.hasNext()) {
			FileItem item = it.next();
			if (!item.isFormField()) {
				name = item.getName();
				long size = item.getSize();
				String type = item.getContentType();
				System.out.println(size + " " + type);
				if (name == null || name.trim().equals("")) {
					continue;
				}
				// 扩展名格式：
				if (name.lastIndexOf(".") >= 0) {
					extName = name.substring(name.lastIndexOf("."));
				}
				File file = null;
				do {
					// 生成文件名：
					name = UUID.randomUUID().toString();
					file = new File(savePath + name + extName);
				} while (file.exists());
				File saveFile = new File(savePath + name + extName);
				try {
					item.write(saveFile);
					// 读取文件
					// this.readExcel(saveFile);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		System.out.println(savePath + "===>" + name + extName);
		response.getWriter().print( "\\"+path + name + extName);
	}

	/**
	 * 读取并打印excel数据
	 * 
	 * @param file
	 */
	private void readExcel(File file) {
		Map<Integer, List<String>> map = new HashMap<Integer, List<String>>();
		FileInputStream fs;
		try {
			//fs = new FileInputStream(file);
			map = ExcelReadUtil.readExcel(file);

			Set<Integer> set = map.keySet();
			Iterator<Integer> it = set.iterator();
			while (it.hasNext()) {
				Integer i = it.next();
				List<String> list = map.get(i);
				for (int k = 0; k < list.size(); k++) {
					String strVal = list.get(k);
					String[] str = strVal.split("\\$\\$\\$\\$\\$");
					for (int j = 0; j < str.length; j++) {
						// 数据校验
						// validate(str[j], i + 1, k + 1, j + 1);
						System.out.print(str[j] + "  ");
					}
					System.out.println("  ");
				}
			}

		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 数据校验
	 * 
	 * @param data
	 * @param sheetIndex
	 * @param rowIndex
	 * @param cellIndex
	 * @return
	 */
	private String validate(String data, int sheetIndex, int rowIndex,
			int cellIndex) {
		StringBuffer errormsg = new StringBuffer();
		if (data == null || "".equals(data.trim())) {
			errormsg.append("第" + sheetIndex + "sheet第" + rowIndex + "行第"
					+ cellIndex + "列数据为空！");
			System.out.println(errormsg.toString());
		}
		return errormsg.toString();
	}
}
