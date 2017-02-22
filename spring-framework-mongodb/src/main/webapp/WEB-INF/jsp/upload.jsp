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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1,maximum-scale=1,user-scalable=no" />
    <title></title>
    <!-- 如果需要导入资源路径写法类似  <link href="resources/css/mui.min.css" rel="stylesheet"/> -->
    
    <link type="text/css" rel="stylesheet" href="resources/css/metro/easyui.css">
	<link type="text/css" rel="stylesheet" href="resources/css/icon.css">
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
	<script type="text/javascript">
		function upload(){
			$("#upform").form("submit" ,{
				url : "save.do" ,
				success : function(data){
					var json = JSON.parse(data);
					if(json.result == "sucess"){
						$.messager.alert("消息" , "文件上传成功" , "info" , function(){
						})
					}else if(json.result == "fail"){
						$.messager.alert("错误" , json.errmsg , "error");
					}
				}
			});
		}
	
	</script>    
</head>
<body>
      <h1 >上传资料</h1>
    <div style="margin-top: 10px;">	
    <form method="post" action="save.do" enctype="multipart/form-data" id="upform">
    	文件类别:
    	<select id="group" name="group">
    		<option value="图片">图片</option>
    		<option value="文件">文件</option>
    	</select>
    	<br/>
    	选择一个文件:
	    <input type="file"  name="file" />
	    <br/><br/>
	    <input type="submit" value="提交">
	    <a id="btnSave" onclick="upload();" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-ok'">保存</a>
	</form>
	</div>
</body>
</html>