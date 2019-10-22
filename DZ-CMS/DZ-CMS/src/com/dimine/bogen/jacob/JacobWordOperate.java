package com.dimine.bogen.jacob;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import com.jacob.activeX.ActiveXComponent;
import com.jacob.com.ComThread;
import com.jacob.com.Dispatch;
import com.jacob.com.Variant;
  
/**  
 * jacob实现转化为word文件   
 * @author Aaron 2014-12-12
 *  
 */  
public class JacobWordOperate {  

    @SuppressWarnings("unchecked")   
	public boolean operate(String tablenameE,String tablenameC,String thisweek,HttpServletRequest request) { 
    	System.out.println("开始查询数据===");
    	LinkedHashMap dbmap = DataBaseDictionaryOperateOracle.dealDataBase(tablenameE,thisweek,request);
        if(dbmap==null){
        	System.out.println("表、字段数据为空===");
        	return false;
        }        
        System.out.println("该用户库中有"+dbmap.size()+"个表！");
        
        //每个文件的表个数，超过50就重新生成文件，提高文件执行效率
        int pagesize = 50;
        if(dbmap.size() > pagesize){
        	Iterator ito = dbmap.keySet().iterator(); 
            LinkedHashMap submap = new LinkedHashMap<String, ArrayList>();
            int tableN = 1;
            int subno = 0;//生成几个word
            while (ito.hasNext()) {
            	 String tableName = (String) ito.next(); 
                 ArrayList list = (ArrayList) dbmap.get(tableName);   
                 submap.put(tableName, list);
                 if(tableN%pagesize == 0){
                	 //生成word文档
                	 gendoc(submap,String.valueOf(subno),subno,pagesize,request);
                	 subno = subno + 1;
                	 submap.clear();
                 }
            	 tableN = tableN+1;
            }
            if(submap.size() > 0){
           	 	gendoc(submap,String.valueOf(subno),subno,pagesize,request);
           	 	subno = subno + 1;
            }
            System.out.println("所有表格已经处理完毕！！！");
            System.out.print("共生成了"+String.valueOf(subno)+"个文档");
            return true;    
        }else{
        	return gendoc(dbmap,"",0,0,request);
        }
    }   
    private boolean gendoc(LinkedHashMap map,String subno,int pagenum,int pagesize,HttpServletRequest request){
    	/**
         * 开始启动word操作
         */
		ComThread.InitSTA();// 初始化com的线程，非常重要！！使用结束后要调用 realease方法
    
        ActiveXComponent wordApp = new ActiveXComponent("Word.Application"); // 启动word 
        Dispatch wordObject = (Dispatch) wordApp.getObject();
        Dispatch.put(wordObject, "Visible", new Variant(false));// false设置word不可见
        Dispatch docs = wordApp.getProperty("Documents").toDispatch();

        // 判断保存路径文件是否存在
        String path = request.getRealPath("/")+"/upfile/word/";
        File f = new File(path);
        if(!f.exists()){
        	f.mkdirs();
        }
        String filePath = path+request.getSession().getId()+subno+".doc";
        System.out.println("WORD文件生成路径为："+filePath);
        File file = new File(filePath);
        Dispatch document = null;
        if(!file.exists()){
        	//不存在则新建文档
        	document = Dispatch.call(docs, "Add").toDispatch();// create
        }else{
        	//否则直接打开已经存在文档
        	document = Dispatch.call(docs, "Open",filePath).toDispatch();// open
        }
        
        //开始写word文档内容                
        Dispatch wordContent = Dispatch.get(document, "content").toDispatch();
        Dispatch selection = Dispatch.get(wordObject, "Selection").toDispatch();
        Dispatch align = Dispatch.get(selection, "ParagraphFormat").toDispatch(); // 行列格式化需要的对象   
        Dispatch font = Dispatch.get(selection, "Font").toDispatch(); // 字型格式化需要的对象
        Dispatch.put(align, "Alignment", "3"); // 1:置中 2:靠右 3:靠左  
        Dispatch.put(font, "Bold", "1"); // 字型粗体   
        Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色
        
    	 /**
         * 开始写入表格
         */    	
        Iterator ito = map.keySet().iterator(); 
        System.out.println("此批次("+String.valueOf(pagenum+1)+")转换有"+map.size()+"个表！");
        int tableN = 1;
        while (ito.hasNext()) {
        	System.out.println("\n开始处理第"+tableN+"个表......");
            Dispatch.call(selection, "EndKey", new Variant(6));//定位到文档最后位置写入内容
            if(tableN == 1){
            	request.getSession().setAttribute("tableNumber",1);
            }
        	//从session中拿当前用户生成表格写入word的序号
            if(request.getSession().getAttribute("tableNumber") != null){
            	tableN = Integer.parseInt(request.getSession().getAttribute("tableNumber").toString());
            }else{
            }
            String tableName = (String) ito.next();//从迭代中获取中英文表名
            System.out.println("该表表名为===>"+tableName);
            Dispatch.call(selection, "TypeText", (pagesize*pagenum+tableN)+"、"+tableName); // 写入标题内容
            	
            ArrayList list = (ArrayList) map.get(tableName);//根据表名获取相应的字段信息            
            System.out.println("该表有字段数："+list.size()+"个");
            
            Dispatch tables = Dispatch.get(wordContent, "Tables").toDispatch(); //表格对象
            Dispatch range = Dispatch.get(selection, "Range").toDispatch();   
            Dispatch.call(tables, "Add", range, list.size(),   
                    new Variant(5), new Variant(1)).toDispatch(); // 设置行数,列数,表格外框宽度
            
            //定位要填充的表格
            Dispatch newtables = Dispatch.get(wordContent, "Tables").toDispatch();
            Dispatch t1 = null;
			try {
				t1 = Dispatch.call(newtables, "Item", new Variant(tableN)).toDispatch();
			} catch (Exception e) {
				e.printStackTrace();
			} 
            System.out.println("开始填充表格......");
            for (int i = 1; i <= list.size(); i++) {
                // 要填充对应的表格   
                Dispatch.call(Dispatch.get(t1, "columns").toDispatch(), "AutoFit");// 自动调整   
                DataBaseInfo info = (DataBaseInfo) list.get(i-1);   
                Dispatch cell = Dispatch.call(t1, "Cell", new Variant(i),   
                        new Variant(1)).toDispatch();// 行，列                
                Dispatch.call(cell, "Select");   
                Dispatch.put(selection, "Text", info.getColumn_Name()); // 写入word的内容   
                Dispatch.put(font, "Bold", "0"); // 字型粗体(1:粗体 0:取消粗体)   
                Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色 
                Dispatch.put(font, "Italic", "0"); // 斜体 1:斜体 0:取消斜体   
    
                Dispatch cell1 = Dispatch.call(t1, "Cell", new Variant(i),   
                        new Variant(2)).toDispatch();// 行，列   
                Dispatch.call(cell1, "Select");   
                Dispatch.put(selection, "Text", info.getData_type()); // 写入word的内容   
                Dispatch.put(font, "Bold", "0"); // 字型租体(1:租体 0:取消租体)   
                Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色   
                Dispatch.put(font, "Italic", "0"); // 斜体 1:斜体 0:取消斜体   
    
                Dispatch cell2 = Dispatch.call(t1, "Cell", new Variant(i),   
                        new Variant(3)).toDispatch();// 行，列   
                Dispatch.call(cell2, "Select");   
                Dispatch.put(selection, "Text", info.getData_length()); // 写入word的内容   
                Dispatch.put(font, "Bold", "0"); // 字型租体(1:租体 0:取消租体)   
                Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色   
                Dispatch.put(font, "Italic", "0"); // 斜体 1:斜体 0:取消斜体   
  
                Dispatch cell3 = Dispatch.call(t1, "Cell", new Variant(i),   
                        new Variant(4)).toDispatch();// 行，列   
                Dispatch.call(cell3, "Select");   
                Dispatch.put(selection, "Text", info.getData_null()); // 写入word的内容   
                Dispatch.put(font, "Bold", "0"); // 字型租体(1:租体 0:取消租体)   
                Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色   
                Dispatch.put(font, "Italic", "0"); // 斜体 1:斜体 0:取消斜体   
  
  
                Dispatch cell4 = Dispatch.call(t1, "Cell", new Variant(i),   
                        new Variant(5)).toDispatch();// 行，列   
                Dispatch.call(cell4, "Select");   
                Dispatch.put(selection, "Text", info.getData_comments()); // 写入word的内容   
                Dispatch.put(font, "Bold", "0"); // 字型租体(1:租体 0:取消租体)   
                Dispatch.put(font, "Color", "0,0,0,0"); // 字型颜色   
                Dispatch.put(font, "Italic", "0"); // 斜体 1:斜体 0:取消斜体   
            }
            System.out.println("该表格数据填充完毕！");
            Dispatch.call(selection, "MoveDown"); // 光标往下一行(确保下一表格定位到文档最后位置与上一表格不重叠制作 )
        	Dispatch.call(selection, "TypeParagraph"); //空一行段落(确保下一表格定位到文档最后位置与上一表格不重叠制作 )
        	
        	//将当前session下生成的表数放入session中，以便控制同sssion用户在一个word文档中生成多个表格
            tableN = tableN+1;
        	request.getSession().setAttribute("tableNumber",tableN);
        }   
        System.out.println("此批次("+String.valueOf(pagenum+1)+")转换有"+map.size()+"个表已经处理完毕！！");
        //保存文档
        Dispatch.call(document, "SaveAs",path+request.getSession().getId()+subno+".doc");
        Dispatch.call(wordApp,"Quit");
        ComThread.Release();        
    	return true;    	
    }
} 