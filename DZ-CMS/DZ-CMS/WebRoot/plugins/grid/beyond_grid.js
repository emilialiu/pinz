/**
 * FILENAME:     beyond_gird 1.1.1
 **/

/**
 *修改旅历
 * 1，修正初始化函数名，将beyond_gird()修改为beyond_grid()
 *	修改时间 2008-8-27
 *
 * 2，改进comBox功能的支持，将原来使用comBox节点初始数据更新为选定option的value
 *	修改时间 2008-8-27
 *
 * 3，添加选择单行支持
 *	修改时间 2008-08-29
 *
 * 4，添加指定行不可编辑功能，指定行的checkbox始终是不可选定中状态
 *	修改日期 2008-09-14
 *
 * 5, 修正设置单行选择模式时，允许添加新行时产生的bug
 *	修改日期 2008-09-14
 *
 * 6，修正不可编辑功能时全选按钮产生的bug。
 *	修改日期 2008-09-14
 *
 * 7, 修正设置单行选择模式时，允许全选时产生的bug
 *	修改日期 2008-09-14
 *
 * 8, 添加hidden支持
 *	修改日期 2008-09-14
 *
 * 9, 添加editcode支持
 *	修改日期 2008-9-19
 *
 * 10, 修正hidden产生的th标记在行数为0时产生的BUG
 *	修改日期 2008-9-20
 *
 * 11,第11次修改结束，添加取得指定列的值
 *	修改日期 2008-9-20
 *
 * 12,第12次修改，对combox加入锁定功能
 *	修改日期 2008-10-2
 *
 * 13,第13次修改,修正无选择时getSelectedColValue产生的BUG
 *	修改日期 2008-10-2
 *
 * 14,第14次修改,修正日期控件可手动输入的BUG
 *	修改日期 2008-11-10
 **/


