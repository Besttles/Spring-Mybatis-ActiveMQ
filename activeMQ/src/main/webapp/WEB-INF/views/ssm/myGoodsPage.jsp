<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/activeMQ/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
<script type="text/javascript">
var userId = '${userId}';
/* 时间类型转换 */
function toDateFound(date) {
	var data = new Date(date);
	var Y = data.getFullYear();
	var m = data.getMonth()+1;
	var D = data.getDate();
	var H = data.getHours();
	var M = data.getMinutes();
	var S = data.getSeconds();
	return Y+"-"+m+"-"+D+" "+H+"-"+M+"-"+S;
}

$(function() {
	$("#sort").click(sort);
	$("#reGoods").click(reGoods);
});



/* 返回商城 */
function reGoods() {
	window.location.href = '/activeMQ/ssm/toShopPage.action?userId='+userId;
}

/* 查询用户信息 */
function sort() {
	$.ajax({
		type  : 'post',
		data : {submitUserId : userId, sort : $("#sort").val()},
		url : '/activeMQ/ssm/getMyOrdersData.action', 
		datatype : "json", 
		success: function(data) {
			if(data == "") {
				alert("系统异常");
			} else {
				var jsonData = eval('(' + data + ')');
				var headTr ="<tr><td width=100>订单号</td>"+
				"<td width=100>下单时间</td>"+
				"<td width=100>订单状态</td>"+
				"<td width=100>订单金额</td></tr>";
				for(var i=0;i<jsonData.length;i++) {
					var orderId = jsonData[i].order_id;
					var createTime = toDateFound(jsonData[i].create_time.time)
					var orderStatus;
					if(jsonData[i].order_status == 1){
						orderStatus = "待发货";
					}
					if(jsonData[i].order_status == 2){
						orderStatus = "已发货";
					}
					if(jsonData[i].order_status == 3){
						orderStatus = "已收货";
					}
					var orderAmount = jsonData[i].order_amount;
					
					var tr = "<tr>";
					tr+="<td width=300>";
					tr+=orderId;
					tr+="</td><td width=300>";
					tr+=createTime;
					tr+="</td><td width=300>";
					tr+=orderStatus;
					tr+="</td><td width=300>";
					tr+=orderAmount;
					tr+="</td>";
					tr+="<td width=50><input type='button' value='删除' onclick='del("+orderId+")' /></td><tr/>";
					headTr+=tr;
				}
				$("#ssmTable").html(headTr);
			}	
		}	   
	});
}
/* 逻辑删除我的订单信息 */
function del(orderId){
	var url = '/activeMQ/ssm/delOrdersData.action';
	var param = {orderId : orderId};
	$.post(url, param, function(){
		sort();
	});
	
}

/* 查询提交MQ信息 */
/* function check(userId){
	$.ajax({
		type  : 'post',
		data : {userId : userId},
		url : '/activeMQ/ssm/getSubMQData.action', 
		datatype : "json", 
		success : function(data) {
			var jsonData = eval('(' + data + ')');
			//orderId订单号 createTime创建时间  orderStatus发货状态 orderAmount金额
			var message="";
			var orderId,createTime,orderStatus,orderAmount;
			for(var i=0;i<jsonData.length;i++) {
				orderId = jsonData[i].order_id;
				createTime = toDateFound(jsonData[i].create_time.time)
				if(jsonData[i].order_status == 1){
					orderStatus = "待发货";
				}
				if(jsonData[i].order_status == 2){
					orderStatus = "已发货";
				}
				if(jsonData[i].order_status == 3){
					orderStatus = "已收货";
				}
				orderAmount = jsonData[i].order_amount;
				message = orderId+";"+createTime+";"+userId+";"+orderStatus+";"+orderAmount;
			}
			$.ajax({
				type  : 'post',
				data : {
					"message" : message
				},
				url : '/activeMQ/activeMQ/toSendMessage.action', 
				datatype : "json", 
				success : function(){
					alert("已提交");
				},
				error : function(){
					alert("异常");
				}
			});
		}
	}); 
}	*/
	

</script>
</head>
<body>

	<select id="sort">
		<option value="desc">降序</option>
		<option value="asc">升序</option>
	</select>
	
	<input type="button" value="返回商城" id="reGoods"/>

	<table border="1" id="ssmTable">
		<tr>
			<td width=300>订单号</td>
			<td width=300>下单时间</td>
			<td width=300>订单状态</td>
			<td width=300>订单金额</td>
		</tr>
	</table>



</body>
</html>