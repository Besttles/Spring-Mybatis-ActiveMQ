<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="/logistics/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流系统</title>
<script type="text/javascript">

/* 时间类型转换 */
function toDateFound(date) {
	var data = new Date(date);
	var Y = data.getFullYear();
	var m = data.getMonth()+1;
	var D = data.getDate();
	var H = data.getHours();
	var M = data.getMinutes();
	var S = data.getSeconds();
	return Y+"-"+m+"-"+D+" "+H+":"+M+":"+S;
}

$(function(){
	$("#querybut").click(check);
	save();
	
});

/* 先从MQ上消费 */
function save(){
	$.ajax({
		type : 'post',
		url : '/logistics/activeMQ/receiveQueue.action',
		success : function(){
		},
		error : function(){
			
		}
	});
}



/* 查询订单信息 */
function check(){
	$.ajax({
       type  : 'post',
	   url   : '/logistics/logistics/findLogisOrder.action', 
	   success: function(data) {
		   if(data == "") {
			   alert("系统异常")
		   } else {
			   var jsonData = eval('(' + data + ')');
			   var headTr ="<tr><td width=100>订单号</td>"+
			   				"<td width=100>订单创建时间</td>"+
			   				"<td width=100>提交人ID</td>"+
			   				"<td width=100>订单数量</td>"+
			   				"<td width=100>订单金额</td>"+
			   				"<td width=100>物流状态描述</td>"+
			   				"<td width=50>操作</td>"+
			   				"<td width=100>物流信息详情</td></tr>";
			   for(var i=0;i<jsonData.length;i++) {
				   var orderId = jsonData[i].order_id;
				   var createTime = toDateFound(jsonData[i].create_time.time);
				   var submitUserId = jsonData[i].submit_user_id;
				   var submitNum = jsonData[i].order_num;
				   var orderAmount = jsonData[i].order_amount;
				   var orderStatus = jsonData[i].order_status;
				   var orderDepict = jsonData[i].order_depict;
				   var tr = "<tr>";
				   tr+="<td width=100>";
				   tr+=orderId;
				   tr+="</td><td width=100>";
				   tr+=createTime;
				   tr+="</td><td width=100>";
				   tr+=submitUserId;
				   tr+="</td><td width=100>";
				   tr+=submitNum;
				   tr+="</td><td width=100>";
				   tr+=orderAmount;
				   tr+="</td><td width=100>";
				   tr+=orderDepict;
				   tr+="</td><td width=50><input type='button' value='修改' onclick='updateLogis("+orderId+")' /></td>";
				   tr+="<td width=100><input type='button' value='物流信息详情' onclick='logisDepict("+orderId+")' /></td><tr/>";
				   headTr+=tr;
			   }
			   $("#logisTable").html(headTr);
		   }
    	}
    }) ;
}

/* 修改页面 */
function updateLogis(orderId){
	window.location.href = '/logistics/logistics/updateLogis.action?orderId='+orderId;
}

/* 物流详情页面 */
function logisDepict(orderId){
	window.location.href = '/logistics/logistics/logisticsDepictPage.action?orderId='+orderId;
}


</script>
</head>
<body>
	<input type="button" value="查询" id="querybut"/>

	<table border="1" id="logisTable">
		<tr>
			<td width=100>订单号</td>
			<td width=100>订单创建时间</td>
			<td width=100>提交人ID</td>
			<td width=100>订单数量</td>
			<td width=100>订单金额</td>
			<td width=100>物流状态描述</td>
			<td width=50>操作</td>
			<td width=100>物流信息详情</td>
		</tr>
	</table>
</body>
</html>