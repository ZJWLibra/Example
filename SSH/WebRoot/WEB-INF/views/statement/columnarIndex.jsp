<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<title>柱状图统计</title>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
	<script type="text/javascript" src="js/echarts.js"></script>
	<script type="text/javascript">
		$(function() {
			// 基于准备好的dom，初始化echarts实例
	        var myChart = echarts.init(document.getElementById("main"));

	        // 指定图表的配置项和数据
	        var option = {
	            title: {
	                text: '各部门员工人数'
	            },
	            tooltip: {},
	            legend: {
	                data:['人数']
	            },
	            xAxis: {
	                data: []
	            },
	            yAxis: {},
	            series: [{
	                name: '人数',
	                type: 'bar',
	                data: []
	            } ]
	        };

	        // 使用刚指定的配置项和数据显示图表。
	        myChart.setOption(option);
	        
	        $.ajax({
	        	url : "statement/departmentColumnar",
	        	type : "POST",
	        	dataType : "JSON",
	        	success : function(data) {
	        		myChart.setOption({
	        			xAxis: {
	    	                data: data.title
	    	            },
	    	            series: [{
	    	                data: data.columnarData
	    	            }]
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
