<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>个人信息</title>
    <%@ include file="/WEB-INF/jsp/public/header.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <script type="text/javascript">
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 个人信息
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <form action="" enctype="multipart/form-data">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 个人信息 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <s:iterator value="myList">
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr>
                        <td width="150">用户ID（登录名）</td>
                        <td>${loginName}&nbsp;</td>
						<td rowspan="5" align="right">
							<img src="${pageContext.request.contextPath}/style/images/defaultAvatar.gif"/>
						</td>
                    </tr>
                    <tr>
                        <td>姓名</td>
                        <td>${name}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>性别</td>
                        <td>${gender}&nbsp;</td>
                    </tr>
                    <tr>
                        <td>部门</td>
                        <td>${department.name}&nbsp;</td>
                    </tr>
					<tr>
                        <td>岗位</td>
                        <td>
							<s:iterator value="roles">
		                		${name}
		                	</s:iterator>&nbsp;
						</td>
                    </tr>
					<tr>
                        <td>联系电话</td>
                        <td>${phoneNumber}&nbsp;</td>
                    </tr>
                </table>
            </div>
        </div>
       </s:iterator>
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </form>
</div>

<div class="Description">
	个人信息：<br />
	1，欢迎查看个人信息，如有错误请与管理员联系。<br />
</div>

</body>
</html>


