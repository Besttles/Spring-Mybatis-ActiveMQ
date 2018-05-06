<?xml version="1.0" encoding="utf-8" ?>
<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<script src="/logistics/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>物流详细信息</title>
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
var id = '${orderId}';


$(function(){
	
	$("#updateBut").click(updateBut);
	$("#logisticsId").val(id);
	$.ajax({
		type : 'post',
		data : {logisticsId : $("#logisticsId").val()},
		url : '/logistics/logistics/findByIdLogisData.action',
		success : function(data){
			var jsonData = eval( '(' + data + ')' );
			var msg = "";
			for(var i=0; i<jsonData.length; i++){
				var logisticsUpdateTime = toDateFound(jsonData[i].logistics_update_time.time);
				var logisticsDepict = jsonData[i].logistics_depict;
				msg += logisticsUpdateTime + '   ' + logisticsDepict + '\r';
			}
			$("#logisDepict").val(msg);
		}
	});
});

function check(){
	
}

function updateBut(){
	window.location.href = '/logistics/logistics/logis.action';
}
</script>
</head>
<body>
	订单号：<input type="text" id="logisticsId" disabled="disabled" /><br/>
	物流详细信息：<br/><textarea id="logisDepict" rows="10" cols="100" ></textarea><br/>
	<input type="button" value="返回" id="updateBut"/>
</body>
</html>