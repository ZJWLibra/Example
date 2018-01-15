<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<title>部门管理</title>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
</head>
<table id="list" class="easyui-datagrid"
		data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'department/list',
			method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'name',width:100">部门名称</th>
			</tr>
		</thead>
	</table>
	
	<div id="tb" style="height: auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openEditWin('editWin', 'list', 'editForm', 'department')">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openClazzWin();">分配员工</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openClazzInfoWin();">查看员工</a>
		<div>
			<form id="searchForm">
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入部门名称',
						required:false,
						novalidate:true" name="department.name"/>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
			</form>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window normal_win" data-options="title:'添加部门', closed:true">
		<form id="addForm">
			<table>
				<tr>
					<td>部门名称</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入部门名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="department.name"/>
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="save('department/insert', 'addForm', 'addWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑部门', closed:true">
		<form id="editForm">
			<input type="hidden" name="department.id" />
			<table>
				<tr>
					<td>姓名</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入部门名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="department.name"/>
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="edit('department/update', 'editForm', 'editWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>

	<div id="clazzWin" class="easyui-window large_win" data-options="title:'选择员工', closed:true">
		<table id="clazzList" class="easyui-datagrid"
			   data-options="
				toolbar:'#clazzTb',
				rownumbers:true,
				border:false,
				singleSelect:false,
				pagination:true,
				pageSize:10">
			<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'title',width:100">名称</th>
			</tr>
			</thead>
		</table>

		<div id="clazzTb" style="height: auto">
			<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="confirmClazz();">确认</a>
			<div>
				<form id="clazzSearchForm">
					<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入员工名称',
							required:false,
							novalidate:true" name="clazz.title"/>
					<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('clazzList', 'clazzSearchForm');">搜索</a>
				</form>
			</div>
		</div>
	</div>

	<div id="clazzInfoWin" class="easyui-window normal_win" data-options="title:'员工信息', closed:true">
		<table id="clazzInfoList" class="easyui-datagrid"
			   data-options="
				rownumbers:true,
				border:false,
				singleSelect:false,
				pagination:false,
				pageSize:10">
			<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'title',width:100">名称</th>
			</tr>
			</thead>
		</table>
	</div>
</body>

<script>

	function openClazzWin() {
	    var row = $("#list").datagrid("getSelected");
	    if (row) {
            openWin("clazzWin");
            var options = pagerOptions("clazzList");
            $("#clazzList").datagrid(
                {
                    url: contextPath + '/class/pager_criteria',
                    method: 'post',
                    pageSize: options.pageSize
                }
            );
        } else {
	        showInfoAlert("请选择需要分配员工的部门")
		}
	}

	function confirmClazz() {
	    var row = $("#list").datagrid("getSelected");
	    var rows = $("#clazzList").datagrid("getSelections");
	    if (rows !== null && rows.length > 0) {
	        var classIds = "";
	        for (var i = 0, len = rows.length; i < len; i++) {
	            if (classIds === "") {
	                classIds = rows[i].id;
				} else {
                    classIds = classIds + "," + rows[i].id
				}
			}
	        $.post(contextPath + "/teacher/save_class",
				{
				    'teacherId':row.id,
					'classIds':classIds
				},
				function (data) {
	            	if (data.result === "success") {
	            	    closeWin("clazzWin");
					} else {
	            	    showInfoAlert(data.message);
					}
				},
				'json'
			);
		} else {
	        showInfoAlert("请选择员工");
		}
	}

	function openClazzInfoWin() {
	    var row = $("#list").datagrid("getSelected");
	    if (row) {
	        openWin("clazzInfoWin");
            $("#clazzInfoList").datagrid(
                {
                    url: contextPath + '/teacher/class',
                    method: 'post',
					pageSize:100,
					pageNumber:1,
					queryParams:{
                        teacherId:row.id
					}
                }
            );
		} else {
	        showInfoAlert("请选择需要查看员工的部门")
		}
	}
</script>
</html>
