$(function(){
	//设置业务表单的值
	$.ajax( {  
        url :rootpath+'/manager/activity/feeborrow/doModify.action', 
        data :{'bean.borrowid':bussinesskey},  
        cache : false,  
        error : function(data) {  
        	alertMsg("系统ajax交互错误");  
        },  
        success : function(data) {  
            // 设置表单数据 
        	setInfoData('act_feeborrow_buiness',data);
        }  
    });  
	
});

