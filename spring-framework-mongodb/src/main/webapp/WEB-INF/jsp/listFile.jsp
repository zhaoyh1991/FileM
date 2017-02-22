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
    <link href="resources/css/page.css" rel="stylesheet"/>
    <script type="text/javascript">
    	function getFile(f){
    		window.location.href="getFile.do?f="+f;
    	}
    </script>
</head>
<body>
    <table>
    	<thead>
            <th>文件名</th>
            <th>用户名</th>
            <th>类别名</th>
            <th>上传时间</th>
        </thead>
    	<c:forEach var="f" items="${files}">
    		<tr><td onclick="getFile('${f.storename}');"><a>${f.filename}</a></td><td>${f.username}</td><td>${f.groupname}</td><td>${f.uploadTime}</td></tr>
    	</c:forEach>
    
    </table>
</body>
</html>