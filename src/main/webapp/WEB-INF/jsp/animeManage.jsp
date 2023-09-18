<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ page import="entity.Anime, java.util.ArrayList"%>
<%
	ArrayList<Anime> animes = (ArrayList<Anime>)request.getAttribute("animes");

	Integer currentPage = (Integer)request.getAttribute("currentPage"), 
		numOfPages = (Integer)request.getAttribute("numOfPages");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Poi</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./resources/css/universal.css">
    <style>
        img {
            width: 400px;
            height: 400px;
        }
    </style>
</head>

<body>
	<a style="text-decoration: none;" href="index.jsp">home</a>
    <a style="text-decoration: none;" href="jumpAnimeAdd">添加动漫</a>
    <div id="content">
		<table border="0" cellspacing="0">
			<% for(int i = 0; i < animes.size(); ++i) { %>
				<form method="post">
					<tr>
						<td><img src="/video/<%=animes.get(i).getName()%>/cover.png?t=<%=Math.random()%>"></td>
						<td>
                            <input name="animeName" type="text" value="<%= animes.get(i).getName() %>"><br>
                            <button formaction="animeModify">修改</button><br>
                            <button formaction="animeDelete">删除</button><br>
                        </td>
					</tr>
				</form>
			<% } %>
		</table>
		<form>
			<input type="submit" formaction="animeManagePageUp" formmethod="post" value="上一页">
			<label><%=currentPage%>/<%=numOfPages%></label>
			<input type="submit" formaction="animeManagePageDown" formmethod="post" value="下一页">
		</form>
	</div>
</body>
</html>