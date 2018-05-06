<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/activeMQ/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<script type="text/javascript">

$(function() {
	$("#querybut").click(check);
});

/* 查询用户信息 */
function check() {
$.ajax({
       type  : 'post',
	   url   : '/activeMQ/ssm/getUsersData.action', 
       datatype : "json", 
	   success: function(data) {
		   if(data == "") {
			   alert("系统异常");
		   } else {
			   var jsonData = eval('(' + data + ')');
			   var headTr ="<tr><td width=100>用户id</td>"+
			   				"<td width=100>用户名</td></tr>";
			   for(var i=0;i<jsonData.length;i++) {
				   var userId = jsonData[i].user_id;
				   var userName = jsonData[i].user_name;
				   var tr = "<tr>";
				   tr+="<td width=100>";
				   tr+=userId;
				   tr+="</td><td width=100>";
				   tr+=userName;
				   tr+="</td>";
				   tr+="<td width=100><a href='toShopPage.action?userId="+userId+"'>进入商城</a></td><tr/>";
				   headTr+=tr;
			   }
			   $("#ssmTable").html(headTr);
	    	}
		}
		   
    }) ;
}
	
</script>
</head>
<body>

<input type="button" value="查询" id="querybut"/>

<table border="1" id="ssmTable">
	<tr>
		<td width=100>用户id</td>
		<td width=100>用户名</td>
	</tr>
</table>



</body>
</html>