/*第1次修改 修改时间2008-8-27 12：40 v1.0.1*/
function beyond_grid()
//第1次修改结束
{
	var CT;//每一列的表现形式，
	var TAB;//table
	var TBD;//tbody
	var ROW;//tr
	var CEL;//cd;
	var CELDIV="##,##";
	var ROWDIV=",,#,,";
	var NULLDATA = "NULLDATA";
	var selectmod="";
	var TABID ="";
	var textBoxincleIndexs="";
	var maxlengths="";	
	//初始化编辑表，
	//tableId：页面上指定表的Id
	//colTypes:每一列的表现形式；支持"id,checkBox,edit,code,img，comBox，date，hidden,editcode"
	
	this.init = function(tableId,colTypes)
	{
		this.TABID=tableId;
		CT = colTypes.split(",");

		TAB = document.getElementById(tableId);

		TBD = TAB.getElementsByTagName("tbody")[0];

		ROW = TBD.getElementsByTagName("tr");
		
		//第10次修改，修正hidden产生的th标记在行数为0时产生的BUG
		for(var i=0;i<CT.length;i++)
		{
		if(CT[i]=="hidden")
		TAB.getElementsByTagName("thead")[0].getElementsByTagName("th")[i].style.display ='none';
		}
		//第10次修改结束

		for(var i=0;i<ROW.length;i++)
		{
			CEL = ROW[i].getElementsByTagName("td");

			if(CEL.length!=CT.length)
			{
				alert("参数错误:colTypes 与列数长度不等，设置列的类型失败！");
				return false;
			}
			for(var j = 0;j<CEL.length;j++)
			{
				if(CT[j]=="id")
				{
					var celHtml = CEL[j].innerHTML;
					var isdisable = "";
					var htmlArray = celHtml.split(",");
					//第4次修改，修改日期 2008-09-14 添加指定行不可选功能
					if(htmlArray.length==2&&htmlArray[1]=="false")
					{
						if(this.selectmod=="single")
						{
						CEL[j].innerHTML = "<input type ='checkBox' disabled='disabled' class = 'selectone' name = 'selectone' id='"+ CEL[j].innerHTML+"' onclick='selectOneNode(this,\""+this.TABID+"\");' />";
						}else
						{
						CEL[j].innerHTML = "<input type ='checkBox' disabled='disable' class = 'selectone' name = 'selectone' id='"+ CEL[j].innerHTML+"' />";
						}
					}
					else
					{
						if(this.selectmod=="single")
						{
						CEL[j].innerHTML = "<input type ='checkBox' class = 'selectone' name = 'selectone' id='"+ CEL[j].innerHTML+"' onclick='selectOneNode(this,\""+this.TABID+"\");' />";
						}else
						{
						CEL[j].innerHTML = "<input type ='checkBox' class = 'selectone' name = 'selectone' id='"+ CEL[j].innerHTML+"' />";
						}
					}
					//第4次修改结束
				}
				
				
				else if(CT[j]=="checkBox")
				{
					if( CEL[j].innerHTML=="1"||CEL[j].innerHTML=="true")
					{
						CEL[j].innerHTML = "<input type ='checkBox' checked />";
					}
					else
					{
						CEL[j].innerHTML = "<input type ='checkBox' />";
					}
				}

				else if(CT[j]=="edit")
				{
				CEL[j].innerHTML = "<input type ='text'  class='editer' value='"+CEL[j].innerHTML+"' />";
				}
				else if(CT[j]=="area")
				{var tempStr =	CEL[j].innerHTML;			
				 while (tempStr.indexOf('#**#')!=-1){					
					tempStr= tempStr.replace('#**#','\r\n')
				 }
				CEL[j].innerHTML = "<textarea cols='' rows='' class='editer'>"+tempStr+"</textarea>";
				}
				else if(CT[j]=="code")
				{
				CEL[j].innerHTML= CEL[j].innerHTML;
				}
				//===============================================
				else if(CT[j]=="img")
				{
				CEL[j].innerHTML= "<div onMouseOver='showImg(this.nextSibling);' onMouseOut='hideImg(this.nextSibling)' style='cursor:pointer;' onclick='changeImg(this.nextSibling)'>预览图片</div><div style='display:none;position:absolute;'><img src='"+ CEL[j].innerHTML+"'></img></div>";
				//===============================================
				}
				else if(CT[j].length>6&&CT[j].substring(0,6)=="comBox")
				{
					var optionValue=CEL[j].innerHTML;
					CEL[j].innerHTML=(document.getElementById(CT[j].substring(6)).innerHTML);
					
					//第12次修改，对combox加入锁定功能
					var optionValueArray=optionValue.split(",");
					if(optionValueArray.length>1&&optionValueArray[1]=="false")
					{
							optionValue=optionValueArray[0];
							//CEL[j].getElementsByTagName("select")[0].disabled=true;
							var options = CEL[j].getElementsByTagName("option");
					
							for(var optionsIndex = 0; optionsIndex < options.length;optionsIndex++)
							{							
							if(options[optionsIndex].value==optionValue)
							{
								CEL[j].innerHTML=options[optionsIndex].innerHTML+"<span style='display:none'>"+optionValue+"</span>";							
								break;
							
							}
							else if(optionsIndex==options.length-1)
							{
								alert("第"+j+"列设置combox时发生错误，在给定的comBox中没有找到与表格数据相同的value");
							}		
						}
					}
					else{
						var options = CEL[j].getElementsByTagName("option");
					
						for(var optionsIndex = 0; optionsIndex < options.length;optionsIndex++)
						{							
							if(options[optionsIndex].value==optionValue)
							{
								options[optionsIndex].selected = true;							
								break;
							
							}
							else if(optionsIndex==options.length-1)
							{
								alert("第"+j+"列设置combox时发生错误，在给定的comBox中没有找到与表格数据相同的value");
							}		
						}
					}
					//第12次修改结束   
					

				/*修改时间为2008-8-27日 13：15 v1.0.1*/
				//CEL[j].innerHTML= CEL[j].innerHTML;
				//下面语句用于生成新行时使用
				//CEL[j].innerHTML=(document.getElementById(CT[j].substring(6)).innerHTML);
				}
				else if(CT[j]=="date")
				{
					CEL[j].innerHTML="<input type='text'readonly='true' class='editer' onClick='WdatePicker()' value='"+CEL[j].innerHTML+"'/>";
				}
				
				//第8次修改：添加hidden功能
				else if(CT[j]=="hidden")
				{	//第10次修改，修正hidden产生的th标记在行数为0时产生的BUG
					//TAB.getElementsByTagName("thead")[0].getElementsByTagName("th")[j].style.display ='none';
					//第10次修改结束
					CEL[j].style.display ='none';
				}
				//第8次修改结束
				//第9次修改：添加editcode功能
				else if(CT[j]=="editcode")
				{
				CEL[j].innerHTML= CEL[j].innerHTML;
				}
				//第9次修改结束
				else
				{
					
					alert("参数错误:colTypes 中存在不支持的格式，设置列的类型失败");
					return false;
				}
			}
		}
		//第14次修改，加入最大长度限制
		this.doSetMaxLength();
	}

	//全选方法
	//参数node：checkbox的节点
	this.selectAll=function(node)
	{
		//第7次修改：修正设置单行选择模式时，允许添加新行时产生的BUG		
		if(this.selectmod=="single")
		{
			alert("在单行模式下不允许全选");
			return;
		}
		var isAll = node.checked;
		
		var rowSelect = new Array();
		var tmpChkBox;
		for(var i=0;i<ROW.length;i++) {
	   		tmpChkBox = ROW[i].getElementsByTagName("input")[0];
			if (typeof tmpChkBox == "object" && tmpChkBox.name == "selectone") {
				rowSelect.push(tmpChkBox);
			}
		}
		//var rowSelect = document.getElementsByName("selectone");

		for(var i = 0;i<rowSelect.length;i++)
		{
		//修正
		if(rowSelect[i].disabled!=true)
		rowSelect[i].checked=isAll;
		}
	}

	//取得所有选中行当ID
	this.getSelectedRowID=function()
	{
		var rowIds="";
		var rowSelect = document.getElementsByName("selectone");
		for(var i = 0;i<rowSelect.length;i++)
		{
			if(rowSelect[i].checked==true)
			{
				rowIds+=rowSelect[i].id;
				rowIds+=ROWDIV;
			}

		}
		//去除最后一个分割符
		return rowIds.substring(0,rowIds.length-ROWDIV.length);
	}

	this.getSelectedRowValue = function()
	{
		var rowValues="";
		//遍历所有行
		for(var i = 0;i<ROW.length;i++)
		{
			//如果是选中行，则提取数据
			if(ROW[i].getElementsByTagName("input")[0].checked)
			{
				//遍历第选定行的每一列
				var eachRow=ROW[i].getElementsByTagName("TD")
				
				for(var j = 0;j<eachRow.length;j++)
				{
					//开始按列类型解析列数据
					var celValue = eachRow[j];
					
					if(CT[j]=="id")
					{ 
						rowValues+=celValue.childNodes[0].id;
					}
					else if(CT[j]=="checkBox")
					{
							rowValues+=celValue.childNodes[0].checked;
					}
					else if(CT[j]=="edit")
					{
						if(celValue.childNodes[0].value=="")
							rowValues+=NULLDATA;
						else
							rowValues+=celValue.childNodes[0].value;
					}
					else if(CT[j]=="area")
					{
						if(celValue.childNodes[0].value=="")
							rowValues+=NULLDATA;
						else
							rowValues+=celValue.childNodes[0].value;
					}
					else if(CT[j]=="code")
					{
						if(celValue.innerHTML=="")
							rowValues+=NULLDATA;
						else
						rowValues+=celValue.innerHTML;
					}
					//第9次修改：添加editcode功能
					else if(CT[j]=="editcode")
					{
						if(celValue.getElementsByTagName("input").length>0)
						{
							if(celValue.childNodes[0].value=="")
								rowValues+=NULLDATA;
							else
								rowValues+=celValue.childNodes[0].value;
						}
						else
						{
							if(celValue.innerHTML=="")
								rowValues+=NULLDATA;
							else
								rowValues+=celValue.innerHTML;
						}
						
					}
					//第9次修改结束
					//第8次修改：加入hidden支持
					else if(CT[j]=="hidden")
					{
						
						if(celValue.innerHTML=="")
							rowValues+=NULLDATA;
						else
						rowValues+=celValue.innerHTML;
					}
					//第8次修改结束
					else if(CT[j]=="img")
					{
							if(celValue.getElementsByTagName("IMG")[0].src=="")
							rowValues+=NULLDATA;
							else
							rowValues+=celValue.getElementsByTagName("IMG")[0].src;
					}
					
					else if(CT[j].length>6&&CT[j].substring(0,6)=="comBox")
					{
						//第12次修改
						if(celValue.getElementsByTagName("select").length==0)
						{
							rowValues+=celValue.getElementsByTagName("span")[0].innerHTML;
						}
						else{
							var options = celValue.getElementsByTagName("select")[0].value;
							rowValues+=options.split(",")[0];
						}
						
						//第12次修改结束
					}
					else if(CT[j]=="date")
					{
						if(celValue.childNodes[0].value=="")
							rowValues+=NULLDATA;
							else
						rowValues+=celValue.childNodes[0].value;
					}
					//单元格解析结束
					if(j<ROW[i].childNodes.length-1)
					rowValues+=CELDIV;
				}
				rowValues+=ROWDIV;

			}
		}
		rowValues=rowValues.substring(0,rowValues.length-ROWDIV.length);
		return rowValues;
	}
	//第11次修改结束，添加取得指定列的值
	this.getSelectedColValue=function(col) 
	{
		
		if(col==null||col.length==0)
		{
			alert("调用getSelectedColValue(col)方法时，参数col不能为空");
			return "";
		}
		var colNo = col.split(",");
		var allRowValue = this.getSelectedRowValue();
		//第13次修改,修正无选择时getSelectedColValue产生的BUG
		if(allRowValue.length==0)
		{
			return "";
		}
		//第13次修改结束
		for(var i=0;i<colNo.length;i++)
		{
			colNo[i] = parseInt(colNo[i]);
			if(isNaN(colNo[i])||colNo[i]<0)
			{
				alert("调用getSelectedColValue(col)方法时，参数col格式错误，指定列的数字必须为正整数");
				return "";
			}
		}
		var returnValue="";
		var eachRowValue = allRowValue.split(ROWDIV);
		
		for(var i = 0 ;i <eachRowValue.length; i++)
		{
			var eachColValue = eachRowValue[i].split(CELDIV);
			for(var j = 0 ;j <colNo.length; j++)
			{
				if(colNo[j]>CT.length-1)
				{
					alert("调用getSelectedColValue(col)方法时，参数col越界，数据表没有"+colNo[j]+"列");
					return "";
				}
				returnValue+=eachColValue[colNo[j]];
				if(j!=colNo.length-1)
				{
					returnValue+=CELDIV;
				}				
			}
			if(i!=eachRowValue.length-1)
				{
					returnValue+=ROWDIV;
				}	
		}
		return returnValue;
	}
	//第11次修改结束
	this.addNew = function()
	{
		//var newRow= ROW[0].cloneNode();
		//var newCels =newRow.getElementsByTagName("td");
		//newCels[0].innerHTML="AAA";
		//第5次修改：修正设置单行选择模式时，允许添加新行时产生的BUG		
		if(this.selectmod=="single")
		{
			alert("在单行模式下不允许添加新行");
			return;
		}
		//第5次修改结束。
		newRow = TBD.insertRow(ROW.length);
		for(var i = 0;i<CT.length;i++)
		{
			var newCel = newRow.insertCell(i);
			if(CT[i]=="id")			
				newCel.innerHTML = "<input type ='checkBox' class = 'selectone' name = 'selectone'id='newElement' />";
			else if(CT[i]=="checkBox")
				newCel.innerHTML = "<input type ='checkBox' />";
			else if(CT[i]=="edit")
			{
				newCel.innerHTML = "<input type ='text' class='editer' />";
			}
			else if(CT[i]=="area")
			{
				newCel.innerHTML = "<textarea cols='' rows='' class='editer'></textarea>";
			}
			else if(CT[i]=="editcode")
				newCel.innerHTML = "<input type ='text'  class='editer' />";
			else if(CT[i].length>6&&CT[i].substring(0,6)=="comBox")
				newCel.innerHTML=(document.getElementById(CT[i].substring(6)).innerHTML);
			else if(CT[i]=="img")
				newCel.innerHTML= "<div onMouseOver='showImg(this.nextSibling);' onMouseOut='hideImg(this.nextSibling)' style='cursor:pointer;' onclick='changeImg(this.nextSibling)'>修改图片</div><div style='display:none;position:absolute;'><img src='noimage.gif'></img></div>";
			else if(CT[i]=="date")
				newCel.innerHTML = "<input type ='text' readonly='true' class='editer' onClick='WdatePicker()' />";
			else if(CT[i]=="code")
				newCel.innerHTML="";
			//第8次修改：加入hidden支持
			else if(CT[i]=="hidden")
			{
			newCel.innerHTML="";
			newCel.style.display ='none';
			}
			//第8次修改结束
		}
		//第14次修改，加入最大长度限制
		this.doSetMaxLength();
	}
	this.deleteSelected = function()
	{
		alert("该方法未完成");
	}
	/*2008-08-29加入的接口，将beyond_grid设置为单选模式*/
	this.setSingleMode =function ()
	{	
		this.selectmod="single";
	}
	/*2008年11月10日 加入的方法，设置输入框的最大长度*/
	this.setMaxLength = function (para1,para2)
	{
		textBoxincleIndexs = para1;
		maxlengths = para2;
	}
	this.doSetMaxLength = function ()
	{
		if(textBoxincleIndexs!=""&&maxlengths!="")
		{
			var tbis = textBoxincleIndexs.split(",");
			var tbmaxL = maxlengths.split(",");
			for(var i = 0 ; i <ROW.length;i++)
			{
				var vtds = ROW[i].getElementsByTagName("TD");
				for(var j = 0 ; j<tbis.length ; j++)
				{
					var inputnode = vtds[parseInt(tbis[j])].getElementsByTagName("input")[0]
					if(inputnode!=null)
					{
						inputnode.setAttribute("maxLength",tbmaxL[j]); 
					}
					
					//vtds[tbis[j]].getElementsByTagName("input")[0].maxLength=tbmaxL[j]; 
				}
				//alert(ROW[i].innerHTML);
			}
		}
	}
	
	
}
/*2008-08-29加入的函数，将beyond_grid设置为单选模式*/
function selectOneNode(node,TABID)
{
	var selects = document.getElementById(TABID).getElementsByTagName("input");
	var nodeid  = node.id;
	for(var index =0; index < selects.length; index++)
	{	
		
		if(selects[index].type=="checkbox"&&selects[index].name=="selectone")
		{
			if(nodeid!=selects[index].id)
			{
				selects[index].checked=false;
			}
		}
	}
}


function changeImg(node)
{
	return false;
	//alert("该方法未完成");
}
function showImg(node)
{
	node.style.display ='block';
}
function hideImg(node)
{
	node.style.display ='none';
}


function checkMaxInput(textareaCheckDoc,maxLen)
{
 if (textareaCheckDoc.value.length > maxLen)
 textareaCheckDoc.value = textareaCheckDoc.value.substring(0, maxLen);
}
