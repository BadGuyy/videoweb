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
	<link rel="stylesheet" href="./resources/css/index.css">
</head>

<body>
	<div id="content">
		<div class="box">
			<h2>Login</h2>
			<form>
				<div class="input-box">
					<label>账号</label>
					<input type="text" name="account"/>
				</div>
				<div class="input-box">
					<label>密码</label>
					<input type="password" name="password"/>
					<label style="color: red;"><%=msg%></label>
				</div>
				<div class="btn-box">
					<div>
						<button formaction="managerLogin" formmethod="post">登录</button>
					</div>
				</div>
			</form>
	</div>
</body>
</html>