<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	//request.getRequestDispatcher("/WEB-INF/jsp/flowAction/myTaskList.jsp").forward(request,response);
	response.sendRedirect(request.getContextPath()+"/flow_myTaskList.action");
%>
