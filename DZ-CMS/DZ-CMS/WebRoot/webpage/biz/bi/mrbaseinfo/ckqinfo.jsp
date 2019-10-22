<%@ page language="java" pageEncoding="utf-8"%>
<%@include file="/webpage/pub/biz/headinfo.jsp"%>
<body>
	<div id="t_bi_mrbaseinfo_info">
		<form id="t_bi_mrbaseinfo_form"
			action="<%=request.getContextPath()%>/webpage/biz/bi/mrbaseinfo/doAddSave.action"
			method="post" class="form-horizontal">
			<input type="hidden" id="rightid" name="rightid" value="${bean.rightid}" />
			<input type="hidden" id="mineid" name="mineid" value="${bean.mineid}" />

			<fieldset>
				<legend>矿业权基本信息</legend>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="mineid">组织机构</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="deptname" id="deptname"
								value="${bean.deptname}" readonly="readonly"
								onclick="selectdept();" class="col-xs-12" />
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="mrcategory">矿权类别</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="KQLB" name="mrcategory"
								cssClass="form-control" fixValue="${bean.mrcategory}"
								isnull="false" disabled="true"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="projectname">项目名称</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="projectname" id="projectname"
								value="${bean.projectname}" datatype="*1-100"
								nullmsg="项目名称1到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="projectquality">项目性质</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="XMXZ" name="projectquality"
								cssClass="form-control" fixValue="${bean.projectquality}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="mrstate">矿业权状态</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="KYQZT" name="mrstate"
								cssClass="form-control" fixValue="${bean.mrstate}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="ownway">拥有方式</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="YYFS" name="ownway"
								cssClass="form-control" fixValue="${bean.ownway}" isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="economictype">经济类型</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="JJLX" name="economictype"
								cssClass="form-control" fixValue="${bean.economictype}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="companyname">公司名称</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="companyname" id="companyname"
								value="${bean.companyname}" datatype="*1-100"
								nullmsg="公司名称1到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="companyaddr">公司地址</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="companyaddr" id="companyaddr"
								value="${bean.companyaddr}" datatype="*0-100"
								nullmsg="公司地址0到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="companypost">公司邮编</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="companypost" id="companypost"
								value="${bean.companypost}" datatype="*0-20"
								nullmsg="公司邮编0到20位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="registeredfund">注册资金</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="registeredfund" id="registeredfund"
								value="${bean.registeredfund}" class="col-xs-12" datatype="num4"
								nullmsg="注册资金必须是数字!" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="legalrepresentative">法定代表人</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="legalrepresentative"
								id="legalrepresentative" value="${bean.legalrepresentative}"
								datatype="*0-50" nullmsg="法定代表人0到50位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="accountnum">帐号</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="accountnum" id="accountnum"
								value="${bean.accountnum}" datatype="*0-50"
								nullmsg="帐号0到50位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="bank">开户银行</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="bank" id="bank" value="${bean.bank}"
								datatype="*0-100" nullmsg="开户银行0到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="mrcardid">矿业权资格证号</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="mrcardid" id="mrcardid"
								value="${bean.mrcardid}" datatype="*0-100"
								nullmsg="矿业权资格证号0到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="eaststart">东经起</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="eaststart" id="eaststart"
								value="${bean.eaststart}" datatype="*0-20" nullmsg="东经起0到20位任意字符!"
								class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="eastend">东经止</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="eastend" id="eastend"
								value="${bean.eastend}" datatype="*0-20" nullmsg="东经止0到20位任意字符!"
								class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="northstart">北纬起</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="northstart" id="northstart"
								value="${bean.northstart}" datatype="*0-20" nullmsg="北纬起0到20位任意字符!"
								class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="northend">北纬止</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="northend" id="northend"
								value="${bean.northend}" datatype="*0-20" nullmsg="北纬止0到20位任意字符!"
								class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="geoposition">地理位置</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="geoposition" id="geoposition"
								value="${bean.geoposition}" datatype="*0-100"
								nullmsg="地理位置0到100位任意字符!" class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="mrarea">矿权面积(km²)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="mrarea" id="mrarea"
								value="${bean.mrarea}" datatype="num4" nullmsg="矿权面积(km²)必须是数字!"
								class="col-xs-12" />
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="expstartdate">有效期开始时间</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<div class="input-group">
								<input type="text" name="expstartdate" id="expstartdate"
									value="${bean.expstartdate}" class="form-control"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*"
									nullmsg="有效期开始时间必填!" /><span class="input-group-addon"><i
									class="ace-icon fa fa-calendar"></i> </span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="expenddate">有效期结束时间</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<div class="input-group">
								<input type="text" name="expenddate" id="expenddate"
									value="${bean.expenddate}" class="form-control"
									onfocus="WdatePicker({dateFmt:'yyyy-MM-dd'})" datatype="*"
									nullmsg="有效期结束时间必填!" /><span class="input-group-addon"><i
									class="ace-icon fa fa-calendar"></i> </span>
							</div>
						</div>
						<div class="col-sm-3">
							<div class="info">
								<span class="Validform_checktip"></span><span class="dec"><s
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="dutyman">责任人</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="dutyman" id="dutyman"
								value="${bean.dutyman}" class="col-xs-12" />
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
			</fieldset>
			<fieldset style="margin-top: 10px;">
				<legend>采矿权基本信息</legend>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="cooperateent">合作单位</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="cooperateent" id="cooperateent"
								value="${ckqbean.cooperateent}" datatype="*0-100"
								nullmsg="合作单位0到100位任意字符!" class="col-xs-12" />
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
						for="planscale">设计规模(万吨/年)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="planscale" id="planscale"
								value="${ckqbean.planscale}" datatype="num4"
								nullmsg="投资额必须是数字!" class="col-xs-12" />
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
						for="involume">投资额</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="involume" id="involume"
								value="${ckqbean.involume}" datatype="num4" nullmsg="投资额必须是数字!"
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
						for="arearange">矿区范围</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="arearange" id="arearange"
								value="${ckqbean.arearange}" datatype="*0-100"
								nullmsg="矿区范围0到100位任意字符!" class="col-xs-12" />
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
						for="mrarea">矿权面积(km²)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="mrarea" id="mrarea"
								value="${ckqbean.mrarea}" datatype="num4" nullmsg="矿权面积(km²)必须是数字!"
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
						for="mineraltype">矿种</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="KZ" name="mineraltype"
								cssClass="form-control" fixValue="${ckqbean.mineraltype}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="catype">共伴生类型</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="GBSLX" name="catype"
								cssClass="form-control" fixValue="${ckqbean.catype}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="verifyreserve">探明的地质储量(万吨)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="verifyreserve" id="verifyreserve"
								value="${ckqbean.verifyreserve}" datatype="num4"
								nullmsg="探明的地质储量必须是数字!" class="col-xs-12" />
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
						for="mineway">开采方式</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="KCFS" name="mineway"
								cssClass="form-control" fixValue="${ckqbean.mineway}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="miningmethod">采矿方法</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="CKFF" name="miningmethod"
								cssClass="form-control" fixValue="${ckqbean.miningmethod}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
				<div class="form-group">
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="benemethod">选矿方法</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="XKFF" name="benemethod"
								cssClass="form-control" fixValue="${ckqbean.benemethod}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
					<label class="control-label col-xs-12 col-sm-2 no-padding-right"
						for="depthupper">采深上限(m)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="depthupper" id="depthupper"
								value="${ckqbean.depthupper}" datatype="num4"
								nullmsg="采深上限必须是数字!" class="col-xs-12" />
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
						for="depthlower">采深下限(m)</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<input type="text" name="depthlower" id="depthlower"
								value="${ckqbean.depthlower}" datatype="num4"
								nullmsg="采深下限必须是数字!" class="col-xs-12" />
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
						for="minegetway">采矿权获取方式</label>
					<div class="col-xs-12 col-sm-4">
						<div class="col-sm-9">
							<!-- class="clearfix" -->
							<dmtag:sourcemanager sourcename="CKQHQFS" name="minegetway"
								cssClass="form-control" fixValue="${ckqbean.minegetway}"
								isnull="false"></dmtag:sourcemanager>
						</div>
						<div class="col-sm-3"></div>
					</div>
				</div>
			</fieldset>
			<fieldset style="margin-top: 10px;">
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
									class="dec1">&#9670;</s><s class="dec2">&#9670;</s> </span>
							</div>
						</div>
					</div>
				</div>
			</fieldset>

		</form>
	</div>
