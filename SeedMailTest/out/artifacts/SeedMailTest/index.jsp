<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<script type="text/javascript">
    function seedEmail(){
    	var email = document.getElementById("getEmail").value;
		window.location.href='http://172.19.224.126/SeedMailTest/email?email=' + email;
	}
</script>
</head>
<body>
	<div align="center" style="margin:300px auto">
		邮箱：<input id="getEmail" type="text"><br>
		<button onclick="seedEmail()">发送</button>
	</div>
</body>
</html>