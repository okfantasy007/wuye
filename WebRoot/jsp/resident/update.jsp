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
    <script type="text/javascript" src="Validform_v5.3.2/Validform_v5.3.2/demo/js/jquery-1.9.1.min.js"></script>
     <script type="text/javascript" src="Validform_v5.3.2/Validform_v5.3.2/demo/js/Validform_v5.3.2_min.js"></script>
     
     <script type="text/javascript">
	    var valid;
		$(function(){valid=$(".demoform").Validform({
			showAllError:true,
			tiptype:3,
			beforeSubmit:function(curform){
					//在验证成功后，表单提交前执行的函数，curform参数是当前表单对象。
					//这里明确return false的话表单将不会提交;	
					alert("修改成功!");
					window.parent.closeAndRefresh();
				}
			})
		})
	</script>
  </head>
  
  <body>
  <br><br/>
  	<form action="ResidentServlet?opt=update" id="demoform" class="demoform" method="post" enctype="multipart/form-data">
  		<input name="name"  type="hidden"/>
  		<input type="hidden" name="id" value="${resident.id}" />
	    <table align="center">
	    	<tr>
	  			<th>住户名</th>
	  			<td><input name="rname"  value="${resident.rname }"  datatype="s2-10" errormsg="请输入2-10位字符！" nullmsg="住户名不能为空!"/></td>
  			</tr>
	  		<tr>
	  			<th>性别</th>
	  			<c:if test="${resident.gender==0}">
	  				<td>男<input name="gender"  type="radio" value="0" checked="checked" />女<input name="gender"  type="radio" value="1" /></td>
		     	</c:if>
	     		<c:if test="${resident.gender==1}">
	  				<td>男<input name="gender"  type="radio" value="0" />女<input name="gender"  type="radio" value="1"  checked="checked"/></td>
	     		</c:if> 
	  		</tr>
	  		<tr>
	  			<th>年龄</th>
	  			<td><input name="age"  value="${resident.age }"  datatype="n" errormsg="请输入正确的年龄!" nullmsg="年龄不能为空!"/></td>
	  		</tr>
	  		<tr>
	   			<th>身份证号</th>
	  			<td><input name="cid"   value="${resident.cid }" datatype="n16-20" errormsg="请输入16至20位数字的身份证号" nullmsg="身份证号不能为空!"/></td>
	  		</tr>
	  		<tr>
	  			<th>学历</th>
	  			<td>
		  			<c:if test="${resident.edu==1}">
			  			<select name="edu"  id="edu"  datatype="*" >
			  			    <option value="1" selected="selected">专科以下</option>
			  			    <option value="2">专科</option>
						    <option value="3" >本科</option>
							<option value="4">硕士</option>
							<option value="5">硕士以上</option>
						</select>
					</c:if>
					<c:if test="${resident.edu==2}">
			  			<select name="edu"  id="edu" >
			  			    <option value="1" >专科以下</option>
			  			    <option value="2" selected="selected">专科</option>
						    <option value="3" >本科</option>
							<option value="4">硕士</option>
							<option value="5">硕士以上</option>
						</select>
					</c:if>
					<c:if test="${resident.edu==3}">
			  			<select name="edu"  id="edu"  >
			  			    <!--<option value="1" selected="selected">专科以下</option>-->
			  			    <option value="1" >专科以下</option>
			  			    <option value="2">专科</option>
						    <option value="3"  selected="selected">本科</option>
							<option value="4">硕士</option>
							<option value="5">硕士以上</option>
						</select>
					</c:if>
					<c:if test="${resident.edu==4}">
			  			<select name="edu"  id="edu"  >
			  				<!--<option value="1" selected="selected">专科以下</option>-->
			  			    <option value="1" >专科以下</option>
			  			    <option value="2">专科</option>
						    <option value="3" >本科</option>
							<option value="4"  selected="selected">硕士</option>
							<option value="5">硕士以上</option>
						</select>
					</c:if>
					<c:if test="${resident.edu==5}">
			  			<select name="edu"  id="edu"  >
			  			    <!--<option value="1" selected="selected">专科以下</option>-->
			  			    <option value="1" >专科以下</option>
			  			    <option value="2">专科</option>
						    <option value="3" >本科</option>
							<option value="4">硕士</option>
							<option value="5"  selected="selected">硕士以上</option>
						</select>
					</c:if>
				</td>	
	  		</tr>
	  		<tr>
	  			<th>邮箱</th>
	  			<td><input name="email"   value="${resident.email }"  datatype="e" errormsg="请输入正确的邮箱地址!" nullmsg="邮箱地址不能为空!"/></td>
	  		</tr>
			<tr>
	  			<th>所租房间</th>
	  			<td>
		  			<select name="rent1"  id="rent1" >
					    <option value="一号楼">一号楼</option>
						<option value="二号楼">二号楼</option>
						<option value="三号楼">三号楼</option>
					</select>
					<select name="rent2"  id="rent2" >
					    <option value="一单元">一单元</option>
						<option value="二单元">二单元</option>
						<option value="二单元">二单元</option>
					</select>
					<select name="rent3"  id="rent3" >
					    <option value="1001室">1001室</option>
						<option value="1002室">1002室</option>
						<option value="1003室">1003室</option>
						<option value="1004室">1004室</option>
						<option value="1005室">1005室</option>
					</select>
				</td>	
	  		</tr>
	  		<tr>
	  			<th>手机号</th>
	  			<td><input name="phone"   value="${resident.phone }"  datatype="m" errormsg="请输入正确的手机号" nullmsg="手机号不能为空"/></td>
	  		</tr>
	  		<tr>
	  			<th>合同开始日期</th>
	  			<td><input id="d14" name="bdate" type="text"  value="${resident.bdate }" datatype="*"  /><img onclick="WdatePicker({el:'d14'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td> 	
	  		</tr>
	  		<tr>
	  			<th>合同结束日期</th>
	  			<td><input id="d15" name="edate" type="text"  value="${resident.edate }"  datatype="*"  /><img onclick="WdatePicker({el:'d15'})" src="My97DatePicker/skin/datePicker.gif" width="16" height="22" align="absmiddle"></td> 	
	  		</tr>
	  		<tr>
	  			<th>住户照片</th>
	  			<td><input type="file" name="photoname" value="${resident.photoname }"/></td>
	  		</tr>
			<tr></tr>
	  		<tr></tr>
	  		<tr></tr>
	  		<tr></tr>
	  		<tr></tr>
	  		<tr></tr>
	  		<tr></tr>
	  		<tr>
	  			<td>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			<input type="submit" value="提交" />
	  			</td>
	  			<td>
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
		  			&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
	  				<input type="reset" value="重置" />
	  			</td>
	  		</tr>	
	    </table>
  	</form>
  </body>
</html>
