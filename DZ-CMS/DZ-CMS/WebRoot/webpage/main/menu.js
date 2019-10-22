   //设置首页的名字
	var substringMatcher;
	var strs1;
	//当前选中的funccode
	var curfunccode;

	$(function(){
		substringMatcher = function(strs) {
			return function findMatches(q, cb) {
				var matches, substringRegex;
				$.ajax({
					url: rootpath+'/main/home/donavigation.action',
					cache: false,
					async: false, 
					data: {"query":q},
					type : "get", 
					success: function(data){
						var dataJson = eval(data);
						strs = eval(dataJson.cells[0].navigatinfo);
					    strs1 = strs;
					},
				   error: function(data) {
						
					}
				});
			  	
				// an array that will be populated with substring matches
				matches = [];
			 
				// regex used to determine if a string contains the substring `q`
				substrRegex = new RegExp(q, 'i');
			 
				// iterate through the pool of strings and for any string that
				// contains the substring `q`, add it to the `matches` array
				$.each(strs, function(i, str) {
					//alert(str.name);
					if (substrRegex.test(str.name)) {
						// the typeahead jQuery plugin expects suggestions to a
						// JavaScript object, refer to typeahead docs for more info
						matches.push({ value: str.name });
					}
				});
				
				cb(matches);
			};
		};

		$('input.typeahead').typeahead({
			hint: true,
			highlight: true,
			minLength: 1
		}, {
			name: '',
			displayKey: 'value',
			source: substringMatcher(null)
		});

		$('input.typeahead').on('typeahead:selected' , function(e, datum) {
			changmenu(datum.value);
		}).on('typeahead:autocompleted', function (e, datum) {
			changmenu(datum.value);
		});
	});
/**
 * 读取cookie，生产历史记录的list;
 */
$(function(){
	//加载常访问的历史记录
	//loadhismenu();
});
function changmenu(value){
	for(var i=0;i<strs1.length;i++){
		if(strs1[i].name==value){
			//更新cookie
			collectmenuhis(strs1[i].funccode,strs1[i].url,strs1[i].name);
			location.href='adminindex.jsp#dimine'+strs1[i].url;
			return ;
		}
	}
}
/**
 * 查看历史的菜单
 * @param value
 */
function gotohis(obj){
	//更新cookie,并且设置funccode
	collectmenuhis($(obj).attr("funccode"),$(obj).attr("url"),$(obj).attr("funcname"));
	location.href='adminindex.jsp#dimine'+$(obj).attr("url");
}

/**
 * 记录点击的连接放到cookies里面
 * @param obj
 */
function collectmenu(obj){
	//获取当前连接的funccode
	funccode=$(obj).attr("funccode");
	var url=$(obj).attr("url");
	var funcname=$(obj).attr("funcname");
	//collectmenuhis(funccode,url,funcname);
	if(funccode=='dad1303cf04e4672bf64b8a3b25c5ecd'){
		parent.window.open("http://safe.conch.cn/");
		return;
	}else if(funccode=='4240942172fd417481c658899b7f38a6'){
		parent.window.open("https://ww-komtrax.komatsu.com.cn/kc2/ww/login.aspx");
		return;
	}
	addTabs({id:funccode,title:funcname,close:true,url:url});
}
/**
 * 更新历史记录的cookies，并设置全局的funccode；
 * @param funccode
 * @param url
 * @param funcname
 */
function collectmenuhis(funccode,url,funcname){
	var str=url+","+funcname+","+funccode;
	curfunccode=funccode;
	//首先获取cookie
	cookstr1="";
	var cookestr=$.cookie("menu");
	if(cookestr!=null){
		var cooke=cookestr.split(";");
		if(cooke.length>10){
			cooke = cooke.slice(cooke.length-10,cooke.length);
		}
		for(var i=0;i<cooke.length-1;i++){
			//如果这个链接已经存在,则要删除
			if(cooke[i]!=str){
				cookstr1+=cooke[i]+";";
			}
		}
		//删除cooke
		$.cookie("menu",null);
		cookstr1=cookstr1+str+";";
		$.cookie("menu", cookstr1 , { path: '/', expires: 1 }); 
	}else{
		$.cookie("menu",null);
		$.cookie("menu", str+";" , { path: '/', expires: 1 });
	}
	//更新历史记录
	loadhismenu();
}
//加载常访问的历史记录
function loadhismenu(){
	var cookestr=$.cookie("menu");
	if(cookestr!=null && cookestr!=""){
		cookestr=cookestr.substring(0,cookestr.length-1);
		var cooke=cookestr.split(";");
		var html="";
		for(var i=cooke.length-1;i>=0;i--){
			var st=cooke[i].split(",");
			html+="<li style=\"border:0px;\">";
			html+="<div class=\"alert alert-info no-border\" style=\"line-height:5px; margin-bottom:0px\">";
			html+="	<button type=\"button\" class=\"close\" data-dismiss=\"alert\" url=\""+st[0]+"\"  funcname=\""+st[1]+"\" funccode=\""+st[2]+"\" onclick=clearhis(this)>";
			html+="	<i class=\"ace-icon fa fa-times\"> </i>";
			html+="</button>";
			html+="<strong url=\""+st[0]+"\" funccode=\""+st[2]+"\" funcname=\""+st[1]+"\"  onclick=gotohis(this) style=\"height:1px;font-size:10px;\">"+st[1]+"</strong>";
			html+="</div>";
			html+="</li>";
		}
		$('#hismenuer').html(html);
	}
}
/**
 * 清除cookies里面的值
 * @returns
 */
function clearhis(obj){
	var url=$(obj).attr("url");
	var funcname=$(obj).attr("funcname");
	var funcode=$(obj).attr("funccode");
	var str=url+","+funcname+","+funcode;
	var cookstr1="";
	//首先获取cookie
	var cookestr=$.cookie("menu");
	if(cookestr!=null){
		var cooke=cookestr.split(";");
		for(var i=0;i<cooke.length-1;i++){
			//如果这个链接已经存在,则要删除
			if(cooke[i]!=str){
				cookstr1+=cooke[i]+";";
			}
		}
	}
	$.cookie("menu",null);
	$.cookie("menu", cookstr1 , { path: '/', expires: 1 }); 
}
	