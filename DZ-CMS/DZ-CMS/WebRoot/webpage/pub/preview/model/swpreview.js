$(function(){
	Init();
});
function Init(){
	 DimineWindow.TopBackColor = 0xFF0000 ;//蓝色
	 DimineWindow.BottomBackColor = 0xFFFFFF;//白色
	 DimineWindow.HttpLoadFile(host,port,rootpath+'/attachment/upfile'+fileurl);
}

//旋转
function orbit(){
	DimineWindow.Orbit();
}
//平移
function pan(){
	DimineWindow_tb.Pan();
}
//复位视图
function zoomToExtents(){
	DimineWindow.ZoomToExtents();
	DimineWindow_bz.ZoomToExtents();
}
//从上往下看
function xZPlane(){
	DimineWindow.XZPlane();
}
//从前向后看
function yxPlane(){
	DimineWindow.YxPlane();
}
//从右向左看
function yzPlane(){
	DimineWindow.YzPlane();
}
//选择
function select(){
	DimineWindow.Select();
}