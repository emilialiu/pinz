
// 基于准备好的dom，初始化echarts图表
var productdayrpt_gcrb_myChart = echarts.init(document.getElementById('productdayrpt_gcrb_picture'));

var productdayrpt_gcrb_option = {
		title : {
			text: '',
			x:'center'
		},
		tooltip : {
			trigger: 'axis',
		},
	    calculable : true,
	    legend: {
	        data:[],
	        x:'right'	
	    },
	    toolbox: {
			show : true,
			orient : 'vertical',
			y:'center',
			feature : {
				mark : {show: true},
				dataView : {show: true, readOnly: false},
				magicType : {show: true, type: ['line',  'bar']},
				restore : {show: true},
				saveAsImage : {show: true}
			}
		},
	    xAxis : [
	        {
	            type : 'category',
	            data : [],
	        }
	    ],
	    yAxis : [
	        {
	            type : 'value',
	            name : '',
	            axisLabel : {
	                formatter: '{value} '
	            }
	        },
	        {
	            type : 'value',
	            name : '',
	            axisLabel : {
	                formatter: '{value}%'
	            }
	        }
	    ],
	    dataZoom : {
            show : true,  
            realtime : true,  
            start : 0,  
            end : 100  
        },
	    series : [

	        {
	            name:home_planned,
	            type:'line',
	            data:[]
	        },
	        {
	            name:home_completion,
	            type:'line',
	            data:[]
	        },
	        {
	            name:home_completionRate,
	            type:'line',
	            data:[]
	        }
	    ]
	};
			                    
function productScrbLoadCharts(techid,procid,targetid,pdate,bizname){
	var realurl;
	var realdata;
	if(bizname=='productdayrpt'){
		realurl=rootpath+"/webpage/biz/pd/pdrptdetail/productScrbLoadCharts.action";
		realdata={"bean.techid":techid,"bean.procid":procid,"bean.targetid":targetid,"bean.pdate":pdate}; 
	}else if(bizname=='productweekrpt'){
		realurl=rootpath+"/webpage/biz/pd/pwrptdetail/productScrbLoadCharts.action";
		realdata={"bean.techid":techid,"bean.procid":procid,"bean.targetid":targetid,"bean.yyvalue":pdate}; 
	}
	$.ajax({
		url : realurl,
		type:'POST',
		data : realdata,  
		cache : false,
		error : function(data) {
			alertMsg("系统ajax交互错误");
		},
		success : function(data) {
			productdayrpt_gcrb_myChart.clear();
			var result = eval(data);
			if (result) {
				//将返回的category和series对象赋值给options对象内的category和series
				//因为xAxis是一个数组 这里需要是xAxis[i]的形式
				productdayrpt_gcrb_option.xAxis[0].data = result.category;
				productdayrpt_gcrb_option.series = result.series;
				productdayrpt_gcrb_option.legend.data = result.legend;
				productdayrpt_gcrb_option.title.text =proctname+'（'+targetname+'）    '+pd_drugbill_unit+' : '+unitname;
				for(var i=0;i<productdayrpt_gcrb_option.series.length;i++){
					productdayrpt_gcrb_option.series[i].itemStyle = {normal: {label:{show:true}}};
				}
				productdayrpt_gcrb_myChart.hideLoading();
				productdayrpt_gcrb_myChart.setOption(productdayrpt_gcrb_option);
				//window.onresize = productdayrpt_gcrb_myChart.resize;//自适应屏幕宽度
			}
		}
	});
}


