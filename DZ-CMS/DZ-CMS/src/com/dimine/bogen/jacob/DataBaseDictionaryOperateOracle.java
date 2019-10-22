package com.dimine.bogen.jacob;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import javax.servlet.http.HttpServletRequest;
import com.dimine.bogen.action.Decoder;
  
/**  
 * 操作oracle数据字典  
 * 主要实现对oracle数据库的查询返回信息放在map中  
 * @author Aaron 2014-12-12
 *  
 */  
public class DataBaseDictionaryOperateOracle {   
  
    private static Statement stmt = null;   
    private static ResultSet rs = null;   
    private static Connection con = null; 
    static String tempArr[][] = null;//页面接收参数  
  
    @SuppressWarnings("unchecked")   
    public static LinkedHashMap dealDataBase(String tablenameObj,String thisweek,HttpServletRequest request){
    	//如果表字段为空代表生成所有表到word文档
    	if(!"".equals(thisweek)&&thisweek!=null){
    		tempArr = Decoder.decode(thisweek);    		
    	}
        try {
            con = (Connection) request.getSession().getAttribute("Connection");   
            if(con == null){
            	return null;
            }
            stmt = con.createStatement();
        } catch (SQLException e) {  
            e.printStackTrace();
            return null;
        }   
        String username = (String)request.getSession().getAttribute("username");
        username = username.toUpperCase();
        StringBuffer strbuf = new StringBuffer();   
        //如果表名为空代表生成所有表到word文档
        if("".equals(tablenameObj)||tablenameObj==null){
        	strbuf.append("SELECT C.comments as tabcomments, A.*,B.comments as colcomments");   
        	strbuf.append(" FROM all_tab_columns A,user_col_comments B,user_tab_comments C,all_tables D");        	
        	strbuf.append(" WHERE A.owner = '"+username+"' and D.owner = '"+username+"' and D.table_name=A.table_name and D.table_name=B.table_name and A.column_name=B.column_name and D.table_name=C.table_name ");
        	strbuf.append(" and A.table_name != 'CRI_EVALUATION_MSG' ");        	
        	strbuf.append(" ORDER BY D.table_name");
        }else{
        	strbuf.append("SELECT C.comments as tabcomments, A.*,B.comments as colcomments");
        	strbuf.append(" FROM all_tab_columns A,user_col_comments B,user_tab_comments C,all_tables D");
        	strbuf.append(" WHERE A.table_name='"+tablenameObj+"' and A.owner = '"+username+"' and D.owner = '"+username+"' and D.table_name=A.table_name and D.table_name=B.table_name and A.column_name=B.column_name and D.table_name=C.table_name");
        }
        System.out.println("====查询库中表信息语句====>"+strbuf);
        LinkedHashMap map = new LinkedHashMap<String, ArrayList>();
        try {   
            rs = stmt.executeQuery(strbuf.toString()); 
            String tb = "";
            ArrayList list = null;
            String tbname = "";
            while (rs.next()) {   
                // 对每个表生成一个新的sheet,并以表名命名   
                String tablename = rs.getString("TABLE_NAME");
                String tabcomments = rs.getString("TABCOMMENTS");
              //如果表字段解析出来不为空代表生成该表到word文档
                if (tempArr != null) {
        			for (int i = 0; i < tempArr.length; i++) {
        				if(tempArr[i][1].equals(rs.getString("COLUMN_NAME"))){//页面选择的字段放入list写入word中
        					//如果tablename和上次循环的不一样,则说明到了一个新的表,重新实例话list   
        	                if (!tbname.equals(rs.getString("TABLE_NAME"))) {
        	                    list = new ArrayList();   
        	                    DataBaseInfo databaseInfoHead = new DataBaseInfo();   
        	                    databaseInfoHead.setColumn_Name("字段名");   
        	                    databaseInfoHead.setData_type("字段类型");   
        	                    databaseInfoHead.setData_length("字段长度");   
        	                    databaseInfoHead.setData_null("是否为空");   
        	                    databaseInfoHead.setData_comments("字段描述");    
        	                    list.add(databaseInfoHead);   
        	                } else {   
        	                    if (tb.length() > 0) {
        	                        map.put(tb, list);//如果不是第一次循环,把list放入map
        	                    }   
        	                }   
        	                DataBaseInfo databaseInfo = new DataBaseInfo();   
        	                databaseInfo.setColumn_Name(rs.getString("COLUMN_NAME"));   
        	                databaseInfo.setData_comments(tempArr[i][2]);
        	                databaseInfo.setData_null(rs.getString("NULLABLE"));
        	                String type = rs.getString("DATA_TYPE");   
        	                databaseInfo.setData_type(type);   
        	  
        	                String data_length = "";   
        	                if (!type.equals("NUMBER")) {   
        	                    data_length = rs.getString("DATA_LENGTH") + "\t";   
        	                } else {   
        	                    String scale = rs.getString("DATA_SCALE");
        	                    String DATA_PRECISION = rs.getString("DATA_PRECISION");
        	                    data_length = DATA_PRECISION + "," + scale + "\t";
        	                }   
        	                databaseInfo.setData_length(data_length);
        	                list.add(databaseInfo);
        	                if(!"".equals(tabcomments)&&tabcomments!=null){
        	                	tb=tablename + "(" +tabcomments+ ")";
        	                }else{
        	                	tb=tablename;
        	                }
        	                tbname = tablename;
        				}
        			}
        		}else{
        			//如果表字段为空代表生成所有表到word文档
        			//如果tablename和上次循环的不一样,则说明到了一个新的表,重新实例话list  
	                if (!tbname.equals(rs.getString("TABLE_NAME"))) {
	                    list = new ArrayList();   
	                    DataBaseInfo databaseInfoHead = new DataBaseInfo();   
	                    databaseInfoHead.setColumn_Name("字段名");   
	                    databaseInfoHead.setData_type("字段类型");   
	                    databaseInfoHead.setData_length("字段长度");   
	                    databaseInfoHead.setData_null("是否为空");   
	                    databaseInfoHead.setData_comments("字段描述");    
	                    list.add(databaseInfoHead);   
	                } else {   
	                    if (tb.length() > 0) {   
	                        map.put(tb, list);//如果不是第一次循环,把list放入map
	                    }   
	                }   
	                DataBaseInfo databaseInfo = new DataBaseInfo();   
	                databaseInfo.setColumn_Name(rs.getString("COLUMN_NAME"));
	                databaseInfo.setData_comments(rs.getString("COLCOMMENTS"));
	                databaseInfo.setData_null(rs.getString("NULLABLE"));
	                String type = rs.getString("DATA_TYPE");   
	                databaseInfo.setData_type(type);   
	  
	                String data_length = "";   
	                if (!type.equals("NUMBER")) {   
	                    data_length = rs.getString("DATA_LENGTH") + "\t";   
	                } else {   
	                    String scale = rs.getString("DATA_SCALE");
	                    String DATA_PRECISION = rs.getString("DATA_PRECISION");
	                    data_length = DATA_PRECISION + "," + scale + "\t";
	                }   
	                databaseInfo.setData_length(data_length);   
	                list.add(databaseInfo);
	                if(!"".equals(tabcomments)&&tabcomments!=null){
	                	tb=tablename + "(" +tabcomments+ ")";
	                }else{
	                	tb=tablename;
	                }
	                tbname = tablename;
        		}
            }   
            map.put(tb, list);// 最后一条记录   
  
        } catch (SQLException e) {   
            e.printStackTrace();   
        } 
        return map;   
    }     
}