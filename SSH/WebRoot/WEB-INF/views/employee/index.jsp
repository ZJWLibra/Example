<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
%>

<!DOCTYPE HTML>
<html>
<head>
	<base href="<%=path%>/">
	<title>员工管理</title>
	
	<script type="text/javascript" src="js/jquery.min.js"></script>
    <script type="text/javascript" src="easyui/jquery.easyui.min.js"></script>
    <script type="text/javascript" src="easyui/locale/easyui-lang-zh_CN.js"></script>
    <script type="text/javascript" src="js/main.js"></script>
    <link rel="stylesheet" type="text/css" href="easyui/themes/default/easyui.css">
    <link rel="stylesheet" type="text/css" href="easyui/themes/icon.css">
    
    <script type="text/javascript">
    	$(function() {
    		$('#department').combobox({
                url : 'employee/listDepartmentAll',
                valueField : 'id',
                textField : 'name'
            });
    	});
    </script>
</head>
<table id="list" class="easyui-datagrid"
		data-options="
			toolbar:'#tb',
			rownumbers:true,
			border:false,
			singleSelect:true,
			pagination:true,
			pageSize:10,
			url:'employee/list',
			method:'post'">
		<thead>
			<tr>
				<th data-options="field:'id',width:80,checkbox:true">编号</th>
				<th data-options="field:'name',width:100">姓名</th>
				<th data-options="field:'sex',width:100">性别</th>
				<th data-options="field:'age',width:100">年龄</th>
				<th data-options="field:'birthday',width:200,formatter:formatBirthday">生日</th>
				<th data-options="field:'department',width:100" formatter="formatDepartment">所属部门</th>
				<th data-options="field:'province',width:100" formatter="formatProvince">所属省</th>
				<th data-options="field:'city',width:100" formatter="formatCity">所属市</th>
				<th data-options="field:'district',width:100" formatter="formatDistrict">所属区</th>
			</tr>
		</thead>
	</table>
	<script type="text/javascript">
		function formatDepartment(value) {
			if (value != null) {
				return value.name;
			}
		}
		function formatProvince(value) {
			if (value != null) {
				return value.name;
			}
		}
		function formatCity(value) {
			if (value != null) {
				return value.name;
			}
		}
		function formatDistrict(value) {
			if (value != null) {
				return value.name;
			}
		}
		function formatBirthday(value){
			var date = new Date(value);
            var year = date.getFullYear().toString();
            var month = (date.getMonth() + 1);
            var day = date.getDate().toString();
            var hour = date.getHours().toString();
            var minutes = date.getMinutes().toString();
            var seconds = date.getSeconds().toString();
            if (month < 10) {
                month = "0" + month;
            }
            if (day < 10) {
                day = "0" + day;
            }
            if (hour < 10) {
                hour = "0" + hour;
            }
            if (minutes < 10) {
                minutes = "0" + minutes;
            }
            if (seconds < 10) {
                seconds = "0" + seconds;
            }
            //return year + "-" + month + "-" + day + " " + hour + ":" + minutes + ":" + seconds;
            return year + "-" + month + "-" + day;
		}
		
		
	</script>
	
	<div id="tb" style="height: auto">
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="openWin('addWin');">添加</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-edit" onclick="openDepEditWin('editWin', 'list', 'editForm', 'employee')">修改</a>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="window.open('employee/exportExcel');">导出</a>
		<form id="importForm" action="employee/importExcel" method="post" enctype="multipart/form-data">
		<input name="employeeExcel" class="easyui-filebox" data-options="buttonText:'选择',prompt:'请选择要导入的文件'"/>
		</form>
		<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-add" onclick="$('#importForm').submit();">导入</a>
		<div>
			<form id="searchForm">
				<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入姓名',
						required:false,
						novalidate:true" name="department.name"/>
				<a href="javascript:void(0);" class="easyui-linkbutton" iconCls="icon-search" onclick="doSearch('list', 'searchForm');">搜索</a>
			</form>
		</div>
	</div>
	
	<div id="addWin" class="easyui-window normal_win" data-options="title:'添加员工', closed:true">
		<form id="addForm">
			<table>
				<tr>
					<td>姓名</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入员工名称',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="employee.name"/>
					</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<input type="radio" name="employee.sex" value="男" checked="checked" />男
						<input type="radio" name="employee.sex" value="女" />女
					</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入员工年龄'" name="employee.age"/>
					</td>
				</tr>
				<tr>
					<td>生日</td>
					<td>
						<input class="easyui-datebox" name="employee.birthday">
					</td>
				</tr>
				<tr>
					<td>所属部门</td>
					<td>
						<input id="department" class="easyui-combobox easyui-validatebox" data-options="prompt:'请选择部门',
						required:true,
						validType:['length[1,20]'],
						novalidate:true" name="employee.department.id" />
					</td>
				</tr>
				<tr>
					<td>所属地区:</td>
					<td>
					    <input id="province" class="easyui-combobox" name="employee.province.id" editable="false" data-options="
					            width:80,
					            required:true,
					                valueField: 'id',
					                textField: 'name',
					            url: 'employee/listProvince',
					            onSelect: function(value){
					                $('#city').combobox('clear');  
					                $('#district').combobox('clear');  
					                var url = 'employee/listCityByProvinceId?employee.province.id='+value.id;
					                $('#city').combobox('reload', url);
					            }">
					    <input id="city" class="easyui-combobox" name="employee.city.id" editable="false" data-options="
					            width:80,
					            required:true,
					            valueField: 'id',
					                textField: 'name',
					            onSelect: function(value){
					                $('#district').combobox('clear');
					                var url = 'employee/listDistrictByCityId?employee.city.id='+value.id;
					                $('#district').combobox('reload', url);
					            }">
					    <input id="district" class="easyui-combobox" name="employee.district.id" editable="false" data-options="
					            width:80,
					            required:true,
					            valueField:'id',
					            textField:'name'">
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="save('employee/insert', 'addForm', 'addWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
	<script type="text/javascript">
		var k = 0;
		var l = 0;
		function openDepEditWin (winId, listId, formId, prefix) {
		    var row = $("#" + listId).datagrid("getSelected");
		    if (row != null) {
		    	k = 1;
		    	l = 1;
		    	$("#department_edit").combobox({
					url : 'employee/listDepartmentAll',
	                valueField : 'id',
	                textField : 'name',
	                value : row.department.id
		    	});
		    	$("#province_edit").combobox({
		    		required:true,
	                valueField: 'id',
	                textField: 'name',
	            	url: 'employee/listProvince',
	            	value: row.province.id,
	            	onSelect: function(value){
		                $('#city_edit').combobox('clear');  
		                $('#district_edit').combobox('clear');
		                var url = 'employee/listCityByProvinceId?employee.province.id='+value.id;
		                $('#city_edit').combobox('reload', url);
		                if(k == 1) {
		                	$('#city_edit').combobox('select', row.city.id);
		                	k = 0;
		                }
		            }
		    	});
		    	$("#city_edit").combobox({
		    		required:true,
		            valueField: 'id',
		            textField: 'name',
		            onSelect: function(value){
		            	if(l == 1) {
		            		$('#district_edit').combobox('clear');
			                var url = 'employee/listDistrictByCityId?employee.city.id='+value.id;
			                $('#district_edit').combobox('reload', url);
		            	}
		                if(l == 1) {
		                	$('#district_edit').combobox('select', row.district.id);
		                	l = 0;
		                }
		            }
		    	});
		        $("#" + formId).form("load", datagridRowHandler(row, prefix));
		        openWin(winId);
		    } else {
		        showInfoAlert("请选择需要修改的数据");
		    }
		}
	</script>
	
	<div id="editWin" class="easyui-window normal_win" data-options="title:'编辑部门', closed:true">
		<form id="editForm">
			<input type="hidden" name="employee.id" />
			<table>
				<tr>
					<td>姓名</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入姓名',
						required:true,
						validType:['length[2,20]'],
						novalidate:true" name="employee.name"/>
					</td>
				</tr>
				<tr>
					<td>性别</td>
					<td>
						<input type="radio" name="employee.sex" value="男" />男
						<input type="radio" name="employee.sex" value="女" />女
					</td>
				</tr>
				<tr>
					<td>年龄</td>
					<td>
						<input class="easyui-textbox easyui-validatebox" data-options="prompt:'请输入员工年龄'" name="employee.age"/>
					</td>
				</tr>
				<tr>
					<td>生日</td>
					<td>
						<input class="easyui-datebox" name="employee.birthday">
					</td>
				</tr> 
				<tr>
					<td>所属部门</td>
					<td>
						<input id="department_edit" class="easyui-combobox easyui-validatebox" data-options="prompt:'请选择部门',
						required:true,
						validType:['length[1,20]'],
						novalidate:true" name="employee.department.id" />
					</td>
				</tr>
				<tr>
					<td>所属地区:</td>
					<td>
					    <input id="province_edit" class="easyui-combobox" name="employee.province.id" editable="false" data-options="
					            width:80">
					    <input id="city_edit" class="easyui-combobox" name="employee.city.id" editable="false" data-options="
					            width:80">
					    <input id="district_edit" class="easyui-combobox" name="employee.district.id" editable="false" data-options="
					            width:80,
					            required:true,
					            valueField:'id',
					            textField:'name'">
					</td>
				</tr>
				<tr>
					<td><a class="easyui-linkbutton" onclick="edit('employee/update', 'editForm', 'editWin', 'list');">确认</a></td>
				</tr>
			</table>
		</form>
	</div>
	
</body>
</html>
