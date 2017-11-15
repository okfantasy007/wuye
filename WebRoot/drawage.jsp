<%@ page language="java" import="java.util.*" pageEncoding="GB18030"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
    
    <title>My JSP 'getJson.jsp' starting page</title>
    <script type="text/javascript" src="Charts/FusionCharts.js"></script>
  </head>
  
  <body>
  <form action="ResidentServlet?opt=drawage"  method="post" > 
    <div id="chartContainer">FusionCharts XT will load here!</div>          
    <script type="text/javascript">      

      var myChart = new FusionCharts( "Charts/Column3D.swf","myChartId", "500", "400", "0" );
      myChart.setJSONData({ 
	        "chart": 
	        { 
	           	"caption" : "年龄段分析" ,    
	           	"xAxisName" : "年龄段", 
	           	"yAxisName" : "人数",  
	           	"numberPrefix" : "" 
	        },
	
	     	"data": 
	        	${drawage}
			});
      
     //myChart.setJSONUrl("Data.json");
      myChart.render("chartContainer");
      
    </script>  
  </body>
</html>
