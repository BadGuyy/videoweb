<%@ page contentType="text/html; charset=UTF-8" language="java"%>
<%@ page contentType="text/html; charset=UTF-8" language="java"%>

<%@ page import="java.util.ArrayList"%>
<%
    ArrayList<String> episodes = (ArrayList<String>)request.getAttribute("episodes");
%>

<!DOCTYPE html>
<html>
<head>
	<title>Poi</title>
	<meta charset="UTF-8">
	<link rel="stylesheet" href="./resources/css/universal.css">
    <style>
        form {
            display: inline;
        }
    </style>
</head>

<body
    <a style="text-decoration: none;" href="index.jsp">home</a>
    <% for(int i = 0; i < episodes.size(); ++i) { %>
        <table>
            <tr>
                <td>
                    <form action="playvideo">
                        <input name="episode" type="text" value=<%=episodes.get(i)%>>
                        <button><%=episodes.get(i)%></button>
                    </form>
                </td>
            </tr>
        </table>
    <% } %>
</body>
</html>