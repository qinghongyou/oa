<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%-- 显示数据 --%>
<html>
<head>
    <title>版权所有</title>
	<%@ include file="/WEB-INF/jsp/public/header.jspf" %>
</head>
<body>
 
<div id="Title_bar">
    <div id="Title_bar_Head">
        <div id="Title_Head"></div>
        <div id="Title"><!--页面标题-->
            <img border="0" width="13" height="13" src="${pageContext.request.contextPath}/style/images/title_arrow.gif"/> 版权所有
        </div>
        <div id="Title_End"></div>
    </div>
</div>

<div id="MainArea">
    <table cellspacing="0" cellpadding="10" class="TableStyle">
       <tr>
       		<td>版权说明:</td>
       </tr>
       <tr>
       		<td>
       			&nbsp;&nbsp;1、http://localhost:8080/OAWeb/为SSH三大框架整合开发的实训网站，是我们小组学习SSH框架的整合，也是提高我们整体编程水平的一个体现。同时是宣传和展示OAWeb的窗口，各位学生交流沟通的渠道。<br><br>
       			&nbsp;&nbsp;2、本网站一贯高度重视和遵守互联网信息传播的国家相关法律法规。用户不可以利用本站传播任何具威胁性、诽谤性、报复性的非法信息，不能违反国家相关法律法规。<br><br>
       			&nbsp;&nbsp;3、欢迎单位及个人引用本站信息来宣传和传播，但不能对信息进行恶意修改和用于非法之处。<br><br>
       			&nbsp;&nbsp;4、本站点严格遵守中华人民共和国版权法，所引用外部文字、图片、课件、数字作品和资料均为非商业用途使用，仅限于学习和交流，所引文字、图片及其它形式资料版权属原作者所有.<br><br>
       			&nbsp;&nbsp;5、如因作品内容、版权和其他问题需要同本站联系，请电邮admin@126.com。<br><br>
       			&nbsp;&nbsp;5、为推广和宣传本网站，欢迎与正规网站交换链接。但未经本网站的书面许可，对本网站上的任何内容，任何人不得做镜像。<br><br>
       		</td>
       </tr>   
    </table>
   
</div>
</body>
</html>
