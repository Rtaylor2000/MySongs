<%-- 
    Document   : browse
    Created on : Mar 22, 2021, 10:40:52 PM
    Author     : Ryan
--%>
<%@ page import="java.util.Map" %>
<%
    @SuppressWarnings("unchecked")
    Map<Integer, Song> music = (Map<Integer, Song>)request.getAttribute("music");
%>
<!DOCTYPE html>
<html>
    <head>
        <title>Play List</title>
    </head>
    <body>
        <a href="<c:url value="/login?logout" />">Logout</a>
        <h2>Song List</h2>
        <p><a href="<c:url value="/songs?action=viewPlayList" />">View Play List</a></p>
        <p>Select an song to add it to your play list</p>
        <ul>
        <%
            for(int id : music.keySet()){
        %>
                <li><a href="<c:url value="/songs">
                    <c:param name="action" value="addToSongList" />
                    <c:param name="songId" value="<%= Integer.toString(id) %>"/>
                </c:url>"><%= music.get(id).getName() %></a></li>
        <%
            }
        %>
        </ul>
    </body>
</html>
