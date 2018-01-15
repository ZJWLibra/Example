<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
	<title>My JSP 'index.jsp' starting page</title>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
</head>
<body class="easyui-layout">
	<div data-options="region:'north'" style="height: 60px;">
		<!-- <div id="logo"><img src="images/girl.jpg" /></div> -->
		<div id="wel_msg">欢迎您：test@126.com&nbsp;&nbsp;
			<a href="javascript:void(0);" onclick="alert('退出成功');">安全退出</a>
		</div>
		<p class="clear"></p>
	</div>
	
	<div data-options="region:'south',split:true" style="height: 50px;">Copyright&copy;ZJW</div>
	<div data-options="region:'west',split:true" title="主菜单" style="width: 250px;">
		<div class="easyui-accordion">
			<div title="部门管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="toDepartmentIndex"
						class="site-navi-tab">所有部门</a></li>
				</ul>
			</div>
			<div title="员工管理" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="toEmployeeIndex"
						   class="site-navi-tab">所有员工</a></li>
				</ul>
			</div>
			<div title="报表统计" data-options="iconCls:'icon-ok'" style="padding: 10px;">
				<ul>
					<li><a href="javascript:void(0);" src="toColumnarIndex"
						   class="site-navi-tab">柱状图统计</a></li>
				</ul>
				<ul>
					<li><a href="javascript:void(0);" src="toPieIndex"
						   class="site-navi-tab">饼图统计</a></li>
				</ul>
			</div>
		</div>
	</div>
	<div id="tabs" class="easyui-tabs" data-options="region:'center'">
			<div title="主页" style="padding: 10px">
				<div class="easyui-panel my-panel" title="系统状态">
					系统状态
				</div>
				<div class="easyui-panel my-panel" title="待办任务">
					待办任务
				</div>
			</div>
		</div>
		
		<div id="mm" class="easyui-menu">
		    <div id="mm-tabupdate">刷新</div>
		    <div class="menu-sep"></div>
		    <div id="mm-tabclose">关闭</div>
		    <div id="mm-tabcloseother">关闭其他</div>
		    <div id="mm-tabcloseall">关闭全部</div>
		</div>
		
</body>
</html>
