<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<script src="/activeMQ/js/jquery-1.8.3.min.js" type="text/javascript"></script>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>订单管理</title>
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
	return Y+"-"+m+"-"+D+" "+H+"-"+M+"-"+S;
}
$(function(){
	$("#querybut").click(check);
	$("#myOrder").click(myOrder);
});

/* 查询我的订单信息 */
function myOrder() {
	window.location.href = '/activeMQ/ssm/myGoodsPage.action?userId='+userId;
}

/* 查询商品信息 */
function check(){
$.ajax({
       type  : 'post',
	   url   : '/activeMQ/ssm/getOrdersData.action', 
       datatype : "json", 
	   success: function(data) {
		   if(data == "") {
			   alert("系统异常")
		   } else {
			   var jsonData = eval('(' + data + ')');
			   var headTr ="<tr><td width=100>商品名称</td>"+
			   				"<td width=100>商品价格</td>"+
			   				"<td width=100>商品描述</td></tr>";
			   for(var i=0;i<jsonData.length;i++) {
				   var goodsName = jsonData[i].goods_name;
				   var price = jsonData[i].price;
				   var goodsDesc = jsonData[i].goods_desc;
				   var tr = "<tr>";
				   tr+="<td width=100>";
				   tr+=goodsName;
				   tr+="</td><td width=100>";
				   tr+=price;
				   tr+="</td><td width=100>";
				   tr+=goodsDesc;
				   tr+="</td><td width=100><input type='button' value='购买' onclick='toBuyPage("+price+")' /></td><tr/>";
				   headTr+=tr;
			   }
			   $("#ssmTable").html(headTr);
		   }
    	}
    }) ;
}

/* 购买 */
function toBuyPage(price) {
	var num = prompt("请输入购买数量","1");
	var amount = num * price;
	if (num!=null && num!="") { 
	  $.ajax({
		  type : 'post',
		  data : {amount : amount,userId : userId},
		  url : '/activeMQ/ssm/getBuyData.action',
		  datatype : 'json',
		  success : function(data){
			  var jsonData = eval('('+data+')');
			  var orderId = jsonData[0].orderId;
			  var createTime = toDateFound(jsonData[0].createTime.time);
			  var submitUserId = jsonData[0].submitUserId;
			  var orderAmount = jsonData[0].orderAmount;
			  var orderStatus;
			  if(jsonData[0].orderStatus == 1){
					orderStatus = "待发货";
				}
				if(jsonData[0].orderStatus == 2){
					orderStatus = "已发货";
				}
				if(jsonData[0].orderStatus == 3){
					orderStatus = "已收货";
				}
			  var message = orderId+';'+createTime+';'+submitUserId+';'+num+';'+orderAmount+';'+orderStatus;
			  /* 向MQ异步发送订单相关信息 */
			  $.ajax({
				  type : 'post',
				  data : {message : message},
				  url : '/activeMQ/activeMQ/toSendMessage.action',
				  datatype : 'json',
				  success : function(){
					  alert("订单提交成功");
					  check();
				  }
			  });
			  
		  },
		  error : function(){
			  alert("提交异常");
		  }
	  });
	} 
}

var userId = '${userId}';
</script>
</head>
<body>

<input type="button" value="查询" id="querybut"/>&nbsp;&nbsp;
<input type="button" value="我的订单" id="myOrder"/>

<table border="1" id="ssmTable">
	<tr>
		<td width=100>商品名称</td>
		<td width=100>商品价格</td>
		<td width=100>商品描述</td>
	</tr>
</table>



</body>
</html>