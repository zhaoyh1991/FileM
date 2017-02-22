<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
	<style type="text/css">
		#frmstu div {
			margin : 5px;
		}
		#frmstu input{
			width : 150px
		}
		input , select , textarea{
			font-size : 12px;
		}
	</style>
	
	<link type="text/css" rel="stylesheet" href="resources/css/metro/easyui.css">
	<link type="text/css" rel="stylesheet" href="resources/css/icon.css">
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		$(function(){
			var d = new Date().getFullYear() + "-" + (new Date().getMonth()+1) + "-" + new Date().getDate();
			$("#birthday" ).datebox("setValue" , d);
			
			$("#gridStore").datagrid({
				title : '信息',
				url : "stuInfos.do",
				fit : true,
				border : false,
				singleSelect:true,
				columns : [
					[
					 	{field : "id" , title : "学号" , width : 100},
					 	{field : "name" , title : "姓名" , width : 100} ,					 	
					 	{field : "birthday" , title : "出生日期" , width : 100 ,formatter : function(value,row,index){
					 		var unixTimestamp = new Date( value) ;
					 		var year=unixTimestamp.getFullYear();
					 		var month=unixTimestamp.getMonth()+1;
					 		var day=unixTimestamp.getDate()+1;
					 		return year+"/"+month+"/"+day;
					 		
					 	}},
					 	{field : "addr" , title : "住址" , width : 100},
					 	{field : "academy" , title : "学院" , width : 100},
					 	{field : "major" , title : "专业" , width : 100},
					 	{field : "clazz" , title : "班级" , width : 100},
					 	{field : "gender" , title : "性别" , width : 100},
					 	{field : "state" , title : "状态" , width : 100,formatter : function(value,row,index){
					 		if(value==1){
					 			return "在校"
					 		}else{
					 			return "毕业"
					 		}
					 	}}
					 ]
				],
				toolbar : [
							{
								text : "刷新" , iconCls : "icon-reload" , handler : function(){
									$("#gridStore").datagrid("reload");
								}
							},
							{
								text : "新增" , iconCls : "icon-add" , handler : function(){
									$("#frmstu input , #frmstu textarea").val("");
									$("#frmstu").attr("mode" , "save");
									$("#dlgForm").css("display" , "block");
									$("#dlgForm").dialog({
										title : "新增客户",
										width : 300,
										height : 250 , 
										modal : true
									})
								}
							},
							{
								text : "删除" , iconCls : "icon-remove" , handler : function(){
									var rec= $("#gridStore").datagrid("getSelected");
									console.info(rec);
									if(rec == null){
										$.messager.alert("警告" , "请选择要更新的学生" , "warning");
										return;	
									}
									$("#delid").val(rec.id)
									$.messager.confirm('Confirm', '是否确认删除', function(r){
										if (r){
											$("#delstu").form("submit" , {
												url : "delStu.do" ,
												success : function(data){
													var json = JSON.parse(data);
													if(json.result == "sucess"){
														$.messager.alert("消息" , "删除学生成功" , "info" , function(){
															$("#gridStore").datagrid("reload");
															
														})
													}else if(json.result == "fail"){
														$.messager.alert("错误" , json.errmsg , "error");
													}
												}
											});
										}
									});
									
								}
							},
							{
								text : "更新资料" , iconCls : "icon-edit" , handler : function(){
									
									var rec= $("#gridStore").datagrid("getSelected");
									console.info(rec);
									if(rec == null){
										$.messager.alert("警告" , "请选择要更新的学生" , "warning");
										return;	
									}
									$("#frmstu #id").val(rec.id);
									$("#frmstu #name").val(rec.name);
									$("#frmstu #birthday").val(rec.birthday);
									$("#frmstu #addr").val(rec.addr);
									$("#frmstu #academy").val(rec.academy);
									$("#frmstu #major").val(rec.major);
									$("#frmstu #clazz").val(rec.clazz);
									$("#frmstu #gender").val(rec.gender);
									$("#frmstu #state").val(rec.state);
									$("#frmstu").form("validate");
									$("#frmstu").attr("mode" , "update");
									$("#dlgForm").css("display" , "block");
									$("#dlgForm").dialog({
										title : "更新资料",
										width : 300,
										height : 250 , 
										modal : true
									})
								}
							}
						]
			});
			
		});		
		
		$(function(){
			$("#btnSave").bind("click" , function(){
				var mode = $("#frmstu").attr("mode");
				if(mode == "save"){
					$("#frmstu").form("submit" , {
						url : "saveStu.do" ,
						success : function(data){
							var json = JSON.parse(data);
							if(json.result == "sucess"){
								$.messager.alert("消息" , "添加学生成功" , "info" , function(){
									$("#dlgForm").dialog("close");
									$("#gridStore").datagrid("reload");
								})
							}else if(json.result == "fail"){
								$.messager.alert("错误" , json.errmsg , "error");
							}
						}
					})
				}else if(mode=="update"){
					$("#frmstu").form("submit" , {
						url : "updateStu.do" ,
						success : function(data){
							var json = JSON.parse(data);
							if(json.result == "sucess"){
								$.messager.alert("消息" , "更新学生成功" , "info" , function(){
									$("#dlgForm").dialog("close");
									$("#gridStore").datagrid("reload");
								})
							}else if(json.result == "fail"){
								$.messager.alert("错误" , json.errmsg , "error");
							}
						}
					})
				}
			});
			
			$("#btnCancel").click(function(){
				$("#dlgForm").dialog("close");
			});
		})
