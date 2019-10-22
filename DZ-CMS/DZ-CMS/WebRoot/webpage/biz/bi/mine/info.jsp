<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="t_bi_mine_info">
		<form id="t_bi_mine_form"
			action="<%=request.getContextPath()%>/webpage/biz/bi/mine/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="mineid" name="mineid" value="${bean.mineid}" />
			<input type="hidden" id="orgid" name="orgid" value="${bean.orgid}" />

			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="orgid">组织机构</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="orgname" id="orgname"
							value="${bean.orgname}" datatype="*" nullmsg="组织机构信息必填!"
							readonly="readonly" onclick="selectdept();" class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="mineno">矿山编码</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="mineno" id="mineno"
							value="${bean.mineno}" datatype="*" nullmsg="矿山编码信息必填!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="minename">矿山名称</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="minename" id="minename"
							value="${bean.minename}" datatype="*1-255"
							nullmsg="矿山名称1到255位任意字符!" class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="oremanutype">矿石工业类型</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<dmtag:sourcemanager sourcename="KSLX" name="oremanutype"
							cssClass="form-control" fixValue="${bean.oremanutype}" isnull="false"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="builddate">建矿日期</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="builddate" id="builddate"
								value="${bean.builddate}" class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',maxDate:'#F{$dp.$D(\'startingdate\')}'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="startingdate">投产日期</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<div class="input-group">
							<input type="text" name="startingdate" id="startingdate"
								value="${bean.startingdate}" class="form-control"
								onfocus="WdatePicker({dateFmt:'yyyy-MM-dd',minDate:'#F{$dp.$D(\'builddate\')}'})" /><span
								class="input-group-addon"><i
								class="ace-icon fa fa-calendar"></i> </span>
						</div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="productivepower">生产能力(万吨)</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="productivepower" id="productivepower"
							value="${bean.productivepower}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="siftpower">选矿能力(万吨)</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="siftpower" id="siftpower"
							value="${bean.siftpower}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="transportation">开拓运输方案</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="transportation" id="transportation"
							value="${bean.transportation}" datatype="*0-100"
							nullmsg="开拓运输方案0到100位任意字符!" class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="miningmethod">开采方式</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<dmtag:sourcemanager sourcename="KCFS" name="miningmethod"
							cssClass="form-control" isnull="false"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="country">国家</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<dmtag:sourcemanager sourcename="SYS007" name="country"
							cssClass="form-control" fixValue="${bean.country}" isnull="true" onchange="loadSelect('country','province','SYS008','${bean.province}')"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="province">省份</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<select name="province" id="province" class="form-control" onchange="loadSelect('province','city','SYS009','${bean.city}')"></select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="city">城市</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<select name="city" id="city" class="form-control">
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="address">地址</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="address" id="address"
							value="${bean.address}" datatype="*0-200" nullmsg="地址0到200位任意字符!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="area">面积(km²)</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="area" id="area" value="${bean.area}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="minewidth">宽度（Km）</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="minewidth" id="minewidth"
							value="${bean.minewidth}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="minelength">长度（Km）</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="minelength" id="minelength"
							value="${bean.minelength}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="mineheight">标高（m）</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="mineheight" id="mineheight"
							value="${bean.mineheight}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="eastlognstart">东经起</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="eastlognstart" id="eastlognstart"
							datatype="*0-20" nullmsg="东经起0到20位任意字符!"
							value="${bean.eastlognstart}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="eastlognend">东经止</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="eastlognend" id="eastlognend"
							datatype="*0-20" nullmsg="东经止0到20位任意字符!"
							value="${bean.eastlognend}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="northlatstart">北纬起</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="northlatstart" id="northlatstart"
							datatype="*0-20" nullmsg="北纬起0到20位任意字符!"
							value="${bean.northlatstart}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="northlatend">北纬止</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="northlatend" id="northlatend"
							datatype="*0-20" nullmsg="北纬止0到20位任意字符!"
							value="${bean.northlatend}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="coordinatex">矿山X坐标</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="coordinatex" id="coordinatex"
							value="${bean.coordinatex}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="coordinatey">矿山Y坐标</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="coordinatey" id="coordinatey"
							value="${bean.coordinatey}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="productivestatus">生产状态</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<dmtag:sourcemanager sourcename="SCZT" name="productivestatus"
							cssClass="form-control" fixValue="${bean.productivestatus}" isnull="false"></dmtag:sourcemanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="serviceyear">服务年限</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="serviceyear" id="serviceyear"
							value="${bean.serviceyear}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="strippingratio">剥离比</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="strippingratio" id="strippingratio"
							value="${bean.strippingratio}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="explorationratio">探采比</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="explorationratio" id="explorationratio"
							value="${bean.explorationratio}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="workusercount">从业人员</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="workusercount" id="workusercount"
							value="${bean.workusercount}" datatype="n" nullmsg="从业人员必须是数字!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="techusercount">技术人员</label>
				<div class="col-xs-12 col-sm-4">
					<div class="col-sm-9">
						<!-- class="clearfix" -->
						<input type="text" name="techusercount" id="techusercount"
							value="${bean.techusercount}" datatype="n" nullmsg="技术人员必须是数字!"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-2 no-padding-right"
					for="remark">备注</label>
				<div class="col-xs-12 col-sm-10">
					<div class="col-sm-11">
						<!-- class="clearfix" -->
						<textarea name="remark" id="remark" datatype="*0-500"
							nullmsg="备注0到500位任意字符!" class="col-xs-12">${bean.remark}</textarea>
					</div>
					<div class="col-sm-1">
						<div class="info">
							<span class="Validform_checktip"></span><span class="dec"><s
								class="dec1">&#9670;</s><s class="dec2">&#9670;</s>
							</span>
						</div>
					</div>
				</div>
			</div>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/bi/mine/info.js"></script>
