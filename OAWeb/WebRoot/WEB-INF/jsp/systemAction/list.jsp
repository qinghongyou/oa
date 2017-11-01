<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- 显示数据 --%>
<html>
<head>
    <title>系统信息</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 系统信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="10" class="TableStyle">
       <tr>
       		<td>版本历程:</td>
       </tr>
       <tr>
       		<td>
       			&nbsp;&nbsp;1、建站时间&nbsp;&nbsp;&nbsp;&nbsp;2017年4月20日<br><br>
       			&nbsp;&nbsp;2、第一版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2017年5月1日<br><br>
       			&nbsp;&nbsp;3、第二版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2017年5月10日<br><br>
       			&nbsp;&nbsp;4、第三版&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2017年5月18日<br><br>
       			&nbsp;&nbsp;5、所用技术&nbsp;&nbsp;&nbsp;&nbsp;SSH框架+后台数据库<br><br>
       			&nbsp;&nbsp;6、访问方式&nbsp;&nbsp;&nbsp;&nbsp;http://localhost:8080/OAWeb/<br><br>
       		</td>
       </tr>
       <tr>
       		<td>系统描述:</td>
       </tr>
       <tr>
       		<td>
       			&nbsp;&nbsp;1、组织机构管理包括岗位的管理，实现对岗位的增删改查和设置权限，部门管理实现部门的增删改查，用户管理实现增删改查和初始化密码（用MD5摘要实现）。<br><br>
       			&nbsp;&nbsp;2、网上交流包括管理员有的论坛管理，管理员可以增删改查板块、还可以上移，下移板块，以便访问者更好的了解现在最流行的论坛话题；论坛主要实现了按各类条件查询，分页显示。<br><br>
       			&nbsp;&nbsp;3、审批流程实现了部署流程，查看流程图，申请模板管理，用户起草申请，查看自己流程状态，管理人员可以查看待自己审批的信息，进而进行相应的操作。<br><br>
       			&nbsp;&nbsp;4、个人设置中可以查看个人信息和修改自己的密码。<br><br>
       		</td>
       </tr>
    </table>
   
</div>
</body>
</html>
