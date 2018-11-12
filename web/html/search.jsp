<%@ page import="sock.database.Room" %>
<%@ page import="sock.database.User" %>
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

        //the search query...replace with whatever the actual form textbox name will actually be, the current html has a name of sample
        var search = request.getParameter("sample");

        //searching is only accessible to logged in users
        if (loggedIn) {
            //TODO: Replace the following two lines with actual database query code once Ben gets it done.
            Room room = /** getRoom(search); **/ null;
            User user = /** getUser(search); **/ null;

            String id = null;
            String profilePictureURL = null; //will this even be used?
            String status = null; //an Enum might be more elegant? Asked Ben to see if he could support Enum for MongoDB. Strings are fine, but less elegant.
            //the search query will either get us a Room XOR User XOR null, since roomids and userids are mutually exclusive...or should be?
            if (room != null) {
                //Display the room...
                id = room.getRoomID();
                profilePictureURL = null; //will this even be used?
                status = room.getRoomStatus();
            } else if (user != null) {
                //Display the user...
                id = user.getRoomID();
                profilePictureURL = null; //will this even be used?
                //TODO: Get the actual user status implemented...?
//                status = user.getUserStatus();
            } else {
                //No search results. Display the failed search...
            }
        } else {
            //tell the user they're a guest and cant search...redirect to registration/login?
        }
    %>
</head>
<body>

</body>
</html>
