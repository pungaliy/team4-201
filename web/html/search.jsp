<%--
  Created by IntelliJ IDEA.
  User: Bub
  Date: 11/6/18
  Time: 10:42 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <%
        HttpSession httpSession = request.getSession();

        //attempt to retrieve the email and the roomID from the session
        var email = httpSession.getAttribute("email");
        var roomID = httpSession.getAttribute("roomID");

        //if the email or the roomID doesnt exist, that means this user must have not logged in yet and is in guest mode
        boolean loggedIn = (email != null && roomID != null);

        request.setAttribute("loggedIn", loggedIn);
        request.setAttribute("room", null /** TODO: replace with actual room jsonData**/);
    %>
</head>
<body>

</body>
</html>