</script>
</head>
<body class = "easyui-layout">
	<div data-options = "region: 'center' " >
		<table id="gridStore" rownumbers="true" pagination="true"></table>
	</div>
	
	<div id = "dlgForm" style = "display:none ; padding:5px">
		<form id="frmstu" method="post">
			<div>
				<label>学号:</label>
					<input class="easyui-validatebox" type="text" name="id" id = "id" data-options="width:150 , required :true" />
			</div>
			
			<div>
				<label>姓名:</label>
					<input class="easyui-validatebox" type="text" name="name" id = "name"  data-options="width:150, required :true"/>
			</div>
			
			<div>
				<label>生日:</label>
					<input class="easyui-datebox" type="text" name="birthday" id = "birthday"  data-options="width:150, required :true,validType:'validPhone'"/>
			</div>
			
			<div>
				<label style="vertical-align: top">地址:</label>
					<textarea class="easyui-validatebox" name = "addr" id = "addr" data-options = "required :true" style = "width:150px;height:50px"></textarea>
			</div>
			
			
			<div>
				<label>学院:</label>
					<input class="easyui-validatebox" type="text" name="academy" id = "academy"  data-options="width:150, required :true"/>
			</div>
			
			<div>
				<label>专业:</label>
					<input class="easyui-validatebox" type="text" name="major" id = "major"  data-options="width:150, required :true"/>
			</div>
			
			<div>
				<label>班级:</label>
					<input class="easyui-validatebox" type="text" name="clazz" id = "clazz"  data-options="width:150, required :true"/>
			</div>
			
			<div>
				<label>性别:</label>
					<select id="gender" name="gender">
						<option value="男">男</option>
						<option value="女">女</option>
					</select>
			</div>
			
			<div>
				<label>状态:</label>
					<select id="state" name="state">
						<option value="1">在校</option>
						<option value="2">毕业</option>
					</select>
			</div>
			
			<div style = "margin:10px 0px 0px 70px" >
				<a id="btnSave" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
				<a id="btnCancel" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</form>
	</div>
	
	<div  style = "display:none ; padding:5px">
		<form id="delstu"  method="post">
			<input type = "hidden" name="id" id = "delid"/>
		</form>
	</div>
	
</body>
</html>