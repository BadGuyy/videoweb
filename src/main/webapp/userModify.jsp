<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
	String account = (String)request.getAttribute("account");
	if(account == null)
        account = "";

	String password = (String)request.getAttribute("password");
	if(password == null)
        password = "";
        
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
</head>

<body>
	<a style="text-decoration: none;" href="index.jsp">home</a>
    账号：<label><%=account%></label><br>
    密码：
    <form action="userPassWordModify">
        <input name="password" type="text" value=<%=password%>>
        <input type="submit" value="更改密码">
    </form><br>
    头像：
    <form action="userImageModify" method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传头像">
        <label><%=msg%></label>
    </form><br>
    <form action="userDelete" method="post">
        <input style="color: red;" type="submit" value="注销账户">
    </form>
</body>
</html>