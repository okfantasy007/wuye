<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ page import="com.cdsxt.resident.vo.*"%>
<%@ page import="util.DB"%>
<%@ page import="util.Page"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<title>无标题文档</title>
		
		<style type="text/css">
		<!--
		body {
			margin-left: 0px;
			margin-top: 0px;
			margin-right: 0px;
			margin-bottom: 0px;
		}
		.STYLE1 {font-size: 12px}
		.STYLE4 {
			font-size: 12px;
			color: #1F4A65;
			font-weight: bold;
		}
		
		a:link {
			font-size: 12px;
			color: #06482a;
			text-decoration: none;
		
		}
		a:visited {
			font-size: 12px;
			color: #06482a;
			text-decoration: none;
		}
		a:hover {
			font-size: 12px;
			color: #FF0000;
			text-decoration: underline;
		}
		a:active {
			font-size: 12px;
			color: #FF0000;
			text-decoration: none;
		}
		-->
		</style>
		<script type="text/javascript" src="wbox/jquery1.4.2.js"  ></script>
		<script type="text/javascript" src="wbox/wbox-min.js"  ></script>
		<link rel="stylesheet" type="text/css" href="wbox/wbox/wbox-min.css">
		<script src="js/jquery-1.4.4.min.js"></script>
		<script src="js/iframe.js"></script>
		
		<script >
			function delSelect(){
				var nodes = document.getElementsByName("select");
				for ( var i = 0; i < nodes.length; i++) {
					nodes[i].checked = !nodes[i].checked;
				}
			}
			
			function del(){
				var nodes = document.getElementsByName("select");
				var ids = "";
				for ( var i = 0; i < nodes.length; i++) {
					if (nodes[i].checked) {
						ids += nodes[i].value+";";
					}
				}
				window.location.href = "ResidentServlet?opt=deleteAll&ids="+ids;
			}
			
			function add(){
				window.location.href = "jsp/resident/addResident.jsp";
			}
			
			function $(id){
				return document.getElementById(id);
			}
			
     		var box;
		    function synInfo(){
				box = $().wBox({
							 requestType: "iframe",
							 iframeWH:{width:500,height:260},
							 target:"jsp/resident/addResident.jsp"
							});
						 box.showBox();
			}	
			
			function closeAndRefresh(){
					
				box.close();
				setTimeout(function(){
						window.location = window.location;//刷新父页面
				}, 2000);
			}
		</script>
	</head>
<body>
        <table width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
        <tr>
	        <td colspan="2">
	        	<button onclick="add();" class="add">添加</button>
		    </td>
		     <td colspan="2">
		       	<button onclick="del();" class="edit">删除</button>
		    </td>
	        <td colspan="11" style="text-align: right">
		        <form action="ResidentServlet"  method="post" >
						<input  type="hidden" name="opt" value="search"  />	
			       	 	<label>栏目：</label>
								<select name="category"  id="category" >
								    <option value="0">===查找方式===</option>
									<option value="1">按姓名查找</option>
									<option value="2">按身份证号查找</option>
									<option value="3">按学历查找</option>
									<option value="4">按楼栋/单元/房间号查找</option>
								</select>&nbsp;
						<label>关键词：</label>
						<input name="key" type="text" class="text"  size="15">
						<button>查询</button>
				</form>
	        </td>
   		</tr>
        <tr>
          <td width="5.8%" height="26" background="../images/tab_14.gif" class="STYLE1">
	               	<div align="center">全选<input id="delSe" type="checkbox" onclick="delSelect();" /></div>   
          </td>
          <td width="3.1%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
          <td width="4.8%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">住户名</div></td>
          <td width="3.9%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">性别</div></td>
          <td width="3.9%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">年龄</div></td>
          <td width="12.5%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">身份证号</div></td>
          <td width="3.8%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">学历</div></td>
          <td width="9.6%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">邮箱</div></td>
          <td width="12.5%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">所租房子</div></td>
          <td width="9.6%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">手机号</div></td>
          <td width="9.6%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">合同开始日期</div></td>
          <td width="9.6%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">合同结束日期</div></td>
          <td width="2.5%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">照片名</div></td>
          <td width="2.6%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">照片相对地址</div></td>
          <td width="3.1%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">修改</div></td>
          <td width="3.1%" height="18" background="../images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td> 
        </tr>  
		<c:forEach items="${page.list}"  var="resident" varStatus="i" >
	      	<tr>
	            <td align="center">&nbsp;<input value="${resident.id }" name="select" type="checkbox" /></td>
	            <td height="18" bgcolor="#FFFFFF" class="STYLE2"><div align="center" class="STYLE2 STYLE1">${i.count+(page.num-1)*page.pageSize }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1"><a href="ResidentServlet?opt=showMsg&id=${resident.id }">${resident.rname }</a></div></td>
	            <td height="18" bgcolor="#FFFFFF">
		            <div align="center" class="STYLE2 STYLE1">
			            <c:if test="${resident.gender==0}">男</c:if>
			            <c:if test="${resident.gender==1}">女</c:if>
	            	</div>
	            </td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.age }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.cid }</div></td>
	            <td height="18" bgcolor="#FFFFFF">
		            <div align="center" class="STYLE2 STYLE1">
		            	<c:if test="${resident.edu==1}">专科以下</c:if>
		            	<c:if test="${resident.edu==2}">专科</c:if>
		            	<c:if test="${resident.edu==3}">本科</c:if>
		            	<c:if test="${resident.edu==4}">硕士</c:if>
		            	<c:if test="${resident.edu==5}">硕士以上</c:if>
	            	</div>
            	</td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.email }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.rent }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.phone }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.bdate }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.edate }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.photoname }</div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${resident.rltprepath }</div></td>    
	            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE1"> </span><a href="ResidentServlet?opt=updatebefore&id=${resident.id}">修改</a><span class="STYLE1"></span></div></td>
	            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"> </span><span class="STYLE1"></span><a href="ResidentServlet?opt=del&id=${resident.id }">删除</a><span class="STYLE1"></span></div></td>
	         </tr>    
        </c:forEach>
        <tr> 
	        <td colspan="10" style="text-align: left">&nbsp;&nbsp;&nbsp;共有${page.rowCount } 条数据，当前第 ${page.num } 页</td>
	        <td colspan="5" style="text-align: right">
					<c:if test="${page.num != 1}">
						<a href="ResidentServlet?opt=findByPage&num=${page.first}" class="pg_index">首页</a>&nbsp;<a href="ResidentServlet?opt=findByPage&num=${page.pre}" class="pg_next">上一页</a>
					</c:if>
					<c:forEach begin="${page.begin}" end="${page.end}" varStatus="i" var="item" >
					     <a href="ResidentServlet?opt=findByPage&num=${item}">${item }</a>
					</c:forEach>
					<c:if test="${page.num != page.last}">
					     <a href="ResidentServlet?opt=findByPage&num=${page.next}" class="pg_next">下一页</a>&nbsp;<a href="ResidentServlet?opt=findByPage&num=${page.last}" class="pg_last">尾页</a>
					</c:if>
			</td> 
		</tr> 
    </table>	
</body>
</html>
