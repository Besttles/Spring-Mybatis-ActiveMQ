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
var orderId = '${orderId}';

$.ajax({
	type : 'post',
	data : {orderId : orderId},
	url : '/logistics/logistics/findById.action',
	success : function(data){
		var jsonData = eval( '(' +data +')' );
		var orderDepict;
		for(var i=0;i<jsonData.length;i++) {
			orderDepict = jsonData[i].order_depict;
		}
		$("#logisDepict").val(orderDepict);
	},
	error : function(){
		alert("获取订单信息异常")
	}
});

$(function(){
	
	$("#updateBut").click(updateLogisDeptic);
});

function updateLogisDeptic(){
	$.ajax({
		type : 'post',
		data : {orderId : orderId, orderDepict : $("#logisDepict").val()},
		url : '/logistics/logistics/findByIdUpdateLogisDep.action',
		success : function(){
			$.ajax({
				type : 'post',
				data : {logisticsId : orderId, logisticsDepict : $("#logisDepict").val()},
				url : '/logistics/logistics/saveLogisticsDepict.action',
				success : function(){
					alert("修改成功");
				}
			});
			
			window.location.href = '/logistics/logistics/logis.action';
		},
		error : function(){
			alert("修改异常")
		}
	});
}

</script>
</head>
<body>
	订单号：<input type="text" value="${orderId}" disabled="disabled" /><br/>
	物流信息：<input type="text" required="required" id="logisDepict" /><br/>
	<input type="button" value="修改" id="updateBut"/>
</body>
</html>