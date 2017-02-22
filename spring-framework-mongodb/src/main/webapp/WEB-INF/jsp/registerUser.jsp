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
		function reguser(){
			
			$("#frmstu").form("submit" , {
				url : "reguser.do" ,
				success : function(data){
					var json = JSON.parse(data);
					if(json.result == "sucess"){
						$.messager.alert("消息" , "注册用户成功" , "info" , function(){
							window.location.href='<%=basePath%>login.do'
						})
					}else if(json.result == "fail"){
						$.messager.alert("错误" , json.errmsg , "error");
						window.location.href='<%=basePath%>registerUser.do'
					}
				}
			})
		}
		function reset(){
			$("#name").val("");
			$("#password").val("");
		}
	</script>
</head>
<body >
	
	<div id = "dlgForm" style = "display:block ; padding:5px">
		<form id="frmstu" method="post" >
			
			<div>
				<label>用户名:</label>
					<input class="easyui-validatebox" type="text" name="name" id = "name"  data-options="width:150, required :true"/>
			</div>
			
			
			<div>
				<label>密码:</label>
					<input class="easyui-validatebox" type="text" name="password" id = "password"  data-options="width:150, required :true"/>
			</div>
			
			
			<div style = "margin:10px 0px 0px 70px" >
				<a id="btnSave" onclick="reguser();" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
				<a id="btnCancel" onclick="reset();" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-cancel'">取消</a>
			</div>
		</form>
	</div>
	
	
</body>
</html>