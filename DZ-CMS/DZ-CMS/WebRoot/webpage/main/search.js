
var substringMatcher;
var searchvalue;//要查询的值

$(function(){
	$("#text_search").on("keyup",function(e){
		$("#textsearch").hide();
		$("#menusearch").show();
		$("#menu_search").val($("#text_search").val());
		$("#menu_search").focus();
		onesearch();
	});
	
	$("#text_btn_search, #menu_btn_search").click(function(){
		search();
	});
	substringMatcher = function(strs) {
		return function findMatches(q, cb) {
			var matches, substringRegex;
			$.ajax({
				url: rootpath+'/main/home/doFuncSearch.action',
				cache: false,
				async: false,
				data: {"query":q},
				type : "get", 
				success: function(data){
					var dataJson = eval(data);
					strs = eval(dataJson.cells[0].navigatinfo);
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
		search(datum.value);
	}).on('typeahead:autocompleted', function (e, datum) {
		search(datum.value);
	});

});
function onesearch(){
	search();
	$("#text_search").unbind("keyup");
	$("#menu_search").on("keyup change blur",function(){
		search();
	});
}
function search(){
	searchvalue = $("#menu_search").val();
	if(searchvalue == null || searchvalue == '')
		return;
	$.ajax({
		url: rootpath+'/main/home/doFuncSearch.action',
		cache: false,
		async: false, 
		data: {"query":searchvalue},
		type: "get", 
		success: function(data){
			var dataJson = eval(data);
			var strs = eval(dataJson.cells[0].navigatinfo);
			var htmlcount = "";
			var htmllist = "";
			var count = strs.length;
			if(count>0){
				htmlcount = "<i class='ace-icon fa fa-rss orange'></i>迪迈为您找到相关结果"+count+"个";
			}else{
				htmlcount = "<i class='ace-icon fa fa-rss orange'></i>很抱歉，没有找到与“<span style='color:red;'>"+searchvalue+"</span>”相关的功能。";
			}

			$.each(strs, function(i, str) {
				if(str.funcicon==''){
					str.funcicon = "<i class='pull-left thumbicon fa fa-key btn-info no-hover'></i>";
				}
				htmllist += "<a href='"+rootpath+"/main/adminindex.jsp#dimine"+str.url+"'><div class='profile-activity clearfix'><div>" + str.funcicon + str.name + "<div class='time'>" + str.memo + "</div></div></div></a>";
			});

			$("#resultscount").html(htmlcount);
			$("#profile-feed-1").html(htmllist);
			$(".profile-activity i").addClass("pull-left thumbicon btn-info");
			$('#profile-feed-1 div').highlight(searchvalue);//高亮显示
//			$('#profile-feed-1').ace_scroll({
//				height: '500px',
//				mouseWheelLock: true,
//				alwaysVisible : true
//			});
		},
		error: function(data) {
			
		}
	});
}