</body>
<%@include file="/webpage/pub/biz/footerinfo.jsp"%>
<script type="text/javascript"
	src="<%=request.getContextPath()%>/webpage/biz/bi/mrbaseinfo/ckqinfo.js"></script>
<script type="text/javascript">
var rightid = '<%=request.getAttribute("bean.rightid")%>';
var actiontype = '<%=request.getAttribute("actiontype")%>';
function onSave() {
	if(actiontype=='addsave')
		addSave("t_bi_mrbaseinfo");
	else if(actiontype=='modifysave')
		modifySave("t_bi_mrbaseinfo");
	else if(actiontype=='deletesave')
		deleteSave("t_bi_mrbaseinfo");
}
initvalid("t_bi_mrbaseinfo");
$(function(){
	if(actiontype=='view')
		parent.setDialogButtons("t_bi_mrbaseinfo",{"取消": function() {parent.closewin("t_bi_mrbaseinfo");}});
	else 
		parent.setDialogButtons("t_bi_mrbaseinfo",{"取消": function() {parent.closewin("t_bi_mrbaseinfo");},"确定": function() {parent.window.frames["t_bi_mrbaseinfo_frame"].onSave();}});
});

//选择部门
function selectdept() {
	parent.openwin("selectdept", "/pub/select/dept/selectDept.action", "部门选择", 325, 450);
	parent.PageObject.itemMap['selectdept'].callback = function(no) {
		selectdeptCallBack(no);
	};
}
function selectdeptCallBack(arr){
	$('#mineid')[0].value = arr[0].id;
	$('#deptname')[0].value = arr[0].name;
}
</script>
</html>