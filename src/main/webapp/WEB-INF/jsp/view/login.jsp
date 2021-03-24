<%-- 
    Document   : profile
    Created on : Mar 22, 2021, 9:12:04 PM
    Author     : Ryan
--%>

<%--@elvariable id="user" type="com.taylor.User"--%>

<!DOCTYPE html>
<html>
    <head>
        <title>Play List</title>
    </head>
    <body>
        <h2>Login</h2>
        <p>You must log in to access the play list site.</p>
        <form method="POST" action="<c:url value="/login" />">
            <label for="username">Username</label>
            <input type="text" name="username" id="username" /><br><br>
            <label for="password">Password</label>
            <input type="password" name="password" id="password" /><br><br>
            <input type="submit" value="Log In" />
        </form>
        <%
            if((Boolean)request.getAttribute("loginFailed")) {
        %>
        <p style="font-weight: bold; color: red;">The username or password you entered are not correct. Please try again.</p>
        <%
            }
        %>
    </body>
</html>
