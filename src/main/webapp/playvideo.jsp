<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%
	String animeString = (String)request.getAttribute("animeString");
	String episode = (String)request.getAttribute("episode");
	String path = "/video/" + animeString + "/" + episode + ".mp4";
%>

<html>
<head>
	<title><%=episode%></title>
	<style>
		#dplayer
		{
			max-width: 960px;
			height: 620px;
			margin: 0 auto;
		}
	</style>
	<script src="/js/DPlayer.min.js"></script>
</head>

<body>
	<a style="text-decoration: none;" href="index.jsp">home</a>
	<div id="dplayer"></div>
	<script>
		const dp = new DPlayer({
			container: document.getElementById("dplayer"),
			video: {
				url: "<%=path%>",
			},
			volumn: 1,
			autoplay: true,
		});
	</script>
</body>
</html>