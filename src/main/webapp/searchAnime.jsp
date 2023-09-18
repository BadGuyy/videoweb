<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ page import="entity.Anime, java.util.ArrayList"%>
<%
	ArrayList<Anime> animes = (ArrayList<Anime>)request.getAttribute("animes");
	String icon = (String)request.getAttribute("icon");

	Integer currentPage = (Integer)request.getAttribute("currentPage"), 
		numOfPages = (Integer)request.getAttribute("numOfPages");
	if(currentPage == null)
		currentPage = 1;

	String iconpath = "/image/profile/" + icon;
%>

<!DOCTYPE html>
<html>
<head>
	<title>Poi</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./resources/css/universal.css">
	<link rel="stylesheet" href="./resources/css/home.css">
	<link rel="stylesheet" href="./resources/iconfont.css">
	<style>
		tr {
			display: inline;
		}
		img {
			width: 400px;
			height: 400px;
		}
	</style>
</head>

<body>
	<!-- 导航栏 -->
	<div id="top-bar">
		<div style="display: inline-block;">
			<form action="userModify">
				<img style="width: 17px; height: 17px;" src=<%=iconpath%>>
				<button style="color: white;">修改信息</button>
			</form>
		</div>
		<form id="search" action="searchAnime">
			<input name="anime" type="text">
			<button class="iconfont icon-fangdajing searchlink"></button>
		</form>
	</div>

	<div id="content">
		<table border="0" cellspacing="0">
			<% for(int i = 0; i < animes.size(); ++i) { %>
				<form action="animeHome" method="post">
					<tr>
						<td><img src=<%= "/video/" + animes.get(i).getName() + "/cover.png" %>></td>
						<input name="animeString" type="text" value="<%= animes.get(i).getName() %>">
						<input type="submit" value="观看">
					</tr>
				</form>
			<% } %>
		</table>
		<form>
			<input type="submit" formaction="homePageUp" formmethod="post" value="上一页">
			<label><%=currentPage%>/<%=numOfPages%></label>
			<input type="submit" formaction="homePageDown" formmethod="post" value="下一页">
		</form>
	</div>
</body>
</html>