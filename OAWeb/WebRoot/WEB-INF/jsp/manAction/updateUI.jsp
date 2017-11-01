<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
	<title>修改密码</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
    <script language="javascript" src="${pageContext.request.contextPath}/script/DemoData.js" charset="utf-8"></script>
	<script language="javascript" src="${pageContext.request.contextPath}/script/DataShowManager.js" charset="utf-8"></script>
    <script type="text/javascript">
	    function validate1(){
			var no=document.getElementById("oldPassword").value;
			var reg=/\w/;
			if(reg.test(no)){
				$("#oldmsg").css("color","blue");
				$("#oldmsg").html("*");	
			}else{
				$("#oldmsg").css("color","red");
				$("#oldmsg").html("×");
			}
		}
	    
	    function validate2(){
			var no=document.getElementById("password").value;
			var reg=/\w/;
			if(reg.test(no)){
				$("#passmsg").css("color","blue");
				$("#passmsg").html("*");	
			}else{
				$("#passmsg").css("color","red");
				$("#passmsg").html("×");
			}
		}
	    
	    function validate3(){
	    	var no=document.getElementById("password").value;
			var no2=document.getElementById("password2").value;
			var reg=/\w/;
			if(reg.test(no2)&&(no===no2)){
				$("#newmsg").css("color","blue");
				$("#newmsg").html("*");	
			}else{
				$("#newmsg").css("color","red");
				$("#newmsg").html("两次密码不一致！");
			}
		}
    </script>
</head>
<body>

<!-- 标题显示 -->
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 修改密码
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<!--显示表单内容-->
<div id=MainArea>
    <s:form action="man_update">
        <div class="ItemBlock_Title1"><!-- 信息说明<DIV CLASS="ItemBlock_Title1">
        	<IMG BORDER="0" WIDTH="4" HEIGHT="7" SRC="${pageContext.request.contextPath}/style/blue/images/item_point.gif" /> 修改密码 </DIV>  -->
        </div>
        
        <!-- 表单内容显示 -->
        <div class="ItemBlockBorder">
            <div class="ItemBlock">
                <table cellpadding="0" cellspacing="0" class="mainForm">
					<tr height="50">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请输入原密码
						</td>
						<td>
							<input type="password" name="oldPassword" id="oldPassword" class="InputStyle" onblur="validate1()"/>
							<foot color="red" ><span id="oldmsg">*</span></foot>
						</td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							请填写新密码
						</td>
						<td>
							<input type="password" name="password" id="password" class="InputStyle" onblur="validate2()"/> 
							<foot color="red" ><span id="passmsg">*</span></foot>
						</td>
						<td></td>
					</tr>
					<tr height="25">
						<td width="150">
							<img border="0" width="4" height="7" src="${pageContext.request.contextPath}/style/blue/images/item_point.gif" />
							再次输入新密码
						</td>
						<td>
							<input type="password" name="password2" id="password2" class="InputStyle" onblur="validate3()" />
							<foot color="red" ><span id="newmsg">*</span></foot>
						</td>
						<td></td>
					</tr>
                </table>
            </div>
        </div>
       
        <!-- 表单操作 -->
        <div id="InputDetailBar">
            <input type="image" src="${pageContext.request.contextPath}/style/images/save.png"/>
            <a href="javascript:history.go(-1);"><img src="${pageContext.request.contextPath}/style/images/goBack.png"/></a>
        </div>
    </s:form>
</div>

<div class="Description">
	验证规则：<br />
	1，旧密码不能为空。<br />
	2，新密码不能为空。<br />
	3，再次输入的密码要和新密码一致。<br />
</div>

</body>
</html>



