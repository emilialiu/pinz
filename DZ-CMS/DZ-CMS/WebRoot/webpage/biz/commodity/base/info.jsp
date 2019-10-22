<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp" %>
<link href="<%=request.getContextPath() %>/webpage/common/uploadify/uploadify.css" rel="stylesheet" type="text/css" />
<body>
<div id="dz_commodity_base_info" >
	<form id="dz_commodity_base_form" action="<%=request.getContextPath()%>/webpage/biz/commodity/base/doAddSave.action" method="post" class="form-horizontal">
		<input type="hidden" id="id" name="id" value="${bean.id}"/>
		<input type="hidden" id="banner_img" name="banner_img" value="${bean.banner_img}"/>
		<input type="hidden" id="context_img" name="context_img" value="${bean.context_img}"/>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="title">商品名称</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="title" id="title" value="${bean.title}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="product_category_id">商品分类</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<dmtag:combomanager table="dz_commodity_category"
							cssClass="chosen-select form-control" name="product_category_id"
							combovalue="id" combotext="CONCAT(name,'(',if(sex=0,'男','女'),')')"
							parameter=""
							isnull="false"></dmtag:combomanager>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="code">产品款号</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="code" id="code" value="${bean.code}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="fabric_zhishu">面料支数</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="fabric_zhishu" id="fabric_zhishu"
							value="${bean.fabric_zhishu}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="fabric_thickness">面料厚度</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="fabric_thickness" id="fabric_thickness"
							value="${bean.fabric_thickness}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="fabric_weight">面料克重</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="fabric_weight" id="fabric_weight"
							value="${bean.fabric_weight}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="fabric_elasticity">面料弹力</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="fabric_elasticity" id="fabric_elasticity"
							value="${bean.fabric_elasticity}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="work_season">适用季节</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="work_season" id="work_season"
							value="${bean.work_season}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="fabric_ingredient">面料成分</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="fabric_ingredient" id="fabric_ingredient"
							value="${bean.fabric_ingredient}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="banner_img">banner图</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
					<table>
						<tr id="alfxbgsctr">
							<td><input type="file" name="uploadify" id="uploadify" /></td>
							<td>
								<div class="uploadify" style="height: 30px; width: 120px;cursor:pointer;">
									<div class="uploadify-button" style="height: 30px; line-height: 30px; width: 120px;">
										<span class="uploadify-button-text" onclick="javascript:jQuery('#uploadify').uploadify('upload','*');">开始上传</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="ckyydeclarename"></span>
							</td>
						</tr>
					</table>
					<div id="uploadify-queue"></div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="context_img">产品描述图片</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<table>
						<tr id="alfxbgsctr">
							<td><input type="file" name="uploadify2" id="uploadify2" /></td>
							<td>
								<div class="uploadify" style="height: 30px; width: 120px;cursor:pointer;">
									<div class="uploadify-button" style="height: 30px; line-height: 30px; width: 120px;">
										<span class="uploadify-button-text" onclick="javascript:jQuery('#uploadify2').uploadify('upload','*');">开始上传</span>
									</div>
								</div>
							</td>
						</tr>
						<tr>
							<td colspan="2">
								<span id="ckyydeclarename2"></span>
							</td>
						</tr>
					</table>
					<div id="uploadify-queue"></div>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="original_price">原价</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="original_price" id="original_price"
							value="${bean.original_price}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="earnest_money">定金</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="earnest_money" id="earnest_money"
							value="${bean.earnest_money}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="seckill_price">秒杀价</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="seckill_price" id="seckill_price"
							value="${bean.seckill_price}" class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="is_hot_money">是否热品</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="is_hot_money"  class="col-xs-12" >
							<option  value="0">是</option>
							<option value="1">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="is_new_product">是否新品</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="is_new_product"  class="col-xs-12" >
							<option  value="0">是</option>
							<option value="1">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="is_recommend">是否推荐</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<select id="is_recommend"  class="col-xs-12" >
							<option  value="0">是</option>
							<option value="1">否</option>
						</select>
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="sales">销量</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="sales" id="sales" value="${bean.sales}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>
			<div class="form-group">
				<label class="control-label col-xs-12 col-sm-3 no-padding-right"
					for="stock">库存</label>
				<div class="col-xs-12 col-sm-9">
					<div class="col-sm-6">
						<!-- class="clearfix" -->
						<input type="text" name="stock" id="stock" value="${bean.stock}"
							class="col-xs-12" />
					</div>
					<div class="col-sm-3"></div>
				</div>
			</div>

		</form>
