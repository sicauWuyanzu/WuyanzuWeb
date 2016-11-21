<%@ page language="java" contentType="text/html; charset=GBK" pageEncoding="UTF-8"%>
<html lang="zh">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>登录界面</title>

<link rel="stylesheet" type="text/css" href="css/styles.css">
<style type="text/css">
body,td,th { font-family: "Source Sans Pro", sans-serif; }
body { background-color: #2B2B2B; }
</style>
</head>
<body>

<span style="Text-align:center;color:red; font-weight:bold">

<%
  if(request.getAttribute("err")!=null){
    out.println(request.getAttribute("err") + "<br/>");
  }
%>

</span>



<div class="wrapper">

	<div class="container">
		<h1>Welcome</h1>
		<form class="form" action="/login">
			<input type="text" placeholder="Username">
			<input type="password" placeholder="Password">
			<input type="text" placeholder="StudentId">
			<button type="submit" id="login-button">登录</button>
		</form>
	</div>

	
	<ul class="bg-bubbles">
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
		<li></li>
	</ul>
	
</div>
<div class="container1">
	<form class="form" action="register.jsp">

		<button type="submit" >注册</button>
	</form>
</div>
<script type="text/javascript" src="WEB-INF/js/jquery-2.1.1.min.js"></script>
<script type="text/javascript">
$('#login-button').click(function(event){
	event.preventDefault();
	$('form').fadeOut(500);
	$('.wrapper').addClass('form-success');
});
</script>

</body>
</html>