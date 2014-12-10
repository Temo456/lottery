<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
	
	response.setHeader("Access-Control-Allow-Origin", "*");
%>

<link href="css/layout_lottery.css" rel="stylesheet" type="text/css" />