<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
	String msg = (String)request.getAttribute("msg");
	if(msg == null)
		msg = "";
%>

<!DOCTYPE html>
<html>
<head>
	<title>Poi</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./resources/css/universal.css">
	<script>
		function verifyName()
		{
			var input = document.getElementsByName("animeName")[0];
			var animeName = input.value;
			var msg = document.getElementById("msg");
			if(value == null)
			{
				msg.style.color = "red";
				msg.innerHTML = "动漫名不能为空";
			}
		}
	</script>
</head>

<body>
	<a style="text-decoration: none;" href="index.jsp">home</a>
	<form action="animeAdd" method="post">
		动漫名：<input name="animeName" type="text" onblur="verifyName()">
		<label id="msg"></label>
		<button>添加动漫</button>
	</form>
</body>
</html>