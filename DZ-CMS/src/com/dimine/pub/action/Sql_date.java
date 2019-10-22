package com.dimine.pub.action;

public class Sql_date {

	private String sqltype = "mysql";
	
	public String dateformat(String field,String type){
		
		String datetype = null;
		
		if(sqltype == "mysql"){
			datetype = "date_format("+field+",'"+type+"')";
		}else if(sqltype == "sql server"){
			if(type=="%Y-%m"){//年-月
				datetype = "convert(varchar(7), "+field+", 120)";
			}else if(type=="%Y-%m-%d"){//年-月-日
				datetype = "convert(varchar(10), "+field+", 120)";
			}else if(type=="%Y-%m-%d %H"){//年-月-日 时
				datetype = "convert(varchar(13), "+field+", 120)";
			}else if(type=="%Y-%m-%d %H:%i"){//年-月-日 时:分
				datetype = "convert(varchar(16), "+field+", 120)";
			}else if(type=="%Y-%m-%d %H:%i:%s"){//年-月-日 时:分:秒
				datetype = "convert(varchar(19), "+field+", 120)";
			}
		}else if(sqltype == "oracle"){
			if(type=="%Y-%m"){//年-月
				datetype = "to_char("+field+",'yyyy-mm')";
			}else if(type=="%Y-%m-%d"){//年-月-日
				datetype = "to_char("+field+",'yyyy-mm-dd')";
			}else if(type=="%Y-%m-%d %H"){//年-月-日 时
				datetype = "to_char("+field+",'yyyy-mm-dd HH24')";
			}else if(type=="%Y-%m-%d %H:%i"){//年-月-日 时:分
				datetype = "to_char("+field+",'yyyy-mm-dd HH24:MI')";
			}else if(type=="%Y-%m-%d %H:%i:%s"){//年-月-日 时:分:秒
				datetype = "to_char("+field+",'yyyy-mm-dd HH24:MI:SS')";
			}
		}
		
		return datetype;
	}
}
