<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ page import="java.util.ArrayList"%>
<%
	String animeName = (String)request.getAttribute("animeName");
	if(animeName == null)
        animeName = "";
    
	String covermsg = (String)request.getAttribute("covermsg");
	if(covermsg == null)
        covermsg = "";
    
	String videomsg = (String)request.getAttribute("videomsg");
	if(videomsg == null)
        videomsg = "";

    ArrayList<String> episodes = (ArrayList<String>)request.getAttribute("episodes");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Poi</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./resources/css/universal.css">
</head>

<body>
	<a style="text-decoration: none;" href="index.jsp">home</a><br>
    动漫名：<label><%=animeName%></label><br>
    
    <form method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传封面" formaction="uploadCover">
        <label><%=covermsg%></label>
    </form><br>

    <form method="post" enctype="multipart/form-data">
        <input type="file" name="file">
        <input type="submit" value="上传视频" formaction="uploadVideo">
        <label><%=videomsg%></label>
    </form><br>

    <% for(int i = 0; i < episodes.size(); ++i) { %>
        <table>
            <tr>
                <td>
                    <form method="post">
                        <input name="episode" type="text" value=<%=episodes.get(i)%>>
                        <input name="res" type="text" placeholder="填写修改后的名字">
                        <button formaction="modifyEpisodeName">修改该集名字</button>
                        <button formaction="deleteEpisode">删除该集</button>
                    </form><br>
                </td>
            </tr>
        </table>
    <% } %>
</body>
</html>