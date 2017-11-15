<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <base href="<%=basePath%>">
<title>无标题文档</title>
<script type="text/javascript" src="wbox/jquery1.4.2.js"  ></script>
    <script type="text/javascript" src="wbox/wbox-min.js"  ></script>
    <link rel="stylesheet" type="text/css" href="wbox/wbox/wbox-min.css">
	
	<script type="text/javascript">
			var box;
    		function synInfo(){

				 box = $().wBox({
					 requestType: "iframe",
					 iframeWH:{width:500,height:260},
					 target:'jsp/emp/son.jsp'
					});
				 box.showBox();
			}
			/*
			function closeAndRefresh(){
				
				box.close();
				setTimeout(function(){
					window.location = window.location;//刷新父页面
				}, 2000);
			}*/
			
			
	
	</script>
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
</head>

<body>
<table  width="100%" border="0" align="center" cellpadding="0" cellspacing="0">
  <tr>
    <td height="30"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="30"><img src="jsp/images/tab_03.gif" width="15" height="30" /></td>
        <td background="jsp/images/tab_05.gif"><img src="jsp/images/311.gif" width="16" height="16" /> <span class="STYLE4">员工信息列表&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</span><a onclick="synInfo();">删除选中行</a></td>
        <td width="14"><img src="jsp/images/tab_07.gif" width="14" height="30" /></td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td><table  width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="9" background="jsp/images/tab_12.gif">&nbsp;</td>
        <td bgcolor="e5f1d6"><table class="tab"  width="99%" border="0" align="center" cellpadding="0" cellspacing="1" bgcolor="#CECECE">
          <tr>
            <td width="6%" height="26" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">选择</div></td>
            <td width="6%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">序号</div></td>
            <td width="8%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">工号</div></td>
            <td width="6%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">姓名</div></td>
            <td width="12%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">身份证号</div></td>
            <td width="10%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">性别</div></td>
            <td width="14%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2 STYLE1">联系方式</div></td>
            <td width="12%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">部门</div></td>
            <td width="12%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">入职时间</div></td>
            <td width="7%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">编辑</div></td>
            <td width="7%" height="18" background="jsp/images/tab_14.gif" class="STYLE1"><div align="center" class="STYLE2">删除</div></td>
          </tr>
          	<c:forEach items="${emp}" var="e" varStatus="i">
          <tr>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE1">
              <input name="checkbox" type="checkbox" class="STYLE2" value="checkbox" />
            </div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${i.count}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.eid}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.name}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.ino}</div></td>
            <c:choose>
            <c:when test="${e.gender==1}">
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">男</div></td>
            </c:when>
            <c:otherwise>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">女</div></td>
            </c:otherwise>
            </c:choose>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.phone}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.dept}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center" class="STYLE2 STYLE1">${e.hirdate}</div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><img src="jsp/images/037.gif" width="9" height="9" /><span class="STYLE1"> [</span><a href="#">编辑</a><span class="STYLE1">]</span></div></td>
            <td height="18" bgcolor="#FFFFFF"><div align="center"><span class="STYLE2"><img src="jsp/images/010.gif" width="9" height="9" /> </span><span class="STYLE1">[</span><a href="#">删除</a><span class="STYLE1">]</span></div></td>
          </tr>
         </c:forEach>
        </table></td>
        <td width="9" background="jsp/images/tab_16.gif">&nbsp;</td>
      </tr>
    </table></td>
  </tr>
  <tr>
    <td height="29"><table width="100%" border="0" cellspacing="0" cellpadding="0">
      <tr>
        <td width="15" height="29"><img src="jsp/images/tab_20.gif" width="15" height="29" /></td>
        <td background="jsp/images/tab_21.gif"><table width="100%" border="0" cellspacing="0" cellpadding="0">
          <tr>
            <td width="40%"><div align="left"><span class="STYLE1">共120条纪录，当前第1/10页，每页10条纪录</span></div></td>
            <td width="60%" class="STYLE1">&nbsp;</td>
          </tr>
        </table></td>
        <td width="14"><img src="jsp/images/tab_22.gif" width="14" height="29" /></td>
      </tr>
    </table></td>
  </tr>
</table>
</body>
</html>

