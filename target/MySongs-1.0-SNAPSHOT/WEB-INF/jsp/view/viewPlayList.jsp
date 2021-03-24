<%-- 
    Document   : viewPlayList
    Created on : Mar 23, 2021, 6:40:48 PM
    Author     : Ryan
--%>
<%@ page import="java.util.Map" %>
<!DOCTYPE html>
<html>
    <head>
        <title>View Play List</title>
    </head>
    <body>
        <h2>View Play List</h2>
        <p><a href="<c:url value="/songs" />">Browse Songs</a></p>
        <%
            @SuppressWarnings("unchecked")
            Map<Integer, Song> cart = (Map<Integer, Song>)session.getAttribute("cart");
            if(cart == null || cart.size() == 0)
                out.println("<p>Your play list is empty.</p>");
            else {
        %>
        <p><a href="<c:url value="/songs?action=emptyPlayList" />">Empty Play List</a></p>
        <ul>
        <%
                for(int id : cart.keySet()) {
        %>
        <li><a href="<%= cart.get(id).getYoutube() %>" target="_blank">
                            <%= cart.get(id).getName() %>(Artist: <%= cart.get(id).getArtist() %>)
                        </a> - 
                        <a href="<c:url value="/songs">
                            <c:param name="action" value="removeSong" />
                            <c:param name="songId" value="<%= Integer.toString(id) %>"/>
                        </c:url>">Remove</a>
                    </li>
        <%
                }
        %>
        </ul>
        <%
            }
        %>
    </body>
</html>