<script type="text/javascript">
	var mineid = '<%=request.getAttribute("bean.mineid")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("t_bi_mine");
		else if(actiontype=='modifysave')
			modifySave("t_bi_mine");
		else if(actiontype=='deletesave')
			deleteSave("t_bi_mine");
	}
	initvalid("t_bi_mine");
	$(function(){
		if(actiontype=='view'){
			parent.setDialogButtons("t_bi_mine",{"取消": function() {parent.closewin("t_bi_mine");}});
		}else{
			parent.setDialogButtons("t_bi_mine",{"取消": function() {parent.closewin("t_bi_mine");},"确定": function() {parent.window.frames["t_bi_mine_frame"].onSave();}});
		}
		if(actiontype!='addsave'){
			loadSelect('country','province','SYS008','<%=request.getAttribute("bean.province")%>');
			loadSelect('province','city','SYS009','<%=request.getAttribute("bean.city")%>');
		}
	});
	//加载下拉列表
	function loadSelect(parentEle,childEle,dicttypeid,selectValue){
		var parentid = $("#"+parentEle).val();
		if(parentid == ""){
			return;
		}
		$.ajax( {  
			url : rootpath+"/pub/select/selectList/limitlist.action", 
			type:'POST',
			data : {'bean.dicttypeid':dicttypeid,'bean.parentid':parentid},  
			cache : false,
			async : false,
			error : function(data) {  
				alertErrorMsg("系统ajax交互错误");
			},
			success : function(data) {
				var data = eval('({'+data+'})');	
				$("#"+childEle)[0].options.length = 0;
				for(i in data){
					var objOption = new Option(data[i],i);
					$("#"+childEle)[0].add(objOption);
				}
				if(selectValue != null && selectValue != ''){
					$("#"+childEle)[0].value = selectValue;
				}
			}  
		 });
	}

	//选择部门
	function selectdept() {
		parent.openwin("selectdept", "/pub/select/dept/selectDept.action", "部门选择", 325, 450);
		parent.PageObject.itemMap['selectdept'].callback = function(no) {
			selectdeptCallBack(no);
		};
	}
	function selectdeptCallBack(arr){
		$('#orgid')[0].value = arr[0].id;
		$('#orgname')[0].value = arr[0].name;
	}
</script>
</html>