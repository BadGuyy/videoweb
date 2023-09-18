<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ page import="entity.Anime, java.util.ArrayList"%>
<%
	ArrayList<Anime> animes = (ArrayList<Anime>)request.getAttribute("animes");
	String icon = (String)request.getAttribute("icon");

	Integer currentPage = (Integer)request.getAttribute("currentPage"), 
		numOfPages = (Integer)request.getAttribute("numOfPages");
	if(currentPage == null)
		currentPage = 1;
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
		ul {
			text-align: center;
			float: left;
		}

		#text {
			font-size: 18px;
			line-height: 25px;
			padding-bottom: 20px;
			padding-top: 15px;
		}
	</style>
</head>

<body>
	<!-- 导航栏 -->
	<div id="top-bar">
		<form id="search" action="searchAnime">
			<input name="anime" type="text">
			<button class="iconfont icon-fangdajing searchlink"></button>
		</form>
	</div>

	<div id="content">
		<ul>
			<% for(int i = 0; i < animes.size(); ++i) { %>		
				<li><img src=<%= "/video/" + animes.get(i).getName() + "/cover.png" %>></li>
				<li id="text"><%= animes.get(i).getName() %></li>
			<% } %>
		</ul>
		<form>
			<input type="submit" formaction="SearchPageUp" formmethod="post" value="上一页">
			<label><%=currentPage%>/<%=numOfPages%></label>
			<input type="submit" formaction="SearchPageDown" formmethod="post" value="下一页">
		</form>
	</div>
</body>
</html>