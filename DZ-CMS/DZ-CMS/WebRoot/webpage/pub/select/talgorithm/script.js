//申明一个栈（主要用于回退功能）
var stack=new Array();
var tstack=new Array();
var sNum1='';
var sOpr='';
var bNeedClear=false;	//是否需要清除输入框中已有的内容

//function calc(iNum1, iNum2, sOpr)
//{
//	var iResult='';
//	switch(sOpr)
//	{
//		case '×':
//			iResult=iNum1*iNum2;
//			break;
//		case '+':
//			iResult=iNum1+iNum2;
//			break;
//		case '-':
//			iResult=iNum1-iNum2;
//			break;
//		case '÷':
//			iResult=iNum1/iNum2;
//			break;
//		default:
//			iResult=iNum2;
//	}
//	
//	return iResult;
//}

function doInput()
{
	var oInput=document.getElementById('input1');
	var talgorithm=document.getElementById('talgorithm');
	var sHtml=this.innerHTML.replace(' ','');
	
	switch(sHtml)
	{
		case '清除':
			oInput.value='';
			talgorithm.value='';
			stack.length=0;
			tstack.length=0;
			break;
		case '回退':
			stack.pop();
			tstack.pop();
			for(var i=0;i<stack.length;i++){
				sNum1+=stack[i];
			}
			for(var j=0;j<tstack.length;j++){
				sOpr+=tstack[j];
			}
			oInput.value=sOpr;
			talgorithm.value=sNum1;
			sNum1="";
			sOpr="";
			break;
		default:	//数字
			oInput.value=oInput.value+sHtml;			
			tstack.push(sHtml);
		
			if(sHtml=='+'||sHtml=='-'||sHtml=='*'||sHtml=='/'||sHtml=='('||sHtml==')'||sHtml=='0'||sHtml=='1'||sHtml=='2'||sHtml=='3'||sHtml=='4'||sHtml=='5'||sHtml=='6'||sHtml=='7'||sHtml=='8'||sHtml=='9'){
				talgorithm.value=talgorithm.value+sHtml;
				stack.push(sHtml);
			}else{
				talgorithm.value=talgorithm.value+"column_"+this.id;
				stack.push("column_"+this.id);
			}
			break;
	}
}

window.onload=function ()
{
	var aLi=document.getElementsByTagName('li');
	var i=0;
	
	for(i=0;i<aLi.length;i++)
	{
		aLi[i].onmousedown=doInput;
		aLi[i].onmouseover=function ()
		{
			this.className='active';
		};
		
		aLi[i].onmouseout=function ()
		{
			this.className='';
		};
	}
	(function (){
		var oS=document.createElement('script');
			
		//oS.type='text/javascript';
		//oS.src='http://sc.chinaz.com';
			
		document.body.appendChild(oS);
	})();
};