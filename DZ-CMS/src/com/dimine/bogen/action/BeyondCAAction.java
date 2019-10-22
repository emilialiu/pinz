package com.dimine.bogen.action;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.http.HttpSession;
import org.apache.struts2.convention.annotation.Action;
import org.apache.struts2.convention.annotation.Namespace;
import org.apache.struts2.convention.annotation.Result;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import com.dimine.bogen.jacob.JacobWordOperate;
import com.dimine.bogen.model.BeyondCAEntity;
import com.dimine.bogen.model.ColumnsEntity;
import com.dimine.bogen.util.DBUtile;
import com.dimine.base.action.BaseAction;

@Namespace("/webpage/manager/bogen")
@Scope("request")
@Controller("beyondCAAction")
public class BeyondCAAction extends BaseAction implements Serializable{
	private static final long serialVersionUID = 1L;
	private String func;//指定执行JS方法名
	private String dbtype;//数据库类型
	private String sid;
	private String username;
	private String password;
	private BeyondCAEntity bean = new BeyondCAEntity(); //数据库连接信息实体
	private ColumnsEntity columns = new ColumnsEntity();//列信息实体
	private Connection c = null; //数据库连接
	private Statement stmt=null;
    private ResultSet rs=null;
    private ResultSet rs2=null;
    private String items; //当前用户所用数据库表名串
	private HttpSession session = null; //回话
	private String tablename; //表名
	private String tablecomments; //表注释
	private List columnsList = new ArrayList(); //表字段、注释等信息列表
	private String pk[]; //主键数组
	private String tablenameE;//当前选择的表英文名
	private String tablenameC;//当前选择的表中文名
	private String thisweek;//页面选择列参数信息


	@Override
	public String getBiztypename() {
		// TODO Auto-generated method stub
		return null;
	}

	public BeyondCAEntity getBean() {
		return bean;
	}

	public void setBean(BeyondCAEntity bean) {
		this.bean = bean;
	}
	
