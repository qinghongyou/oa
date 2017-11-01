<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- 显示数据 --%>
<html>
<head>
    <title>联系我们</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 联系我们
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="10" class="TableStyle">
       <tr>
       		<td>联系我们:</td>
       </tr>
       <tr>
       		<td>
       			&nbsp;&nbsp;联系部门&nbsp;&nbsp;&nbsp;&nbsp;信息技术中心<br><br>
       			&nbsp;&nbsp;联系电话&nbsp;&nbsp;&nbsp;&nbsp;0791-88888888<br><br>
       			&nbsp;&nbsp;邮&nbsp;&nbsp;箱&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;admin@126.com<br><br>
       			&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;Q&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;888888888<br><br>
       			&nbsp;&nbsp;联系地址&nbsp;&nbsp;&nbsp;&nbsp;江西省南昌市XXX区XXX路XXX号<br><br>
       		</td>
       </tr>
       <tr>
       		<td>网站纠错:</td>
       </tr>
       <tr>
       		<td>
       			&nbsp;&nbsp;欢迎各位同学和各界人士就本网站存在信息错误、空链、错链等情况，反映于admin@126.com
       		</td>
       </tr>  
       
    </table>
   
</div>
</body>
</html>