</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp" %>
<script type="text/javascript" src="<%=request.getContextPath()%>/webpage/biz/commodity/base/info.js"></script>
<script type="text/javascript" src="<%=request.getContextPath() %>/webpage/common/uploadify/jquery.uploadify.min.js"></script>
<script type="text/javascript">
	var id = '<%=request.getAttribute("bean.id")%>';
	var actiontype = '<%=request.getAttribute("actiontype")%>';
	var product_category_id = '<%=request.getAttribute("bean.product_category_id")%>';
	var is_recommend = '<%=request.getAttribute("bean.is_recommend")%>';
	var is_new_product = '<%=request.getAttribute("bean.is_new_product")%>';
	var is_hot_money = '<%=request.getAttribute("bean.is_hot_money")%>';
	function onSave() {
		if(actiontype=='addsave')
			addSave("dz_commodity_base");
		else if(actiontype=='modifysave')
			modifySave("dz_commodity_base");
		else if(actiontype=='deletesave')
			deleteSave("dz_commodity_base");
	}
	initvalid("dz_commodity_base");
	$(function(){
		$("#product_category_id  option[value='"+product_category_id+"']").attr("selected","selected");
		$("#is_recommend  option[value='"+is_recommend+"']").attr("selected","selected");
		$("#is_new_product  option[value='"+is_new_product+"']").attr("selected","selected");
		$("#is_hot_money  option[value='"+is_hot_money+"']").attr("selected","selected");
		if(actiontype=='view')
			parent.setDialogButtons("dz_commodity_base",{"取消": function() {parent.closewin("dz_commodity_base");}});
		else 
			parent.setDialogButtons("dz_commodity_base",{"取消": function() {parent.closewin("dz_commodity_base");},"确定": function() {parent.window.frames["dz_commodity_base_frame"].onSave();}});
	});
		initUploadify();
	function initUploadify(){
		$("#uploadify").uploadify({
			'swf' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify.swf',
			'uploader' : '<%=request.getContextPath()%>/uploadServlet?path=drillrecord',
			'cancelImg' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify-cancel.png',
			'folder' : 'uploads',
			'queueID' : 'uploadify-queue',
			'auto' : false,
			'multi' : true,
			'uploadLimit' : 10,
			'simUploadLimit' : 1,
			'buttonText' : '添加附件',
			'queueSizeLimit': 1,
			'fileTypeExts':"*.jpg;*.png;",
			'onUploadStart' : function(file) {
	        },
	        'onUploadSuccess':function(file, data, response){  
				savefile(file.name, data);
	        },  
	        'onUploadComplete':function(){
	        }
		});
	}
	function savefile(filename, filepath){
		$("#banner_img").val(filepath);
	}
		initUploadify2();
	function initUploadify2(){
		$("#uploadify2").uploadify({
			'swf' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify.swf',
			'uploader' : '<%=request.getContextPath()%>/uploadServlet?path=drillrecord',
			'cancelImg' : '<%=request.getContextPath()%>/webpage/common/uploadify/uploadify-cancel.png',
			'folder' : 'uploads',
			'queueID' : 'uploadify-queue',
			'auto' : false,
			'multi' : true,
			'uploadLimit' : 10,
			'simUploadLimit' : 1,
			'buttonText' : '添加附件',
			'queueSizeLimit': 1,
			'fileTypeExts':"*.jpg;*.png;",
			'onUploadStart' : function(file) {
	        },
	        'onUploadSuccess':function(file, data, response){  
				savefile2(file.name, data);
	        },  
	        'onUploadComplete':function(){
	        }
		});
	}
	function savefile2(filename, filepath){
		$("#context_img").val(filepath);
	}
</script>
</html>