	/**
	 * 查询用户所有表
	 */
	@Action(value = "connectDB", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String connectDB() {
		System.out.println("=======开始连接数据库========>");
		String result = SUCCESS;
		session = this.getRequest().getSession();
		session.removeAttribute("Connection");
		session.removeAttribute("username");
		c = new DBUtile().connectDB(this.getDbtype(),this.getSid(), this.getUsername(), this.getPassword());

		if(c==null){
			result = "error";
			setErrorFlag("true");
			setErrorMessage("连接数据库服务器失败，请检查网络或重新输入连接信息！");
			return result;
		}else{
			session.setAttribute("Connection", c);
			session.setAttribute("username", this.getUsername());
		}
        try {
    		System.out.println("=======开始查找数据库表名和表说明========>"+c);
			stmt=c.createStatement();
			if("ORACLE".equals(this.getDbtype())){
				//查询数据库表名和表说明
				rs=stmt.executeQuery("select a.table_name,b.comments from user_tables a,user_tab_comments b where a.table_name = b.table_name");
			}else if("SQLSERVER".equals(this.getDbtype())){
				rs=stmt.executeQuery("select UPPER(name) table_name,'' comments from sysobjects where xtype='U' and name<>'dtproperties' ORDER BY name");
			}else if("MYSQL".equals(this.getDbtype())){
				rs=stmt.executeQuery("SELECT UPPER(TABLE_NAME) table_name,TABLE_COMMENT comments FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA=\'"+this.getSid().substring(this.getSid().lastIndexOf("/")+1)+"\'");
			}
			StringBuffer sb = new StringBuffer();
			sb.append("{tablesinfo:\'");
			sb.append("[");
			while (rs.next()) {
				if(rs.getString("comments")!=null){
					sb.append("{ text: \""+rs.getString("table_name")+" "+rs.getString("comments")+"\", value: \""+rs.getString("table_name")+" "+rs.getString("comments")+"\" },"); 
				}else{
					sb.append("{ text: \""+rs.getString("table_name")+"\", value: \""+rs.getString("table_name")+"\" },"); 
				}
			}
			items = sb.toString();
			items = items.substring(0, items.length()-1) + "]" + "\'}";
		} catch (Exception e) {
			e.printStackTrace();
			result = "error";
			setErrorFlag("true");
			setErrorMessage("数据库查询用户所有表失败，请与管理员联系！");
			return result;
		}
		System.out.println("========数据库表名和表说明信息为=======>"+items);
		this.getRequest().setAttribute("jsonString", items);
		setErrorFlag("success");
		return result;
	}
	
	/**
	 * 根据表名查询表详细信息
	 */
	@Action(value = "doInfo", results = { @Result(name = "success", location = "/webpage/manager/bogen/list.jsp") })
	public String doInfo(){		
		System.out.println("========开始获取表字段名、字段类型、字段说明、表主键列信息=======>"+this.getRequest().getSession());
		String result = SUCCESS;
		String tablenamecomments[] = this.getTablename().split(" ");
		this.setTablename(tablenamecomments[0]);
		if(tablenamecomments.length>1){
			this.setTablecomments(tablenamecomments[1]);
		}else{
			this.setTablecomments("");
		}
		//根据不同的数据库类型执行不同的查询语句
		String sql2 = "";
		String sql3 = "";
		if("ORACLE".equals(this.getDbtype())){
			//查询该表的表字段名、字段类型、字段说明
			sql2 = "SELECT a.COLUMN_NAME,a.DATA_TYPE,b.COMMENTS FROM "+
					" USER_TAB_COLUMNS a,USER_COL_COMMENTS b WHERE "+
						" b.TABLE_NAME = a.TABLE_NAME AND "+
						" b.COLUMN_NAME = a.COLUMN_NAME AND "+
						" a.TABLE_NAME=\'"+this.getTablename()+"\'";
			//查询该表的主键列
			sql3 = "select cu.column_name from user_cons_columns cu, user_constraints au where "+
					" cu.constraint_name = au.constraint_name and "+
					" au.constraint_type = 'P' and "+
					" au.table_name = \'"+this.getTablename()+"\'";
		}else if("SQLSERVER".equals(this.getDbtype())){
			sql2 = "SELECT a.name COLUMN_NAME, b.name DATA_TYPE, cast(isnull(g.[value],'') as nvarchar(100)) COMMENTS "+
					"FROM syscolumns a "+
					"left join systypes b on a.xusertype = b.xusertype "+
					"inner join sysobjects d on a.id=d.id and d.xtype='U' and d.name<>'dtproperties' "+
					"left join sys.extended_properties g on a.id=g.major_id and a.colid=g.minor_id "+
					"where d.name=\'"+this.getTablename()+"\'";
			
			sql3 =  "select b.column_name column_name "+
					"from information_schema.table_constraints a "+
					"inner join information_schema.constraint_column_usage b "+
					"on a.constraint_name = b.constraint_name "+
					"where a.constraint_type = 'PRIMARY KEY' and a.table_name = \'"+this.getTablename()+"\'";
			
		}else if("MYSQL".equals(this.getDbtype())){
			sql2 = "select column_name COLUMN_NAME,column_type DATA_TYPE,column_comment COMMENTS "+
					"from information_schema.columns "+
					"where table_schema=\'"+this.getSid().substring(this.getSid().lastIndexOf("/")+1)+"\' and table_name=\'"+this.getTablename()+"\'";
					
			sql3 = "select COLUMN_NAME column_name from INFORMATION_SCHEMA.COLUMNS where table_name=\'"+this.getTablename()+"\' AND COLUMN_KEY=\'PRI\'";
		}		

		try {
			session = this.getRequest().getSession();
			c=(Connection) session.getAttribute("Connection");
			try {
				stmt=c.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY);
			} catch (Exception e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			System.out.println("========获取表字段名、字段类型、字段说明语句为=======>"+sql2);
			System.out.println("========获取表主键列语句为=======>"+sql3);
			try {
				rs = stmt.executeQuery(sql2);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (rs.next()) {
				ColumnsEntity columns2 = new ColumnsEntity();
				columns2.setColumn_name(rs.getString("COLUMN_NAME")); 
				columns2.setData_type(rs.getString("DATA_TYPE")); 
				columns2.setComments(rs.getString("COMMENTS"));
				columnsList.add(columns2);
			}
			try {
				rs2 = stmt.executeQuery(sql3);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			try {
				rs2.last();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			int k = rs2.getRow();
			pk = new String[k];
			rs2.beforeFirst();
			int m = 0;
			try {
				while (rs2.next()) {
					pk[m] = rs2.getString("column_name");
					m++;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			this.getRequest().setAttribute("columnsList", this.getColumnsList());
			this.getRequest().setAttribute("tablename", this.getTablename());
			this.getRequest().setAttribute("tablecomments", this.getTablecomments());
			this.getRequest().setAttribute("pk", this.getPk());
		} catch (Exception e) {
			result = "error";
			setErrorMessage("数据库查询该表的列信息失败，请与管理员联系！");
			return result;
		}			
		return result;
	}
	
	/**
	 * 生成该表到word文件
	 */
	@Action(value = "toWord", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String toWord(){
		String result = SUCCESS;
		JacobWordOperate operate = new JacobWordOperate();
        operate.operate(this.getTablenameE(),this.getTablenameC(),this.getThisweek(),this.getRequest());
        return result;
	}
	
	/**
	 * 生成所有表到word文件
	 */
	@Action(value = "toWordAll", results = { @Result(name = "success", location = "/webpage/pub/jsonpager.jsp") })
	public String toWordAll(){
		String result = SUCCESS;
		JacobWordOperate operate = new JacobWordOperate();
        operate.operate("","","",this.getRequest());
        return result;
	}
	
	public String getTablenameE() {
		return tablenameE;
	}
	
	public void setTablenameE(String tablenameE) {
		this.tablenameE = tablenameE;
	}
	
	public String getTablenameC() {
		return tablenameC;
	}
	
	public void setTablenameC(String tablenameC) {
		this.tablenameC = tablenameC;
	}

	public String[] getPk() {
		return pk;
	}
	
	public void setPk(String[] pk) {
		this.pk = pk;
	}
	
	public Connection getC() {
		return c;
	}
	
	public void setC(Connection c) {
		this.c = c;
	}
	public String getItems() {
		return items;
	}

	public void setItems(String items) {
		this.items = items;
	}

	public String getTablename() {
		return tablename;
	}

	public void setTablename(String tablename) {
		this.tablename = tablename;
	}

	public List getColumnsList() {
		return columnsList;
	}

	public void setColumnsList(List columnsList) {
		this.columnsList = columnsList;
	}

	public ColumnsEntity getColumns() {
		return columns;
	}

	public void setColumns(ColumnsEntity columns) {
		this.columns = columns;
	}

	public String getTablecomments() {
		return tablecomments;
	}

	public void setTablecomments(String tablecomments) {
		this.tablecomments = tablecomments;
	}

	public String getSid() {
		return sid;
	}

	public void setSid(String sid) {
		this.sid = sid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getFunc() {
		return func;
	}

	public void setFunc(String func) {
		this.func = func;
	}

	public String getDbtype() {
		return dbtype;
	}

	public void setDbtype(String dbtype) {
		this.dbtype = dbtype;
	}

	public String getThisweek() {
		return thisweek;
	}

	public void setThisweek(String thisweek) {
		this.thisweek = thisweek;
	}

}
