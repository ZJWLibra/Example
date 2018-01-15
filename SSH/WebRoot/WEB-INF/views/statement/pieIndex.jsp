<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<title>饼图统计</title>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript">
		$(function() {
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById("main"));

	        option = {
        	    title : {
        	        text: '各部门员工人数',
        	        subtext: '人数绝对属实',
        	        x:'center'
        	    },
        	    tooltip : {
        	        trigger: 'item',
        	        formatter: "{a} <br/>{b} : {c} ({d}%)"
        	    },
        	    legend: {
        	        orient: 'vertical',
        	        left: 'left',
        	        data: []
        	    },
        	    series : [
        	        {
        	            name: '人数',
        	            type: 'pie',
        	            radius : '55%',
        	            center: ['50%', '60%'],
        	            data:[],
        	            itemStyle: {
        	                emphasis: {
        	                    shadowBlur: 10,
        	                    shadowOffsetX: 0,
        	                    shadowColor: 'rgba(0, 0, 0, 0.5)'
        	                }
        	            }
        	        }
        	    ]
        	};


	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
	        
	        $.ajax({
	        	url : "statement/departmentPie",
	        	type : "POST",
	        	dataType : "JSON",
	        	success : function(data) {
	        		myChart.setOption({
	        			legend: {
	            	        data: data.title
	            	    },
	            	    series : [
	            	        {
	            	            data:data.pieData,
	            	        }
	            	    ]
	        		});
	        	}
	        });
		});
    </script>
</head>
<body>
	<div id="main" style="width: 100%;height:400px;"></div>
</body>
</html>
