<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cdsxt.resident.vo.*"%>
<%@ page import="util.DB"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'update.jsp' starting page</title>
    <script language="javascript" type="text/javascript" src="My97DatePicker/WdatePicker.js"></script>
    
    <script type="text/javascript" src="wbox/jquery1.4.2.js"  ></script>
    <script type="text/javascript" src="wbox/wbox-min.js"  ></script>
    <link rel="stylesheet" type="text/css" href="wbox/wbox/wbox-min.css">
    
    <script type="text/javascript">
    
    	function showRealPhoto(rltrealpath,photoname){
				wbox = $('.wboxImg').wBox({//图片wbox
				     title: "查看照片："+photoname,
					 requestType: "img",
					 timeout:3000,
					 target:rltrealpath
					});
				 wbox.showBox();
			}
    
    
    </script>

  </head>
  
  <body>&nbsp; 
  <br><br/>
  <br><br/>
  	<form action="ResidentServlet?opt=showMsg" method="post">
	    <table align="center">
		    <tr>
	  			<td>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			${resident.rname}<lable>的个人信息</lable>
	  			</td>
	  		</tr>
		    <tr>
	  			<th>序号</th>
	  			<td>${resident.id }</td>
	  		</tr>
		    <tr>
	  			<th>住户名</th>
	  			<td>${resident.rname }</td>
	  		</tr>
	  		<tr>
	  			<th>性别</th>
	  			<c:if test="${resident.gender==0}">
	  				<td>男</td>
		     	</c:if>
		     	<c:if test="${resident.gender==1}">
	  				<td>女</td>
		     	</c:if>  
	  		</tr>
	  		<tr>
	  			<th>年龄</th>
	  			<td>${resident.age }</td>
	  		</tr>
	  		<tr>
	    			<th>身份证号</th>
	  			<td>${resident.cid }</td>
	  		</tr>
	  		<tr>
	  			<th>学历</th>
	  			<c:if test="${resident.edu==1}">
	  			<td>专科以下</td>
	  		     </c:if>
	  		     <c:if test="${resident.edu==2}">
	  			<td>专科</td>
	  		     </c:if>
	  		     <c:if test="${resident.edu==3}">
	  			<td>本科</td>
	  		     </c:if>
	  		     <c:if test="${resident.edu==4}">
	  			<td>硕士</td>
	  		     </c:if>
	  		     <c:if test="${resident.edu==5}">
	  			<td>硕士以上</td>
	  		     </c:if>
	  		</tr>
	  		<tr>
	  			<th>邮箱</th>
	  			<td>${resident.email }</td>
	  		</tr>
	  			<tr>
	  			<th>所租房间</th>
	  			<td>${resident.rent }</td>
	  		</tr>
	  		<tr>
	  			<th>手机号</th>
	  			<td>${resident.phone }</td>
	  		</tr>
	  		<tr>
	  			<th>合同开始日期</th>
	  			<td>${resident.bdate }</td>
	  		</tr>
	  		<tr>
	  			<th>合同结束日期</th>
	  			<td>${resident.edate }</td>
	  		</tr>
	  		<tr>
	  			<th>住户照片</th>
	  			<td>
	  				<div>
		  				<c:if test="${resident.rltprepath!=null}">
		  						<img src="${resident.rltprepath }" title="${resident.photoname }" onmousemove="showRealPhoto('${resident.rltrealpath}','${resident.photoname }');"></img><!-- 根据相对地址(不是绝对地址)显示图片 -->
			  			</c:if>
			  			<c:if test="${resident.rltprepath==null}">
			  				<label style="color: red;">该用户没有上传照片</label>
			  			</c:if>
		  			</div>
	  			</td>
	  		</tr>
	    </table> 
  	</form>
  </body>
</html>
