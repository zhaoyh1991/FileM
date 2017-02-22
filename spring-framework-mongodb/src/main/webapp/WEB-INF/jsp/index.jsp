<%@ page language="java" import="java.util.*" pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
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
  	 <link type="text/css" rel="stylesheet" href="resources/css/metro/easyui.css">
	<link type="text/css" rel="stylesheet" href="resources/css/icon.css">
	<script type="text/javascript" src="resources/js/jquery.min.js"></script>
	<script type="text/javascript" src="resources/js/jquery.easyui.min.js"></script>
	
	<script>
		$(function(){
			$("#menu").tree({
				onClick : function(node){
					console.info(node);
					var isExists = $("#tabContainer").tabs("exists" , node.text);
					//判断是否存在指定的页签
					if(isExists == true){
						$("#tabContainer").tabs("select" , node.text);
					}else{
						if(node.attributes.url != null){
							$("#tabContainer").tabs("add" , {
								title :  node.text,
								content : "<iframe src = '" + node.attributes.url + "' width='100%' height='97%' style='border:0px'></iframe>",
								closable : true,
								selected: true,
								border : false
							}); 
						}
					}
				}
			})
		})
	</script>
</head>
<body>
	<div id="cc" class="easyui-layout" style="width:1200px;height:800px;"> 
    <div data-options="region:'north',title:'标题栏'" style="height:100px;">
    	<h2 align="center">文件信息管理</h2>
    </div>  
    <div data-options="region:'west',title:'菜单栏',split:true" style="width:180px;">
    	
    	<ul class="easyui-tree" id="menu">
			<li>
				<span>文档管理</span>
				<ul>
					<li data-options = "attributes:{url : 'listFile.do'}">列出文件</li>
					<li data-options = "attributes:{url : 'upload.do'}">上传文件</li>
				</ul>
			</li>
			<li>
				<span>学生信息</span>
				<ul>
					<li data-options = "attributes:{url : 'stuinfo.do'}">学生信息管理</li>
				</ul>
			</li>
			
		</ul>
    	
    </div>  
    
    <div data-options="region:'center',title:'内容'" style="padding:5px;background:#eee;">
    	
    	<div class="easyui-tabs" data-options="fit:true" id = "tabContainer">
			<div title = "首页 ">
				<h1 align="center">welcome!!!!</h1>
			</div>
		</div>
    
    </div>  
    
    </div>
</body> 
</body>
</html>