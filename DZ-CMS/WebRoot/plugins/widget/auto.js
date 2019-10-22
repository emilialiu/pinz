
function Change(controlid,table,text,parameter,width,maxsize){
	var controlval = document.getElementById(""+controlid+"").value;
	$.ajax({
		url: rootpath+'/dm/autocomplete/auto.action',
		cache:false,
		data: { 'auto.tablename':table,'auto.autotext':text,'auto.parameter':parameter,'auto.controlval':controlval,'auto.maxsize':maxsize },//键值对.传递的参数,如果是多个参数，写法就是这样[{ name : 'bean.id', value : ll[0].id }]
		success: function(data){
			var dataJson = eval(data);
			if(dataJson.success){
				var items = dataJson.cells[0];
				$('#'+controlid+'').AutoComplete({
			                'data': items,
			                'width':eval(width),
			                'listStyle': 'custom',
			                'createItemHandler': function(index, data){
			                    var div = $("<div></div>").append(data.label);
			                    return div;
			                }
				}).AutoComplete('show');
			}else{
				parent.alertError("请确认自动完成控件参数值");
			}
		},
		error: function(data) {
			
		}
	});
}
