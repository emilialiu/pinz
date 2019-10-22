<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<body>
<div id="dz_commodity_base_info" >
	<form id="dz_commodity_base_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/base/doAddSave.action" method="post" class="form-horizontal">
			<input type="hidden" id="product_category_id" value = "${bean.product_category_id}" name ="product_category_id">
			<input type="hidden" id="id" value = "${bean.id}" name ="id">
			<div id="htmlxx">
			</div>

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/base/info.js"></script>
<script type="text/javascript">
	//var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	var jsonStr = "<%=request.getAttribute("jsonStr")%>";
	var beanStr = <%=request.getAttribute("beanStr")%>;
	var result = eval(beanStr);
	initvalid("dz_commodity_base");
	$(function(){
		$("#htmlxx").html(jsonStr);
		if(actiontype=='view'){
			parent.setDialogButtons("dz_commodity_base",{"取消": function() {parent.closewin("dz_commodity_base");}});
		}else {
			parent.setDialogButtons("dz_commodity_base",{"取消": function() {parent.closewin("dz_commodity_base");},"确定": function() {parent.window.frames["dz_commodity_base_frame"].onSave();}});
		}
		 if(actiontype=='addsave'){
		 	addSave("dz_commodity_base",s);
		 }else if(actiontype=='modifysave'){
		 	var codelist = result.codelist;
		 		$("#blue4").attr("checked","checked");
		 	for(var i=0;i<codelist.length;i++){
		 		$("#"+codelist[i]).attr("checked","checked");
		 	}
		}
		
	});

	function onSave() {
		var list1 = result.list1;
		var s = "{";
		s+="\"product_category_id\":\""+$("#product_category_id").val()+"\",\"data\":["
		for ( var i = 0; i < list1.length; i++) {
			s+="{"
			s+="\"name\":\""+list1[i].sku_name+"\",\"code\":\""+list1[i].sku_code+"\",\"site\":[";
			var list2 = list1[i].list1;
			 for ( var j = 0; j < list2.length; j++) {
				  var obj = document.getElementsByName(list2[j].limit_ids); //选择所有name=""test""的对象，返回数组    
				//取到对象数组后，我们来循环检测它是不是被选中    
				for ( var k = 0; k < obj.length; k++) {
					if (obj[k].checked){
						s+="{\"name\":\""+list2[j].sku_name+"\",\"code\":\""+obj[k].value+"\"}"
						if(k!=obj.length-1){
							s+=","
						}
					}
						 
				}  
			}
			s+="]"
			s+="}"
			if(i!=list1.length-1){
				s+=","
			}
		} 
		s += "]}";
		 if(actiontype=='addsave'){
		 	addSave("dz_commodity_base",s);
		 }else if(actiontype=='modifysave'){
		 	
			modifySave("dz_commodity_base",s);
		}
			

	}
	//新增保存
function addSave(_bizname,jsonstr) {
	//检查不成功，跳出
	if(!$("#"+_bizname+"_form").Validform().checkForm()) return;
    var consoleDlg = $("#"+_bizname+"_info");
	//新增保存开始
    consoleDlg.trigger('domi.addsave.before');
    //提取表单参数
    var params = getInfoData(_bizname);

    $.ajax( {
        url :  rootpath+"/webpage/biz/commodity/base/insertjson.action",
        type:'POST',
        data : {"bean.id":$("#id").val(),"bean.sku_json":jsonstr},
        cache : false,
        success : function(data) {
        	var dataJson = eval(data);
            if(dataJson.success) {
                gridrowdata = initGridRowData(_bizname,data);
                parent.alertMsg("配置成功!");
                parent.closewin(_bizname);
            	//新增保存完成
                consoleDlg.trigger('domi.addsave.after');
            } else {
            	parent.alertErrorMsg("配置失败:"+dataJson.errorMessage);
                consoleDlg.trigger('domi.addsave.failue');
            }
        },
        error : function(data) {
        	parent.alertErrorMsg("系统ajax交互错误");
        }
    });
}
function modifySave(_bizname,jsonstr) {
	//检查不成功，跳出

    var consoleDlg = $("#"+_bizname+"_info");
	//修改保存开始
    consoleDlg.trigger('domi.modifysave.before');
    //提取表单参数

    $.ajax( {
        url : rootpath+"/webpage/biz/commodity/base/updatejson.action",
        type:'POST',
        data : {"bean.id":$("#id").val(),"bean.sku_json":jsonstr},
        cache : false,
        error : function(data) {
        	parent.alertErrorMsg("系统ajax交互错误 ");
        },
        success : function(data) {
        	var dataJson = eval(data);
            if(dataJson.success) {
                gridrowdata = initGridRowData(_bizname,data);
                parent.alertMsg("信息修改成功!");
                parent.closewin(_bizname);
            	//修改保存完成
                consoleDlg.trigger('domi.modifysave.after');
            } else {
            	parent.alertErrorMsg("修改失败:"+dataJson.errorMessage);
                consoleDlg.trigger('domi.modifysave.failue');
            }
        }
    });
}

</script>
</html>