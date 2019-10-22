$(function() {//加载时执行
	//获取导入模版定义信息
	$.ajax({
		url: rootpath+"/manager/sys/modelimport/main.action",
		cache: false,
		async: false,
		type : "POST",
		success: function(data){
			var dataJson = eval(data);
			$("#modelidT")[0].innerHTML = dataJson.modelinfoList;
		},
		error: function(data) {
			alert("导入失败，请与管理员联系！");
		}
	});
});

//导入模版文件信息
function doImport(){
	//开始导入文件
	var modelid = $("#modelid").val(); //ID    
	var fileName = $("#filen").val(); //获取文件名称
    var suffix = fileName.split('.')[1];//文件后缀
    var fileObj = document.getElementById("filen").files[0]; //获取文件对象
    var FileController = rootpath+"/manager/sys/modelimport/doImport.action";//接收上传文件的后台地址 
    // FormData 对象
    var form = new FormData();
    form.append("modelid", modelid);//ID
    form.append("file", fileObj);//文件对象
    form.append("suffix", suffix);//文件后缀
    // XMLHttpRequest 对象
    var xhr = new XMLHttpRequest();
    xhr.open("post", FileController, true);
    xhr.send(form);
    alert('导入成功!');
}